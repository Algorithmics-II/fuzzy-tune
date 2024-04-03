package objects.user;

import org.example.fuzzy_matcher.component.MatchService;
import org.example.fuzzy_matcher.domain.Document;
import org.example.fuzzy_matcher.domain.Match;
import org.example.objects.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MatchServiceUserTest {
    private MatchService matchService = new MatchService();

    @Test
    public void itShouldApplyMatchForDemo() {
        List<Document> documentList = new ArrayList<>();
        documentList.add(getUser1().toDocument());
        documentList.add(getUser2().toDocument());

        Map<String, List<Match<Document>>> result = matchService.applyMatchByDocId(documentList);

        result.entrySet().forEach(entry -> {
            entry.getValue().forEach(match -> {
                System.out.println("Data: " + match.getData() + " Matched With: " + match.getMatchedWith() + " Score: " + match.getScore().getResult());
            });
        });
        Assert.assertEquals(2, result.size());
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
}
