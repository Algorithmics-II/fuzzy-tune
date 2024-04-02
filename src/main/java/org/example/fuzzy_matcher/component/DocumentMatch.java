package org.example.fuzzy_matcher.component;


import org.example.fuzzy_matcher.domain.*;
import org.apache.commons.lang3.BooleanUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The `DocumentMatch` class starts the matching process by element level matching and aggregates the results back.
 * It uses the `ScoringFunction` defined at each `Document` to get the aggregated `Document` score for matched `Elements`.
 */
public class DocumentMatch {

    private final ElementMatch elementMatch;

    /**
     * This is the constructor of the `DocumentMatch` class.
     * It initializes a new instance of the `ElementMatch` class.
     */
    public DocumentMatch() {
        this.elementMatch = new ElementMatch();
    }

    /**
     * This method executes the matching of a stream of `Document` objects.
     *
     * @param documents The stream of `Document` objects.
     * @return A stream of `Match` of `Document` type objects.
     */
    public Stream<Match<Document>> matchDocuments(Stream<Document> documents) {

        Stream<Match<Document>> documentMatch = documents.flatMap(document -> {
            Set<Element> elements = document.getPreProcessedElement();
            Set<Match<Element>> eleMatches = elements.stream()
                    .flatMap(element -> elementMatch.matchElement(element).stream())
                    .collect(Collectors.toSet());
            return documentThresholdMatching(document, eleMatches);
        });

        return documentMatch;
    }

    /**
     * This method performs document threshold matching.
     * It groups the matching elements by their document and then creates a `Match` object for each group.
     * If the score of the `Match` object is greater than the threshold of the document, it returns a stream containing the `Match` object.
     * Otherwise, it returns an empty stream.
     *
     * @param document         The `Document` object.
     * @param matchingElements The set of matching `Element` objects.
     * @return A stream of `Match` of `Document` type objects.
     */
    private Stream<Match<Document>> documentThresholdMatching(Document document, Set<Match<Element>> matchingElements) {
        Map<Document, List<Match<Element>>> matches = matchingElements.stream()
                .collect(Collectors.groupingBy(matchElement -> matchElement.getMatchedWith().getDocument()));

        Stream<Match<Document>> result = matches.entrySet().stream().flatMap(matchEntry -> {

            List<Score> childScoreList = matchEntry.getValue()
                    .stream()
                    .map(d -> d.getScore())
                    .collect(Collectors.toList());
            //System.out.println(Arrays.toString(childScoreList.toArray()));
            Match<Document> leftMatch = new Match<Document>(document, matchEntry.getKey(), childScoreList);

            // Document match Found
            if (leftMatch.getScore().getResult() > leftMatch.getData().getThreshold()) {

                if (BooleanUtils.isNotFalse(matchEntry.getKey().isSource())) {
                    Match<Document> rightMatch = new Match<Document>(matchEntry.getKey(), document, childScoreList);
                    return Stream.of(leftMatch, rightMatch);
                }
                return Stream.of(leftMatch);
            } else {
                return Stream.empty();
            }
        });

        return result;
    }

}
