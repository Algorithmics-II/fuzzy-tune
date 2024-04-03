package objects.music;

import org.example.fuzzy_matcher.domain.Element;
import org.example.fuzzy_matcher.domain.Token;
import org.example.proyect.objects.Music;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.example.fuzzy_matcher.domain.ElementType.TEXT;
import static org.example.fuzzy_matcher.function.TokenizerFunction.wordTokenizer;

public class TokenizerMusicFunctionTest {
    private Music music;

    @BeforeEach
    public void setUp() {
        music = getMusic();
    }

    @Test
    public void trackNameMusicTokenizer() {
        String trackName = music.getTrackName();
        Element elem = new Element.Builder().setType(TEXT).setValue(trackName).createElement();
        List<Token> results = getResults(wordTokenizer().apply(elem));
        System.out.println(results);
        Assert.assertEquals("justified", results.get(0).getValue());
        Assert.assertEquals("ancient", results.get(1).getValue());
        Assert.assertEquals("stand", results.get(2).getValue());
        Assert.assertEquals("by", results.get(3).getValue());
        Assert.assertEquals("the", results.get(4).getValue());
        Assert.assertEquals("jams", results.get(5).getValue());
    }

    @Test
    public void artistGenresMusicTokenizer() {
        String artistGenres = music.getArtistGenres();
        Element elem = new Element.Builder().setType(TEXT).setValue(artistGenres).createElement();
        List<Token> results = getResults(wordTokenizer().apply(elem));
        Assert.assertEquals("acid", results.get(0).getValue());
        Assert.assertEquals("house", results.get(1).getValue());
        Assert.assertEquals("ambient", results.get(2).getValue());
        Assert.assertEquals("house", results.get(3).getValue());
        Assert.assertEquals("big", results.get(4).getValue());
    }

    private Music getMusic() {
        return new Music(1, "Justified & Ancient - Stand by the Jams", "The KLF", "Songs Collection",
                "The KLF", "1992-08-03", "",
                "acid house,ambient house,big beat,hip house", 1, 3, 216270, false, 0,
                0.617, 0.872, 0.048, 0.0158, 0.112, 0.408, 0.504, "");
    }

    private List<Token> getResults(Stream<Token> resultStream) {
        return resultStream.collect(Collectors.toList());
    }
}
