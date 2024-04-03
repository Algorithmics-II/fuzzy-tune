package objects.music;

import org.example.fuzzy_matcher.component.ElementMatch;
import org.example.fuzzy_matcher.domain.Document;
import org.example.fuzzy_matcher.domain.Element;
import org.example.fuzzy_matcher.domain.Match;
import org.example.proyect.objects.Music;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static org.example.fuzzy_matcher.domain.ElementType.TEXT;

public class MatchElementMusicTest {
    private ElementMatch elementMatch = new ElementMatch();
    AtomicInteger atomicInteger = new AtomicInteger();
    private Music music;
    private Music music2;

    @BeforeEach
    public void setUp() {
        music = getMusic1();
        music2 = getMusic2();
    }

    @Test
    public void matchElementFavoritesGenres() {
        String genres = String.join(" ", music.getArtistGenres());
        Element elem = new Element.Builder().setType(TEXT).setValue(genres).createElement();
        new Document.Builder(atomicInteger.incrementAndGet() + "").addElement(elem).createDocument();

        String genres2 = String.join(" ", music2.getArtistGenres());
        Element elem2 = new Element.Builder().setType(TEXT).setValue(genres2).createElement();
        new Document.Builder(atomicInteger.incrementAndGet() + "").addElement(elem2).createDocument();

        Set<Match<Element>> matchSet1 = elementMatch.matchElement(elem);
        Assert.assertEquals(0, matchSet1.size());

        Set<Match<Element>> matchSet2 = elementMatch.matchElement(elem2);
        System.out.println(matchSet2);
        Assert.assertEquals(1, matchSet2.size());
        Assert.assertEquals(0.5, matchSet2.iterator().next().getResult(), 0.0);
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
