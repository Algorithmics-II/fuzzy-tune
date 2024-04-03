package objects.recommendation;

import org.example.fuzzy_matcher.component.MatchService;
import org.example.fuzzy_matcher.domain.Document;
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
    public void itShouldApplyMatchForDemo2() {
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
}
