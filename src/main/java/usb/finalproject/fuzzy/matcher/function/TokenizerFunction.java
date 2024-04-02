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
 * A functional interface to Tokenize Elements
 */
public class TokenizerFunction {

    private static final Soundex soundex = new Soundex();

    public static Function<Element, Stream<Token>> valueTokenizer() {
        return (element -> Stream.of(new Token(element.getPreProcessedValue(), element)));
    }

    public static Function<Element<String>, Stream<Token<String>>> wordTokenizer() {
        return (element) -> Arrays.stream(element.getPreProcessedValue().split("\\s+"))
                .map(token -> new Token<String>(token, element));
    }

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
                    // System.out.println(val +"->" + code);
                    return code;
                }).map(token -> new Token<String>(token, element));
    }

    public static Function<Element<String>, Stream<Token<String>>> triGramTokenizer() {
        return (element) -> getNGramTokens(3, element);
    }

    public static Function<Element<String>, Stream<Token<String>>> decaGramTokenizer() {
        return (element) -> getNGramTokens(10, element);
    }


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
    
    public static Function<Element<String>, Stream<Token<String>>> chainTokenizers(Function<Element<String>, Stream<Token<String>>>... tokenizers) {
        return element -> Arrays.stream(tokenizers).flatMap(fun -> fun.apply(element));
    }
}