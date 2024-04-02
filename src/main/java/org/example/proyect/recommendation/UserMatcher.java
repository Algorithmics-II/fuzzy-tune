package org.example.proyect.recommendation;

import org.example.fuzzy_matcher.component.MatchService;
import org.example.fuzzy_matcher.domain.*;
import org.example.proyect.objects.User;
import org.example.proyect.utils.Printer;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserMatcher {
    private MatchService matchService;

    public UserMatcher(MatchService matchService) {
        this.matchService = matchService;
    }

    public List<Document> getTopMatches(Document userDocument, List<Document> documents, int topN) {
        Map<Document, List<Match<Document>>> matches = matchService.applyMatch(documents);
        List<Match<Document>> userMatches = matches.get(userDocument);
        userMatches.sort(Comparator.comparing(Match<Document>::getScore, Comparator.comparingDouble(Score::getResult)).reversed());
        return userMatches.stream()
                .limit(topN)
                .map(Match::getMatchedWith)
                .collect(Collectors.toList());
    }

    public Document getUserDocumentToMatch(List<Document> documents, String userId) {
        return documents.stream().filter(document -> document.getKey().equals(userId)).findFirst().orElse(null);
    }

    public void printRecommendedUsers(List<Document> recommendedUsers, List<User> users) {
        for (Document document : recommendedUsers) {
            for (User user: users) {
                if (document.getKey().equals(String.valueOf(user.getId()))) {
                    Printer.printUser(user);
                }
            }
            System.out.println("--------------------");
        }
    }
}
