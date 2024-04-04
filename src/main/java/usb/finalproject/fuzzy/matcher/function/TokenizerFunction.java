package usb.finalproject.fuzzy.matcher.function;

import usb.finalproject.fuzzy.matcher.domain.Element;
import usb.finalproject.fuzzy.matcher.domain.Token;
import usb.finalproject.fuzzy.matcher.util.Utils;
import usb.finalproject.fuzzy.matcher.exception.MatchException;
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
     * This method tokenizes an array of strings.
     * @return A function that tokenizes an array of strings.
     */
    public static Function<Element<String>, Stream<Token<String>>> arrayTokenizer() {
        return (element) -> Arrays.stream(element.getPreProcessedValue().split(" "))
                .map(str -> new Token<String>(str, element));
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
     * Returns a function that tokenizes a string element into word-based n-grams.
     * The function takes an Element<String> object and returns a Stream<Token<String>>.
     *
     * @return A function that tokenizes a string element into word-based n-grams.
     */
    public static Function<Element<String>, Stream<Token<String>>> wordBasedTokenizer() {
        return (element) -> getTokenBasedNGrams(element);
    }

    
    /**
     * Generates word-based n-gram tokens from a string element.
     * This method splits the string element into words and constructs n-gram tokens.
     *
     * @param element The string element to tokenize.
     * @return A stream of word-based n-gram tokens.
     */
    public static Stream<Token<String>> getTokenBasedNGrams(Element element) {
        Object elementValue = element.getPreProcessedValue();
        String elementValueStr;
        if (elementValue instanceof String) {
            elementValueStr = (String) elementValue;
        } else {
            throw new MatchException("Unsupported data type");
        }
        
        String[] words = elementValueStr.split("\\s+");
        
        return Utils.getNGrams(elementValueStr, words.length).map(str -> new Token<String>(str, element));
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
