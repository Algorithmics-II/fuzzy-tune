package usb.finalproject.fuzzy.matcher.component;

import usb.finalproject.fuzzy.matcher.domain.Element;
import usb.finalproject.fuzzy.matcher.domain.ElementClassification;
import usb.finalproject.fuzzy.matcher.domain.MatchType;
import usb.finalproject.fuzzy.matcher.domain.Token;
import usb.finalproject.fuzzy.matcher.exception.MatchException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * The `TokenRepo` class is used for storing and retrieving tokens and elements.
 */
public class TokenRepo {

    private Map<ElementClassification, Repo> repoMap;

    /**
     * This is the constructor of the `TokenRepo` class.
     * It initializes a new instance of the `TokenRepo` class with an empty repository map.
     */
    public TokenRepo() {
        this.repoMap = new ConcurrentHashMap<>();
    }

    /**
     * This method adds a token to the repository.
     * It first gets the element classification of the token and retrieves the corresponding repository from the repository map.
     * If the repository does not exist, it creates a new repository with the match type of the element of the token and adds it to the repository map.
     * Then, it adds the token and its element to the repository.
     *
     * @param token The token to be added.
     */
    public void put(Token token) {

        ElementClassification elementClassification = token.getElement().getElementClassification();
        Repo repo = repoMap.get(elementClassification);

        if (repo == null) {
            repo = new Repo(token.getElement().getMatchType());
            repoMap.put(elementClassification, repo);
        }
        repo.put(token, token.getElement());
    }

    /**
     * This method retrieves a set of elements associated with a token from the repository.
     * It first gets the element classification of the token and retrieves the corresponding repository from the repository map.
     * If the repository exists, it returns the set of elements associated with the token in the repository.
     * Otherwise, it returns null.
     *
     * @param token The token.
     * @return A set of elements associated with the token, or null if the repository does not exist.
     */
    public Set<Element> get(Token token) {
        Repo repo = repoMap.get(token.getElement().getElementClassification());
        if (repo != null) {
            return repo.get(token);
        }
        return null;
    }

    /**
     * The `Repo` class is a private inner class of the `TokenRepo` class.
     * It represents a repository for tokens and elements.
     */
    private class Repo {

        MatchType matchType;

        Map<Object, Set<Element>> tokenElementSet;

        TreeSet<Object> tokenBinaryTree;

        private final Double AGE_PCT_OF = 10D;
        private final Double DATE_PCT_OF = 15777e7D; // 5 years of range

        /**
         * This is the constructor of the `Repo` class.
         * It initializes a new instance of the `Repo` class with the specified match type.
         *
         * @param matchType The match type.
         */
        Repo(MatchType matchType) {
            this.matchType = matchType;
            switch (matchType) {
                case NEAREST_NEIGHBORS:
                    tokenBinaryTree = new TreeSet<>();
                case EQUALITY:
                    tokenElementSet = new ConcurrentHashMap<>();
            }
        }

        /**
         * This method adds a token and an element to the repository.
         * If the match type is `NEAREST_NEIGHBORS`, it adds the value of the token to the binary tree.
         * If the match type is `EQUALITY`, it adds the element to the set of elements associated with the value of the token in the map.
         *
         * @param token   The token to be added.
         * @param element The element to be added.
         */
        void put(Token token, Element element) {
            switch (matchType) {
                case NEAREST_NEIGHBORS:
                    tokenBinaryTree.add(token.getValue());
                case EQUALITY:
                    Set<Element> elements = tokenElementSet.getOrDefault(token.getValue(), new HashSet<>());
                    elements.add(element);
                    tokenElementSet.put(token.getValue(), elements);
            }
        }

        /**
         * This method gets a set of elements associated with a token from the repository.
         * If the match type is `EQUALITY`, it returns the set of elements associated with the value of the token in the map.
         * If the match type is `NEAREST_NEIGHBORS`, it returns a set of elements associated with the values in the binary tree that are within the neighborhood range of the value of the token.
         *
         * @param token The token.
         * @return A set of elements associated with the token.
         */
        Set<Element> get(Token token) {
            switch (matchType) {
                case EQUALITY:
                    return tokenElementSet.get(token.getValue());
                case NEAREST_NEIGHBORS:
                    TokenRange tokenRange;
                    switch (token.getElement().getElementClassification().getElementType()) {
                        case AGE:
                            tokenRange = new TokenRange(token, token.getElement().getNeighborhoodRange(), AGE_PCT_OF);
                            break;
                        case DATE:
                            tokenRange = new TokenRange(token, token.getElement().getNeighborhoodRange(), DATE_PCT_OF);
                            break;
                        default:
                            tokenRange = new TokenRange(token, token.getElement().getNeighborhoodRange());
                    }
                    return tokenBinaryTree.subSet(tokenRange.lower, true, tokenRange.higher, true)
                            .stream()
                            .flatMap(val -> tokenElementSet.get(val).stream()).collect(Collectors.toSet());

            }
            return null;
        }
    }

    /**
     * The `TokenRange` class is a private inner class of the `Repo` class.
     * It represents a range of values for a token.
     */
    private class TokenRange {

        private final Object lower;
        private final Object higher;

        /**
         * This is the constructor of the `TokenRange` class.
         * It initializes a new instance of the `TokenRange` class with the specified token, percentage, and percentage of value.
         *
         * @param token The token.
         * @param pct   The percentage.
         * @param pctOf The percentage of value.
         */
        TokenRange(Token token, double pct, Double pctOf) {
            Object value = token.getValue();
            if (value instanceof Double) {
                this.lower = getLower((Double) value, pct, pctOf).doubleValue();
                this.higher = getHigher((Double) value, pct, pctOf).doubleValue();
            } else if (value instanceof Integer) {
                this.lower = getLower((Integer) value, pct, pctOf).intValue();
                this.higher = getHigher((Integer) value, pct, pctOf).intValue();
            } else if (value instanceof Long) {
                this.lower = getLower((Long) value, pct, pctOf).longValue();
                this.higher = getHigher((Long) value, pct, pctOf).longValue();
            } else if (value instanceof Float) {
                this.lower = getLower((Float) value, pct, pctOf).floatValue();
                this.higher = getHigher((Float) value, pct, pctOf).floatValue();
            } else if (value instanceof Date) {
                this.lower = new Date(getLower(((Date) value).getTime(), pct, pctOf).longValue());
                this.higher = new Date(getHigher(((Date) value).getTime(), pct, pctOf).longValue());
            } else {
                throw new MatchException("Data Type not supported");
            }
        }

        /**
         * This is another constructor of the `TokenRange` class.
         * It initializes a new instance of the `TokenRange` class with the specified token and percentage.
         *
         * @param token The token.
         * @param pct   The percentage.
         */
        TokenRange(Token token, double pct) {
            this(token, pct, null);
        }

        /**
         * This method calculates the lower bound of the range for a given number, percentage, and percentage of value.
         *
         * @param number The number.
         * @param pct    The percentage.
         * @param pctOf  The percentage of value.
         * @return The lower bound of the range.
         */
        private Number getLower(Number number, double pct, Double pctOf) {
            Double dnum = number.doubleValue();
            Double dPctOf = pctOf != null ? pctOf : dnum;
            Double pctVal = Math.abs(dPctOf * (1.0 - pct));
            return dnum - pctVal;
        }

        /**
         * This method calculates the higher bound of the range for a given number, percentage, and percentage of value.
         *
         * @param number The number.
         * @param pct    The percentage.
         * @param pctOf  The percentage of value.
         * @return The higher bound of the range.
         */
        private Number getHigher(Number number, double pct, Double pctOf) {
            Double dnum = number.doubleValue();
            Double dPctOf = pctOf != null ? pctOf : dnum;
            Double pctVal = Math.abs(dPctOf * (1.0 - pct));
            return dnum + pctVal;
        }

    }

}
