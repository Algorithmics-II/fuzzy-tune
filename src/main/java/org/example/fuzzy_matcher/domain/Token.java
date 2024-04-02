package org.example.fuzzy_matcher.domain;

import java.util.Objects;

/**
 * The `Token` class is a generic class that represents a token object.
 * It contains two properties: `value` and `element`.
 *
 * @param <T> This is the type parameter which represents the type of the value of the token.
 */
public class Token<T> {

    private T value;
    private Element element;

    /**
     * This is the constructor of the `Token` class.
     *
     * @param value   The value of the token.
     * @param element The element associated with the token.
     */
    public Token(T value, Element element) {
        this.value = value;
        this.element = element;
    }

    /**
     * This method returns the value of the token.
     *
     * @return The value of the token.
     */
    public T getValue() {
        return value;
    }

    /**
     * This method returns the element associated with the token.
     *
     * @return The element associated with the token.
     */
    public Element getElement() {
        return element;
    }

    /**
     * This method returns a string representation of the token.
     *
     * @return A string representation of the token.
     */
    @Override
    public String toString() {
        return "{" + value + '}';
    }

    /**
     * This method checks if the given object is equal to this token.
     *
     * @param o The object to be compared.
     * @return `true` if the given object is equal to this token, `false` otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equals(value, token.value) &&
                Objects.equals(element, token.element);
    }

    /**
     * This method returns a hash code value for the token.
     *
     * @return A hash code value for the token.
     */
    @Override
    public int hashCode() {
        return Objects.hash(value, element);
    }
}
