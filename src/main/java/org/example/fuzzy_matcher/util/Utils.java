package org.example.fuzzy_matcher.util;

import org.apache.lucene.analysis.ngram.NGramTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.example.fuzzy_matcher.exception.MatchException;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {
    /**
     * This method generates n-grams from a given string.
     *
     * @param value The input string.
     * @param size  The size of the n-grams.
     * @return A stream of n-grams.
     */
    public static Stream<String> getNGrams(String value, int size) {
        Stream.Builder<String> stringStream = Stream.builder();
        if (value.length() <= size) {
            stringStream.add(value);
        } else {
            NGramTokenizer nGramTokenizer = new NGramTokenizer(size, size);
            CharTermAttribute charTermAttribute = nGramTokenizer.addAttribute(CharTermAttribute.class);
            nGramTokenizer.setReader(new StringReader(value));
            try {
                nGramTokenizer.reset();
                while (nGramTokenizer.incrementToken()) {
                    stringStream.add(charTermAttribute.toString());
                }
                nGramTokenizer.end();
                nGramTokenizer.close();
            } catch (IOException io) {
                throw new MatchException("Failure in creating tokens : ", io);
            }
        }
        return stringStream.build();
    }

    /**
     * utility method to apply dictionary for normalizing strings
     *
     * @param str  A String of element value to be normalized
     * @param dict A dictionary map containing the mapping of string to normalize
     * @return the normalized string
     */
    public static String getNormalizedString(String str, Map<String, String> dict) {
        return Arrays.stream(str.split("\\s+"))
                .map(d -> dict.containsKey(d.toLowerCase()) ?
                        dict.get(d.toLowerCase())
                        : d)
                .collect(Collectors.joining(" "));
    }

    /**
     * This method checks if a string contains any numeric characters.
     *
     * @param str The input string.
     * @return `true` if the string contains any numeric characters, `false` otherwise.
     */
    public static boolean isNumeric(String str) {
        return str.matches(".*\\d.*");
    }
}
