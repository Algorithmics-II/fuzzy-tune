package usb.finalproject.core.objects.user;

import usb.finalproject.core.objects.User;

import usb.finalproject.fuzzy.matcher.component.ElementMatch;
import usb.finalproject.fuzzy.matcher.domain.Document;
import usb.finalproject.fuzzy.matcher.domain.Element;
import usb.finalproject.fuzzy.matcher.domain.Match;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static usb.finalproject.fuzzy.matcher.domain.ElementType.TEXT;

public class MatchElementUserTest {
    private ElementMatch elementMatch = new ElementMatch();
    AtomicInteger atomicInteger = new AtomicInteger();
    private User user;
    private User user2;

    @BeforeEach
    public void setUp() {
        user = getUser1();
        user2 = getUser2();
    }

    @Test
    public void matchElementFavoritesGenres() {
        String favoriteGenres1 = String.join(" ", user.getFavoriteGenres());
        Element elem = new Element.Builder().setType(TEXT).setValue(favoriteGenres1).createElement();
        new Document.Builder(atomicInteger.incrementAndGet() + "").addElement(elem).createDocument();

        String favoriteGenres2 = String.join(" ", user2.getFavoriteGenres());
        Element elem2 = new Element.Builder().setType(TEXT).setValue(favoriteGenres2).createElement();
        new Document.Builder(atomicInteger.incrementAndGet() + "").addElement(elem2).createDocument();

        Set<Match<Element>> matchSet1 = elementMatch.matchElement(elem);
        Assert.assertEquals(0, matchSet1.size());

        Set<Match<Element>> matchSet2 = elementMatch.matchElement(elem2);
        System.out.println(matchSet2);
        Assert.assertEquals(1, matchSet2.size());
        Assert.assertEquals(0.4, matchSet2.iterator().next().getResult(), 0.0);
    }

    private User getUser1() {
        return new User(1, "John", 30, Arrays.asList("Pop", "Hip-hop", "R&B"),
                Arrays.asList("Justin Timberlake", "Pitbull", "Britney Spears"),
                Arrays.asList("Justified & Ancient - Stand by the Jams", "I Know You Want Me (Calle Ocho)", "From the Bottom of My Broken Heart"),
                Arrays.asList("Apeman - 2014 Remastered Version", "You Can't Always Get What You Want", "Don't Stop - 2004 Remaster"));
    }

    private User getUser2() {
        return new User(2, "Jennifer", 32, Arrays.asList("Pop", "Electro", "Classic Rock", "Hip-hop"),
                Arrays.asList("Rihanna", "Pitbull", "The Chainsmokers"),
                Arrays.asList("Listen to the Band - Single Version", "Something About The Way You Look Tonight - Edit Version"),
                Arrays.asList("Here Without You", "It's Like That", "Don't Stop - 2004 Remaster"));

    }
}
