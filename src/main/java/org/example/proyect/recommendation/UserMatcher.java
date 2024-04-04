package org.example.proyect.recommendation;

import org.example.fuzzy_matcher.component.MatchService;
import org.example.fuzzy_matcher.domain.*;
import org.example.proyect.objects.Music;
import org.example.proyect.objects.User;
import org.example.proyect.utils.Printer;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class is responsible for matching the user with the rest of the users and getting the top N matches.
 */
public class UserMatcher {
    private MatchService matchService;
    private List<Match<Document>> userMatches;
    private List<Match<Document>> userPreferencesMatches;

    /**
     * Constructor of the class.
     * @param matchService MatchService to apply the match.
     */
    public UserMatcher(MatchService matchService) {
        this.matchService = matchService;
    }

    /**
     * This method gets the top N matches for the user.
     * @param userDocument User document to match.
     * @param users List of users to match.
     * @param topN Number of top matches to get.
     * @return List of top N matches.
     */
    public List<Document> getTopMatches(Document userDocument, List<User> users, int topN) {
        List<Document> documents = users.stream().map(User::getDocument).collect(Collectors.toList());
        Map<Document, List<Match<Document>>> matches = matchService.applyMatch(documents);
        userMatches = matches.get(userDocument);
        userMatches.sort(Comparator.comparing(Match<Document>::getScore, Comparator.comparingDouble(Score::getResult)).reversed());
        return userMatches.stream()
                .limit(topN)
                .map(Match::getMatchedWith)
                .collect(Collectors.toList());
    }

    /**
     * This method gets the preferences recommendation of the user and that will search in all the music.
     * @param user User to get the preferences recommendation.
     * @param allMusic List of all music to search.
     * @return List of preferences recommendation.
     */
    public List<Document> getPreferencesRecommendation(User user, List<Music> allMusic) {
        userPreferencesMatches = matchUserWithMusic(user, allMusic);
        userPreferencesMatches.sort(Comparator.comparing(Match<Document>::getScore, Comparator.comparingDouble(Score::getResult)).reversed());

        return userPreferencesMatches.stream()
                .limit(10)
                .map(Match::getMatchedWith)
                .collect(Collectors.toList());
    }

    /**
     * This method matches the user with the music.
     * @param user User to match.
     * @param allMusic List of all music to match.
     * @return List of matches.
     */
    private List<Match<Document>> matchUserWithMusic(User user, List<Music> allMusic) {
        Document userDocument = user.getDocumentPreferences();
        List<Document> musicDocuments = allMusic.stream().map(Music::getDocumentPreferencesUser).collect(Collectors.toList());
        MatchService matchService = new MatchService();

        Map<Document, List<Match<Document>>> matches = matchService.applyMatch(userDocument, musicDocuments);

        System.out.println(matches);
        return matches.get(userDocument);
    }

    /**
     * This method prints the recommended songs.
     * @param recommendedMusics List of recommended songs.
     * @param music List of music.
     */
    public void printRecommendedSongs(List<Document> recommendedMusics, List<Music> music) {
        int index = 0;
        for (Document document : recommendedMusics) {
            for (Music song: music) {
                if (document.getKey().equals(String.valueOf(song.getId()))) {
                    Printer.printSong(song);
                    System.out.println("Score match: " + userPreferencesMatches.get(index++).getScore().getResult());
                }
            }
            System.out.println("--------------------");
        }
    }


    /**
     * This method gets the user document to match.
     * @param documents List of documents to match.
     * @param userId User id to match.
     * @return User document to match.
     */
    public Document getUserDocumentToMatch(List<Document> documents, String userId) {
        return documents.stream().filter(document -> document.getKey().equals(userId)).findFirst().orElse(null);
    }

    /**
     * This method prints the recommended users.
     * @param recommendedUsers List of recommended users.
     * @param users List of users.
     */
    public void printRecommendedUsers(List<Document> recommendedUsers, List<User> users) {
        int index = 0;
        for (Document document : recommendedUsers) {
            for (User user: users) {
                if (document.getKey().equals(String.valueOf(user.getId()))) {
                    Printer.printUser(user);
                    System.out.println("Score match: " + userMatches.get(index++).getScore().getResult());
                }
            }
            System.out.println("--------------------");
        }
    }

    /**
     * This method gets the user matches.
     * @return List of user matches.
     */
    public List<Match<Document>> getUserMatches() {
        return userMatches;
    }
}
