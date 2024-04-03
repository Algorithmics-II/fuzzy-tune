package org.example.fuzzy_matcher.function;

import org.example.fuzzy_matcher.domain.Element;
import org.example.fuzzy_matcher.domain.Token;
import org.example.fuzzy_matcher.exception.MatchException;
import org.example.fuzzy_matcher.util.Utils;
import org.apache.commons.codec.language.Soundex;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * The `TokenizerFunction` class provides several static methods for tokenizing elements.
 */
public class TokenizerFunction {

    private static final Soundex soundex = new Soundex();

    /**
     * This method returns a function that tokenizes an element by its preprocessed value.
     *
     * @return A function that tokenizes an element by its preprocessed value.
     */
    public static Function<Element, Stream<Token>> valueTokenizer() {
        return (element -> Stream.of(new Token(element.getPreProcessedValue(), element)));
    }

    /**
     * This method returns a function that tokenizes a string element by words.
     *
     * @return A function that tokenizes a string element by words.
     */
    public static Function<Element<String>, Stream<Token<String>>> wordTokenizer() {
        return (element) -> Arrays.stream(element.getPreProcessedValue().split("\\s+"))
                .map(token -> new Token<String>(token, element));
    }

    /**
     * This method returns a function that tokenizes a string element by words and encodes each word using the Soundex algorithm.
     *
     * @return A function that tokenizes a string element by words and encodes each word using the Soundex algorithm.
     */
    public static Function<Element<String>, Stream<Token<String>>> wordSoundexEncodeTokenizer() {
        return (element) -> Arrays.stream(element.getPreProcessedValue().toString().split("\\s+"))
                .map(val -> {
                    String code = val;
                    if (!Utils.isNumeric(val)) {

                        code = soundex.encode(val);
                        if (code.equals("")) {
                            code = val;
                        }
                    }
                    return code;
                }).map(token -> new Token<String>(token, element));
    }

    /**
     * This method returns a function that tokenizes a string element into trigrams.
     *
     * @return A function that tokenizes a string element into trigrams.
     */
    public static Function<Element<String>, Stream<Token<String>>> triGramTokenizer() {
        return (element) -> getNGramTokens(3, element);
    }

    /**
     * This method returns a function that tokenizes a string element into decagrams.
     *
     * @return A function that tokenizes a string element into decagrams.
     */
    public static Function<Element<String>, Stream<Token<String>>> decaGramTokenizer() {
        return (element) -> getNGramTokens(10, element);
    }

    /**
     * This method generates n-gram tokens from a string element.
     *
     * @param size    The size of the n-grams.
     * @param element The string element.
     * @return A stream of n-gram tokens.
     */
    public static Stream<Token<String>> getNGramTokens(int size, Element element) {
        Object elementValue = element.getPreProcessedValue();
        String elementValueStr;
        if (elementValue instanceof String) {
            elementValueStr = (String) elementValue;
        } else {
            throw new MatchException("Unsupported data type");
        }
        return Utils.getNGrams(elementValueStr, size).map(str -> new Token<String>(str, element));

    }

    /**
     * This method returns a function that applies a chain of tokenizer functions to a string element.
     *
     * @param tokenizers The chain of tokenizer functions.
     * @return A function that applies a chain of tokenizer functions to a string element.
     */
    public static Function<Element<String>, Stream<Token<String>>> chainTokenizers(Function<Element<String>, Stream<Token<String>>>... tokenizers) {
        return element -> Arrays.stream(tokenizers).flatMap(fun -> fun.apply(element));
    }
}
