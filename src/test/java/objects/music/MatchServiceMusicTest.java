package objects.music;

import usb.finalproject.core.objects.Music;
import usb.finalproject.fuzzy.matcher.component.MatchService;
import usb.finalproject.fuzzy.matcher.domain.Document;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MatchServiceMusicTest {
    private MatchService matchService = new MatchService();

    @Test
    public void itShouldApplyMatchForDemo() {
        List<Document> documentList = new ArrayList<>();
        documentList.add(getMusic1().toDocument());
        documentList.add(getMusic2().toDocument());

        // This unit test will not run because there is no preprocessing for Double, so that must be implemented.
        //Map<String, List<Match<Document>>> result = matchService.applyMatchByDocId(documentList);

        /*result.entrySet().forEach(entry -> {
            entry.getValue().forEach(match -> {
                System.out.println("Data: " + match.getData() + " Matched With: " + match.getMatchedWith() + " Score: " + match.getScore().getResult());
            });
        });
        Assert.assertEquals(2, result.size());*/
    }

    private Music getMusic1() {
        return new Music(1, "Justified & Ancient - Stand by the Jams", "The KLF", "Songs Collection",
                "The KLF", "1992-08-03", "",
                "acid house,ambient house,big beat,hip house", 1, 3, 216270, false, 0,
                0.617, 0.872, 0.048, 0.0158, 0.112, 0.408, 0.504, "");
    }
    private Music getMusic2() {
        return new Music(2, "Justified & Ancient", "The KLF", "Songs Collection 2",
                "The KLF - MAN", "1992-08-03", "",
                "acid house,ambient house,pop,electro", 1, 5, 316270, false, 0,
                0.617, 0.872, 0.048, 0.0158, 0.112, 0.408, 0.504, "");
    }
}
