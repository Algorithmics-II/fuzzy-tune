package usb.finalproject.fuzzy.matcher.component;

import usb.finalproject.fuzzy.matcher.domain.Element;
import usb.finalproject.fuzzy.matcher.domain.Match;
import usb.finalproject.fuzzy.matcher.domain.Token;
import org.apache.commons.lang3.BooleanUtils;

import java.util.*;

/**
 * The `ElementMatch` class is used for matching elements.
 */
public class ElementMatch {

    private final TokenRepo tokenRepo;

    /**
     * This is the constructor of the `ElementMatch` class.
     * It initializes a new instance of the `TokenRepo` class.
     */
    public ElementMatch() {
        this.tokenRepo = new TokenRepo();
    }

    /**
     * This method matches an element with other elements in the token repository.
     * It first filters the tokens of the element if the document of the element is a source.
     * Then, it performs element threshold matching for each token.
     * Finally, it adds each token to the token repository.
     *
     * @param element The element to be matched.
     * @return A set of matches of the element.
     */
    public Set<Match<Element>> matchElement(Element element) {
        Set<Match<Element>> matchElements = new HashSet<>();
        Map<Element, Integer> elementTokenScore = new HashMap<>();

        List<Token> tokens = element.getTokens();
        tokens.stream()
                .filter(token -> BooleanUtils.isNotFalse(element.getDocument().isSource()))
                .forEach(token -> {
                    elementThresholdMatching(token, elementTokenScore, matchElements);
                });

        tokens.forEach(token -> tokenRepo.put(token));

        return matchElements;
    }

    /**
     * This method performs element threshold matching for a given token.
     * It gets the matching elements for the token from the token repository.
     * If there are matching elements, it calculates the score for each matching element and the element of the token.
     * If the score is greater than the threshold of the element of the token, it creates a new `Match` object and adds it to the set of matching elements.
     *
     * @param token             The token for which to perform element threshold matching.
     * @param elementTokenScore The map of element token scores.
     * @param matchingElements  The set of matching elements.
     */
    private void elementThresholdMatching(Token token, Map<Element, Integer> elementTokenScore, Set<Match<Element>> matchingElements) {
        Set<Element> matchElements = tokenRepo.get(token);
        Element element = token.getElement();

        // Token Match Found
        if (matchElements != null) {
            matchElements.forEach(matchElement -> {
                int score = elementTokenScore.getOrDefault(matchElement, 0) + 1;
                elementTokenScore.put(matchElement, score);
                // Element Score above threshold
                double elementScore = element.getScore(score, matchElement);

                // Element match Found
                if (elementScore > element.getThreshold()) {
                    Match<Element> elementMatch = new Match<>(element, matchElement, elementScore);
                    matchingElements.remove(elementMatch);
                    matchingElements.add(elementMatch);
                }
            });
        }
    }
}
