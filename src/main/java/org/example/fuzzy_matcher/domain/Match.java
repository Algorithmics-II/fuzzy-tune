package org.example.fuzzy_matcher.domain;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * A generic class to hold the match between 2 objects and the score of the match result.
 * A match between similar Token, Element or Document is represented by this class.
 * <p>
 * The "data" and "matchedWith" object holds the 2 records that matched. And "score" represents the match for these 2 objects.
 * "childScore" is used by ScoringFunction to aggregate and calculate the "score" value
 * <p>
 * The `Match` class is a generic class that represents a match between two `Matchable` objects.
 * It holds the two matched records and the score of the match result.
 *
 * @param <T> This is the type parameter which extends `Matchable`.
 */
public class Match<T extends Matchable> {

    private T data; // The first matched record.
    private T matchedWith; // The second matched record.
    private Score score; // The score of the match result.

    /**
     * This is the constructor of the `Match` class.
     *
     * @param t           The first matched record.
     * @param matchedWith The second matched record.
     */
    public Match(T t, T matchedWith) {
        this.data = t;
        this.matchedWith = matchedWith;
    }

    /**
     * This is another constructor of the `Match` class that also takes a list of child scores.
     *
     * @param t           The first matched record.
     * @param matchedWith The second matched record.
     * @param childScores The list of child scores.
     */
    public Match(T t, T matchedWith, List<Score> childScores) {
        this(t, matchedWith);
        List<Score> maxDistinctChildScores = getMaxDistinctScores(childScores);
        setScore(maxDistinctChildScores);
    }

    /**
     * This is another constructor of the `Match` class that also takes a result.
     *
     * @param t           The first matched record.
     * @param matchedWith The second matched record.
     * @param result      The result of the match.
     */
    public Match(T t, T matchedWith, double result) {
        this(t, matchedWith);
        this.score = new Score(result, this);
    }

    /**
     * This method returns the first matched record.
     *
     * @return The first matched record.
     */
    public T getData() {
        return this.data;
    }

    /**
     * This method returns the second matched record.
     *
     * @return The second matched record.
     */
    public T getMatchedWith() {
        return matchedWith;
    }

    /**
     * This method returns the result of the match.
     *
     * @return The result of the match.
     */
    public double getResult() {
        return this.score.getResult();
    }

    /**
     * This method returns the score of the match result.
     *
     * @return The score of the match result.
     */
    public Score getScore() {
        return this.score;
    }

    /**
     * This method sets the score of the match result.
     *
     * @param childScores The list of child scores.
     */
    public void setScore(List<Score> childScores) {
        if (this.score == null) {
            this.score = this.data.getScoringFunction().apply(this, childScores);
        }
    }

    /**
     * This method returns the maximum distinct scores from a list of scores.
     *
     * @param scoreList The list of scores.
     * @return The list of maximum distinct scores.
     */
    private List<Score> getMaxDistinctScores(List<Score> scoreList) {
        Map<Matchable, Optional<Score>> map = scoreList.stream()
                .collect(Collectors.groupingBy(s -> s.getMatch().getData(),
                        Collectors.maxBy(Comparator.comparingDouble(Score::getResult))));

        return map.entrySet().stream().map(entry -> entry.getValue().get()).collect(Collectors.toList());
    }

    /**
     * This method returns the weight of the first matched record.
     *
     * @return The weight of the first matched record.
     */
    public double getWeight() {
        return getData().getWeight();
    }

    /**
     * This method returns a string representation of the `Match` object.
     *
     * @return A string representation of the `Match` object.
     */
    @Override
    public String toString() {
        return "Match{" +
                "data=" + data +
                ", matchedWith=" + matchedWith +
                ", score=" + score.getResult() +
                '}';
    }

    /**
     * This method checks if the given object is equal to this match.
     *
     * @param o The object to be compared.
     * @return `true` if the given object is equal to this match, `false` otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match<?> match = (Match<?>) o;
        return Objects.equals(data, match.data) &&
                Objects.equals(matchedWith, match.matchedWith);
    }

    /**
     * This method returns a hash code value for the match.
     *
     * @return A hash code value for the match.
     */
    @Override
    public int hashCode() {
        return Objects.hash(data, matchedWith);
    }
}
