package usb.finalproject.core.objects.user;

import usb.finalproject.fuzzy.matcher.domain.Element;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static usb.finalproject.fuzzy.matcher.domain.ElementType.ARRAY;

public class PreProcessFunctionUserTest {
    @Test
    public void preProcessTest1(){
        List<String> value = Arrays.asList("Pop","Hip-hop","R&B");
        Element element = new Element.Builder().setType(ARRAY).setValue(value).createElement();
        Assert.assertEquals("pop hiphop randb", element.getPreProcessedValue());
    }

    @Test
    public void preProcessTest2(){
        List<String> value = Arrays.asList("Justin Timberlake", "Pitbull", "Britney Spears");
        Element element = new Element.Builder().setType(ARRAY).setValue(value).createElement();
        Assert.assertEquals("justin timberlake pitbull britney spears", element.getPreProcessedValue());
    }

    @Test
    public void preProcessTest3(){
        List<String> value = Arrays.asList("Justified & Ancient - Stand by the Jams", "I Know You Want Me (Calle Ocho)", "From the Bottom of My Broken Heart");
        Element element = new Element.Builder().setType(ARRAY).setValue(value).createElement();
        Assert.assertEquals("justified and ancient stand by the jams i know you want me calle ocho from the bottom of my broken heart", element.getPreProcessedValue());
    }
}
