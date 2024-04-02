package org.example.fuzzy_matcher.domain;

import org.example.fuzzy_matcher.function.ScoringFunction;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * The primary object for matching. The required attribute is a unique key and elements
 * <p>
 * Configurable attributes
 * <ul>
 * <li>elements - A set of Element object to match against</li>
 * <li>threshold - Value above which documents are considered a match, default 0.5</li>
 * </ul>
 */
public class Document implements Matchable {
    /**
     * This is the constructor of the `Document` class.
     *
     * @param key       The key of the document.
     * @param elements  The set of elements in the document.
     * @param threshold The threshold for the document.
     */
    private Document(String key, Set<Element> elements, double threshold) {
        this.key = key;
        this.elements = elements;
        this.threshold = threshold;
    }

    private String key;
    private Set<Element> elements;
    private Set<Element> preProcessedElement;
    private double threshold;
    private Boolean source;

    private static final BiFunction<Match, List<Score>, Score> DEFAULT_DOCUMENT_SCORING = ScoringFunction.getExponentialWeightedAverageScore();

    /**
     * This method returns the key of the document.
     *
     * @return The key of the document.
     */
    public String getKey() {
        return key;
    }

    /**
     * This method returns the set of elements in the document.
     *
     * @return The set of elements in the document.
     */
    public Set<Element> getElements() {
        return elements;
    }

    /**
     * This method returns the set of preprocessed elements in the document.
     *
     * @return The set of preprocessed elements in the document.
     */
    public Set<Element> getPreProcessedElement() {
        if (this.preProcessedElement == null) {
            this.preProcessedElement = getDistinctNonEmptyElements().collect(Collectors.toSet());
        }
        return preProcessedElement;
    }

    /**
     * This method returns the threshold for the document.
     *
     * @return The threshold for the document.
     */
    public double getThreshold() {
        return threshold;
    }

    /**
     * This method returns a stream of distinct elements in the document.
     *
     * @return A stream of distinct elements in the document.
     */
    public Stream<Element> getDistinctElements() {
        return this.elements.stream()
                .filter(distinctByKey(Element::getPreprocessedValueWithType));
    }

    /**
     * This method returns a stream of distinct non-empty elements in the document.
     *
     * @return A stream of distinct non-empty elements in the document.
     */
    public Stream<Element> getDistinctNonEmptyElements() {
        return getDistinctElements()
                .filter(m -> {
                    if (m.getPreProcessedValue() instanceof String) {
                        return !StringUtils.isEmpty(m.getPreProcessedValue().toString());
                    } else
                        return m.getPreProcessedValue() != null;
                });
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    /**
     * This method returns the count of child objects of this document that match with the other `Matchable` object.
     *
     * @param other The other `Matchable` object to compare with.
     * @return The count of matching child objects.
     */
    @Override
    public long getChildCount(Matchable other) {
        if (other instanceof Document) {
            Document o = (Document) other;
            List<ElementClassification> childrenType = this.getPreProcessedElement().stream()
                    .map(Element::getElementClassification).collect(Collectors.toList());
            List<ElementClassification> oChildrenType = o.getPreProcessedElement().stream()
                    .map(Element::getElementClassification).collect(Collectors.toList());
            return CollectionUtils.union(childrenType, oChildrenType).size();
        }
        return 0;

    }

    /**
     * This method returns the count of child objects of this document that do not match with the other `Matchable` object.
     *
     * @param other The other `Matchable` object to compare with.
     * @return The count of unmatched child objects.
     */
    @Override
    public long getUnmatchedChildCount(Matchable other) {
        if (other instanceof Document) {
            Document o = (Document) other;
            List<ElementClassification> childrenType = this.getPreProcessedElement().stream()
                    .map(Element::getElementClassification).collect(Collectors.toList());
            List<ElementClassification> oChildrenType = o.getPreProcessedElement().stream()
                    .map(Element::getElementClassification).collect(Collectors.toList());
            return CollectionUtils.disjunction(childrenType, oChildrenType).size();
        }
        return 0;
    }

    /**
     * This method returns the scoring function for the document.
     *
     * @return The scoring function for the document.
     */
    @Override
    public BiFunction<Match, List<Score>, Score> getScoringFunction() {
        return DEFAULT_DOCUMENT_SCORING;
    }

    /**
     * This method returns the weight of the document.
     *
     * @return The weight of the document.
     */
    @Override
    public double getWeight() {
        return 1.0;
    }

    /**
     * This method returns whether the document is a source.
     *
     * @return `true` if the document is a source, `false` otherwise.
     */
    public Boolean isSource() {
        return source;
    }

    public void setSource(Boolean source) {
        this.source = source;
    }

    /**
     * The `Builder` class is a static inner class of the `Document` class.
     * It follows the Builder design pattern and is used to construct a `Document` object.
     */
    public static class Builder {

        private String key; // The key of the document.
        private Set<Element> elements; // The set of elements in the document.
        private double threshold = 0.5; // The threshold for the document.

        /**
         * This is the constructor of the `Builder` class.
         *
         * @param key The key of the document.
         */
        public Builder(String key) {
            this.key = key;
        }

        /**
         * This method sets the threshold for the document.
         *
         * @param threshold The threshold for the document.
         * @return The current Builder object.
         */
        public Builder setThreshold(double threshold) {
            this.threshold = threshold;
            return this;
        }

        /**
         * This method adds an element to the set of elements in the document.
         *
         * @param element The element to be added.
         * @return The current Builder object.
         */
        public Builder addElement(Element element) {
            if (this.elements == null || this.elements.isEmpty()) {
                this.elements = new HashSet<>();
            }
            this.elements.add(element);
            return this;
        }

        /**
         * This method creates a new `Document` object using the current state of the Builder object.
         *
         * @return A new `Document` object.
         */
        public Document createDocument() {
            Document doc = new Document(key, elements, threshold);
            doc.elements.stream().forEach(element -> element.setDocument(doc));
            return doc;
        }
    }

    @Override
    public String toString() {
        return "{" + getOrderedElements(elements) + "}";
    }

    /**
     * This method returns a list of elements in the document ordered by their element type.
     *
     * @param elements The set of elements in the document.
     * @return A list of elements in the document ordered by their element type.
     */
    public List<Element> getOrderedElements(Set<Element> elements) {
        return elements.stream().sorted(Comparator.comparing(ele -> ele.getElementClassification().getElementType()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        return key.equals(document.key);

    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }
}
