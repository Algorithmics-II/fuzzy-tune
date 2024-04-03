package objects.recommendation;

import org.example.fuzzy_matcher.component.MatchService;
import org.example.fuzzy_matcher.domain.Document;
import org.example.proyect.objects.Music;
import org.example.proyect.objects.User;
import org.example.proyect.recommendation.UserMatcher;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserMatcherTest {
    private UserMatcher userMatcher;
    private MatchService matchService;

    @Test
    public void fiveUserRecommendation() {
        matchService = new MatchService();
        userMatcher = new UserMatcher(matchService);

        List<User> users = new ArrayList<>();
        users.add(getUser1());
        users.add(getUser2());
        users.add(getUser3());

        List<Document> documentList = new ArrayList<>();
        documentList.add(users.get(0).getDocument());
        documentList.add(users.get(1).getDocument());
        documentList.add(users.get(2).getDocument());

        Document userToMatch = userMatcher.getUserDocumentToMatch(documentList, "1");

        List<Document> recommendedUsers = userMatcher.getTopMatches(userToMatch, documentList, 5);
        userMatcher.printRecommendedUsers(recommendedUsers, users);
        System.out.println(recommendedUsers);

        // User 1 recommended: Mateo
        Assert.assertEquals("Mateo", users.get(2).getName());
        Assert.assertEquals(Arrays.asList("Pop","Hip-hop","R&B"), users.get(2).getFavoriteGenres());
        Assert.assertEquals(Arrays.asList("Justin Timberlake", "Pitbull", "Britney Spears"), users.get(2).getFavoriteArtists());
        Assert.assertEquals(Arrays.asList("Justified & Ancient - Stand by the Jams", "I Know You Want Me (Calle Ocho)", "From the Bottom of My Broken Heart"), users.get(2).getFavoriteSongs());
        Assert.assertEquals(Arrays.asList("Apeman - 2014 Remastered Version", "You Can't Always Get What You Want", "Don't Stop - 2004 Remaster"), users.get(2).getRecentlyPlayed());
        Assert.assertEquals(1.0, userMatcher.getUserMatches().get(0).getScore().getResult(), 0.0001);

        // User 2 recommended: Jeniffer
        Assert.assertEquals("Jennifer", users.get(1).getName());
        Assert.assertEquals(Arrays.asList("Pop", "Electro", "Hip-hop"), users.get(1).getFavoriteGenres());
        Assert.assertEquals(Arrays.asList("Britney Spears", "Pitbull", "The Chainsmokers"), users.get(1).getFavoriteArtists());
        Assert.assertEquals(Arrays.asList("Justified & Ancient - Stand by the Jams", "I Know You Want Me (Calle Ocho)", "From the Bottom of My Broken Heart"), users.get(1).getFavoriteSongs());
        Assert.assertEquals(Arrays.asList("Here Without You", "It's Like That", "Apeman - 2014 Remastered Version", "You Can't Always Get What You Want"), users.get(1).getRecentlyPlayed());
        Assert.assertEquals(0.8169889330626028, userMatcher.getUserMatches().get(1).getScore().getResult(), 0.0001);
    }

    @Test
    public void recommendationOnPreferencesUser() {
        matchService = new MatchService();
        userMatcher = new UserMatcher(matchService);

        List<User> users = new ArrayList<>();
        users.add(getUser1());

        List<Music> allMusics = new ArrayList<>();
        allMusics.add(getMusic1());
        allMusics.add(getMusic2());
        allMusics.add(getMusic3());
        allMusics.add(getMusic4());
        allMusics.add(getMusic5());

        List<Document> recommendedUsers = userMatcher.getPreferencesRecommendation(getUser1(), allMusics);
        userMatcher.printRecommendedSongs(recommendedUsers, allMusics);
    }

    private User getUser1() {
        return new User(1, "John", 30, Arrays.asList("Pop", "Hip-hop", "R&B"),
                Arrays.asList("Justin Timberlake", "Pitbull", "Britney Spears"),
                Arrays.asList("Justified & Ancient - Stand by the Jams", "I Know You Want Me (Calle Ocho)", "From the Bottom of My Broken Heart"),
                Arrays.asList("Apeman - 2014 Remastered Version", "You Can't Always Get What You Want", "Don't Stop - 2004 Remaster"));
    }

    private User getUser2() {
        return new User(2, "Jennifer", 30, Arrays.asList("Pop", "Electro", "Hip-hop"),
                Arrays.asList("Britney Spears", "Pitbull", "The Chainsmokers"),
                Arrays.asList("Justified & Ancient - Stand by the Jams", "I Know You Want Me (Calle Ocho)", "From the Bottom of My Broken Heart"),
                Arrays.asList("Here Without You", "It's Like That", "Apeman - 2014 Remastered Version", "You Can't Always Get What You Want"));

    }

    private User getUser3() {
        return new User(3, "Mateo", 30, Arrays.asList("Pop", "Hip-hop", "R&B"),
                Arrays.asList("Justin Timberlake", "Pitbull", "Britney Spears"),
                Arrays.asList("Justified & Ancient - Stand by the Jams", "I Know You Want Me (Calle Ocho)", "From the Bottom of My Broken Heart"),
                Arrays.asList("Apeman - 2014 Remastered Version", "You Can't Always Get What You Want", "Don't Stop - 2004 Remaster"));
    }

    private Music getMusic1() {
        return new Music(1, "Justified & Ancient - Stand by the Jams, I Know You Want Me (Calle Ocho), From the Bottom of My Broken Heart", "Justin Timberlake, Britney Spears, Pitbull", "Songs Collection",
                "Pitbull", "1992-08-03", "",
                "pop, electro R&B", 1, 3, 216270, false, 0,
                0.617, 0.872, 0.048, 0.0158, 0.112, 0.408, 0.504, "");
    }
    private Music getMusic2() {
        return new Music(2, "Why Don't You", "The KLF - MAN, Justin Timberlake, Pitbull", "Songs Collection 2",
                "The KLF - MAN, Justin Timberlake, Pitbull", "1992-08-03", "",
                "electro swing", 1, 5, 316270, false, 0,
                0.617, 0.872, 0.048, 0.0158, 0.112, 0.408, 0.504, "");
    }
    private Music getMusic3() {
        return new Music(3, "Hips Don't Lie (feat. Wyclef Jean)", "Shakira, Wyclef Jean", "Songs Collection 2",
                "The KLF - MAN", "1992-08-03", "",
                "pop, electro", 1, 5, 316270, false, 0,
                0.617, 0.872, 0.048, 0.0158, 0.112, 0.408, 0.504, "");
    }
    private Music getMusic4() {
        return new Music(4, "Love Story - US Album Version", "Taylor Swift, Britney Spears", "Songs Collection 2",
                "Britney Spears", "1992-08-03", "",
                "pop, R&B", 1, 5, 316270, false, 0,
                0.617, 0.872, 0.048, 0.0158, 0.112, 0.408, 0.504, "");
    }
    private Music getMusic5() {
        return new Music(5, "Love Story - US Album Version", "Taylor Swift, Britney Spears", "Songs Collection 2",
                "The KLF - MAN, Britney Spears, Pitbull", "1992-08-03", "",
                "Pop Hip-hop R&B", 1, 5, 316270, false, 0,
                0.617, 0.872, 0.048, 0.0158, 0.112, 0.408, 0.504, "");
    }
}
