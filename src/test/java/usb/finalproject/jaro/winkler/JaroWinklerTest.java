package usb.finalproject.jaro.winkler;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JaroWinklerTest {
  @Test
  public void jaroWinklerSimilarityTest() {
    assertEquals(1.0, JaroWinkler.jaroWinklerSimilarity("hello", "hello"), 0.001);
    //assertEquals(0.944, JaroWinkler.jaroWinklerSimilarity("hello", "holla"), 0.001);
    //assertEquals(0.892, JaroWinkler.jaroWinklerSimilarity("hello", "hell"), 0.001);
    //assertEquals(0.0, JaroWinkler.jaroWinklerSimilarity("", "hello"), 0.001);
    //assertEquals(0.0, JaroWinkler.jaroWinklerSimilarity("hello", ""), 0.001);
    assertEquals(1.0, JaroWinkler.jaroWinklerSimilarity("", ""), 0.001);
  }

  @Test
  public void calculateJaroSimilarityTest() {
    assertEquals(1.0, JaroWinkler.calculateJaroSimilarity("hello", "hello"), 0.001);
    assertEquals(0.733, JaroWinkler.calculateJaroSimilarity("hello", "holla"), 0.001);
    assertEquals(0.933, JaroWinkler.calculateJaroSimilarity("hello", "hell"), 0.001);
    assertEquals(0.0, JaroWinkler.calculateJaroSimilarity("", "hello"), 0.001);
    assertEquals(0.0, JaroWinkler.calculateJaroSimilarity("hello", ""), 0.001);
    assertEquals(1.0, JaroWinkler.calculateJaroSimilarity("", ""), 0.001);
  }

  @Test
  public void getCommonPrefixLengthTest() {
    assertEquals(5, JaroWinkler.getCommonPrefixLength("hello", "hello"));
    assertEquals(1, JaroWinkler.getCommonPrefixLength("hello", "holla"));
    assertEquals(4, JaroWinkler.getCommonPrefixLength("hello", "hell"));
    assertEquals(0, JaroWinkler.getCommonPrefixLength("", "hello"));
    assertEquals(0, JaroWinkler.getCommonPrefixLength("hello", ""));
    assertEquals(0, JaroWinkler.getCommonPrefixLength("", ""));
  }
}
