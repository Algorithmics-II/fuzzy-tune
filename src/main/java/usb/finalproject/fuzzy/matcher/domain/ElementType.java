package usb.finalproject.fuzzy.matcher.domain;

import java.util.function.Function;

import static usb.finalproject.fuzzy.matcher.domain.MatchType.EQUALITY;
import static usb.finalproject.fuzzy.matcher.domain.MatchType.NEAREST_NEIGHBORS;
import static usb.finalproject.fuzzy.matcher.function.PreProcessFunction.*;
import static usb.finalproject.fuzzy.matcher.function.TokenizerFunction.*;

/**
 * The `ElementType` enum defines different types of elements.
 * It is used to categorize the data and apply functions at different stages of match.
 * The functions can be overridden from the `Element` class using the appropriate setters at the time of creation.
 */
public enum ElementType {
    NAME,
    TEXT,
    ADDRESS,
    EMAIL,
    PHONE,
    NUMBER,
    DATE,
    AGE,
    ARRAY;

    /**
     * This method returns the preprocessing function for this element type.
     *
     * @return The preprocessing function for this element type.
     */
    protected Function getPreProcessFunction() {
        return switch (this) {
            case NAME -> namePreprocessing();
            case TEXT -> removeSpecialChars();
            case ADDRESS -> addressPreprocessing();
            case EMAIL -> removeDomain();
            case PHONE -> usPhoneNormalization();
            case NUMBER, AGE -> numberPreprocessing();
            case ARRAY -> arrayPreprocessing();
            default -> none();
        };
    }

    /**
     * This method returns the tokenizer function for this element type.
     *
     * @return The tokenizer function for this element type.
     */
    protected Function getTokenizerFunction() {
        return switch (this) {
            case NAME -> wordSoundexEncodeTokenizer();
            case TEXT -> wordTokenizer();
            case ADDRESS -> wordSoundexEncodeTokenizer();
            case EMAIL -> triGramTokenizer();
            case PHONE -> decaGramTokenizer();
            case ARRAY -> arrayTokenizer();
            default -> valueTokenizer();
        };
    }

    /**
     * This method returns the match type for this element type.
     *
     * @return The match type for this element type.
     */
    protected MatchType getMatchType() {
        return switch (this) {
            case NUMBER, DATE, AGE -> NEAREST_NEIGHBORS;
            default -> EQUALITY;
        };
    }
}
