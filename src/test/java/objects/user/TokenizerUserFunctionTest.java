package objects.user;

import org.example.fuzzy_matcher.domain.Element;
import org.example.fuzzy_matcher.domain.Token;
import org.example.proyect.objects.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.example.fuzzy_matcher.domain.ElementType.*;
import static org.example.fuzzy_matcher.function.TokenizerFunction.*;

public class TokenizerUserFunctionTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = getUser1();
    }

    @Test
    public void nameUserTokenizer() {
        String nameUser = user.getName();
        Element elem = new Element.Builder().setType(NAME).setValue(nameUser).createElement();
        List<Token> results = getResults(wordSoundexEncodeTokenizer().apply(elem));
        Assert.assertEquals("J500", results.get(0).getValue());
    }

    @Test
    public void ageUserTokenizer() {
        int age = user.getAge();
        Element elem = new Element.Builder().setType(AGE).setValue(age).createElement();
        List<Token> results = getResults(valueTokenizer().apply(elem));
        Assert.assertEquals(30, results.get(0).getValue());
    }

    @Test
    public void favoriteGenresUserTokenizer() {
        List<String> favoriteGenres = user.getFavoriteGenres();
        Element elem = new Element.Builder().setType(ARRAY).setValue(favoriteGenres).createElement();
        List<Token> results = getResults(arrayTokenizer().apply(elem));
        Assert.assertEquals("pop", results.get(0).getValue());
        Assert.assertEquals("hiphop", results.get(1).getValue());
        Assert.assertEquals("randb", results.get(2).getValue());
    }

    @Test
    public void favoriteArtistUserTokenizer() {
        List<String> favoriteArtist = user.getFavoriteArtists();
        Element elem = new Element.Builder().setType(ARRAY).setValue(favoriteArtist).createElement();
        List<Token> results = getResults(arrayTokenizer().apply(elem));
        Assert.assertEquals("justin", results.get(0).getValue());
        Assert.assertEquals("timberlake", results.get(1).getValue());
        Assert.assertEquals("pitbull", results.get(2).getValue());
        Assert.assertEquals("britney", results.get(3).getValue());
        Assert.assertEquals("spears", results.get(4).getValue());
    }

    private User getUser1() {
        return new User(1, "John", 30, Arrays.asList("Pop", "Hip-hop", "R&B"),
                Arrays.asList("Justin Timberlake", "Pitbull", "Britney Spears"),
                Arrays.asList("Justified & Ancient - Stand by the Jams", "I Know You Want Me (Calle Ocho)", "From the Bottom of My Broken Heart"),
                Arrays.asList("Apeman - 2014 Remastered Version", "You Can't Always Get What You Want", "Don't Stop - 2004 Remaster"));
    }

    private List<Token> getResults(Stream<Token> resultStream) {
        return resultStream.collect(Collectors.toList());
    }
}
