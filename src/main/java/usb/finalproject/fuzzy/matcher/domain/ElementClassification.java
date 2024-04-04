package usb.finalproject.fuzzy.matcher.domain;

import java.util.Objects;

/**
 * The `ElementClassification` class defines how each element is classified using `ElementType` and variance.
 * `ElementType` is an enum which gives a template on all the functions that should be applied during match.
 * Variance is a user-defined String, that allows multiple `ElementType` to be defined in a Document.
 */
public class ElementClassification {

    private ElementType elementType;
    private String variance;

    /**
     * This is the constructor of the `ElementClassification` class.
     *
     * @param elementType The type of the element.
     * @param variance    The variance of the element.
     */
    public ElementClassification(ElementType elementType, String variance) {
        this.elementType = elementType;
        this.variance = variance;
    }

    /**
     * This method returns the type of the element.
     *
     * @return The type of the element.
     */
    public ElementType getElementType() {
        return elementType;
    }

    /**
     * This method returns the variance of the element.
     *
     * @return The variance of the element.
     */
    public String getVariance() {
        return variance;
    }

    /**
     * This method checks if the given object is equal to this element classification.
     *
     * @param o The object to be compared.
     * @return `true` if the given object is equal to this element classification, `false` otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementClassification that = (ElementClassification) o;
        return elementType == that.elementType &&
                Objects.equals(variance, that.variance);
    }

    /**
     * This method returns a hash code value for the element classification.
     *
     * @return A hash code value for the element classification.
     */
    @Override
    public int hashCode() {
        return Objects.hash(elementType, variance);
    }
}
