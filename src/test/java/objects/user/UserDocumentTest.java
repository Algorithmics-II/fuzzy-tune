package objects.user;

import usb.finalproject.core.objects.User;
import usb.finalproject.fuzzy.matcher.domain.Document;
import usb.finalproject.fuzzy.matcher.domain.Element;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static usb.finalproject.fuzzy.matcher.domain.ElementType.*;
import static usb.finalproject.fuzzy.matcher.domain.ElementType.EMAIL;

public class UserDocumentTest {
    @Test
    public void childCountAndUnmatchedCountTest() {
        Document d1 = getUser1().getDocument();
        System.out.println(d1);

        Document d2 = new Document.Builder("2")
                .addElement(new Element.Builder().setType(NAME).setValue("James Parker").createElement())
                .addElement(new Element.Builder().setType(ADDRESS).setValue(" ").createElement())
                .addElement(new Element.Builder().setType(PHONE).setValue("123-123-1234").createElement())
                .addElement(new Element.Builder().setType(EMAIL).setValue("james@email.com").createElement())
                .createDocument();

        Assert.assertEquals(6, d1.getChildCount(d2));
        Assert.assertEquals(6, d2.getChildCount(d1));
        Assert.assertEquals(6, d1.getUnmatchedChildCount(d2));
    }

    private User getUser1() {
        return new User(1, "John", 30, Arrays.asList("Pop", "Hip-hop", "R&B"),
                Arrays.asList("Justin Timberlake", "Pitbull", "Britney Spears"),
                Arrays.asList("Justified & Ancient - Stand by the Jams", "I Know You Want Me (Calle Ocho)", "From the Bottom of My Broken Heart"),
                Arrays.asList("Apeman - 2014 Remastered Version", "You Can't Always Get What You Want", "Don't Stop - 2004 Remaster"));
    }
}
