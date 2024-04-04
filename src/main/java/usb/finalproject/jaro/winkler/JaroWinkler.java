package usb.finalproject.jaro.winkler;

/**
 * Class for calculating Jaro-Winkler similarity between two strings.
 */
public class JaroWinkler {

  /**
   * Calculates the Jaro-Winkler similarity between two strings.
   *
   * @param s1 The first string.
   * @param s2 The second string.
   * @return The Jaro-Winkler similarity score between the two strings.
   */
  public static double jaroWinklerSimilarity(String s1, String s2) {
    if (s1 == null || s2 == null) {
      return 0.0;
    }

    double jaroSimilarity = calculateJaroSimilarity(s1, s2);

    double jaroWinklerSimilarity = jaroSimilarity + (getCommonPrefixLength(s1, s2) * (1 - jaroSimilarity));

    return jaroWinklerSimilarity;
  }

  /**
   * Calculates the Jaro similarity between two strings.
   *
   * @param s1 The first string.
   * @param s2 The second string.
   * @return The Jaro similarity score between the two strings.
   */
  public static double calculateJaroSimilarity(String s1, String s2) {
    if (s1.length() == 0 && s2.length() == 0) {
      return 1.0;
    }

    int matchDistance = Math.max(s1.length(), s2.length()) / (2 - 1);
    boolean[] s1Matches = new boolean[s1.length()];
    boolean[] s2Matches = new boolean[s2.length()];
    int matches = 0;

    for (int i = 0; i < s1.length(); i++) {
      int start = Math.max(0, i - matchDistance);
      int end = Math.min(i + matchDistance + 1, s2.length());
      for (int j = start; j < end; j++) {
        if (!s2Matches[j] && s1.charAt(i) == s2.charAt(j)) {
          s1Matches[i] = true;
          s2Matches[j] = true;
          matches++;
          break;
        }
      }
    }

    if (matches == 0) {
      return 0.0;
    }

    double transpositions = 0;
    int k = 0;

    for (int i = 0; i < s1.length(); i++) {
      if (s1Matches[i]) {
        while (!s2Matches[k]) {
          k++;
        }

        if (s1.charAt(i) != s2.charAt(k)) {
          transpositions++;
        }

        k++;
      }
    }

    return (matches / (double) s1.length() + matches / (double) s2.length() + (matches - transpositions / 2) / (double) matches) / 3.0;
  }

  /**
   * Calculates the length of the common prefix between two strings.
   *
   * @param s1 The first string.
   * @param s2 The second string.
   * @return The length of the common prefix between the two strings.
   */
  public static int getCommonPrefixLength(String s1, String s2) {
    int prefixLength = 0;
    int minLength = Math.min(s1.length(), s2.length());

    for (int i = 0; i < minLength; i++) {
      if (s1.charAt(i) == s2.charAt(i)) {
        prefixLength++;
      }
      else {
        break;
      }
    }

    return prefixLength;
  }
}
