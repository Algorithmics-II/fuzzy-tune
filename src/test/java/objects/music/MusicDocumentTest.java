package objects.music;

import org.example.fuzzy_matcher.domain.Document;
import org.example.fuzzy_matcher.domain.Element;
import org.example.objects.Music;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


import static org.example.fuzzy_matcher.domain.ElementType.*;

public class MusicDocumentTest {
    @Test
    public void childCountAndUnmatchedCountTest() {
        Document d1 = getMusic().toDocument();
        System.out.println(d1);

        Document d2 = new Document.Builder("2")
                .addElement(new Element.Builder().setType(NAME).setValue("Ariana Grande").createElement())
                .addElement(new Element.Builder().setType(ADDRESS).setValue(" ").createElement())
                .addElement(new Element.Builder().setType(PHONE).setValue("123-123-1234").createElement())
                .addElement(new Element.Builder().setType(TEXT).setValue("Sia").createElement())
                .createDocument();

        Assert.assertEquals(19, d1.getChildCount(d2));
        Assert.assertEquals(19, d2.getChildCount(d1));
        Assert.assertEquals(17, d1.getUnmatchedChildCount(d2));
    }

    private Music getMusic() {
        return new Music(1, "Justified & Ancient - Stand by the Jams", "The KLF", "Songs Collection",
                "The KLF", "1992-08-03", "",
                "acid house,ambient house,big beat,hip house", 1, 3, 216270, false, 0,
                0.617, 0.872, 0.048, 0.0158, 0.112, 0.408, 0.504, "");
    }
}
