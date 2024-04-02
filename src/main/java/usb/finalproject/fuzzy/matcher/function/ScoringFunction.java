package usb.finalproject.fuzzy.matcher.function;

import usb.finalproject.fuzzy.matcher.domain.Match;
import usb.finalproject.fuzzy.matcher.domain.Score;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * The `ScoringFunction` interface extends `BiFunction<Match, List<Score>, Score>`.
 * It is a functional interface used to get a score between two `Match` objects.
 */
public interface ScoringFunction extends BiFunction<Match, List<Score>, Score> {

    double EXPONENT = 1.5;
    double EXPONENTIAL_INCREASE_THRESHOLD = 0.9;
    double DEFAULT_UNMATCHED_CHILD_SCORE = 0.5;

    /**
     * For all the childScores in a Match object it calculates the average.
     * To get a balanced average for 2 Match object which do not have same number of childScores.
     * It gives a score of 0.5 for missing children
     *
     * @return the scoring function for Average
     */
    static ScoringFunction getAverageScore() {
        return (match, childScores) -> {
            double numerator = getSumOfResult(childScores) + getUnmatchedChildScore(match);
            double denominator = getChildCount(match);
            return new Score(numerator / denominator, match);
        };
    }

    /**
     * For all the childScores in a Match object it calculates the average.
     * Average is calculated with a total of child scored divided by the child count
     *
     * @return the scoring function for Simple Average
     */
    static ScoringFunction getSimpleAverageScore() {
        return (match, childScores) -> {
            double numerator = getSumOfResult(childScores);
            double denominator = getChildCount(match);
            return new Score(numerator / denominator, match);
        };
    }

    /**
     * Follows the same rules as "getAverageScore" and in addition applies weights to children.
     * It can be used for aggregating Elements to Documents, where weights can be provided at Element level
     *
     * @return the scoring function for WeightedAverage
     */
    static ScoringFunction getWeightedAverageScore() {
        return (match, childScores) -> {
            double numerator = getSumOfWeightedResult(childScores)
                    + getUnmatchedChildScore(match);
            double denominator = getSumOfWeights(childScores)
                    + getChildCount(match)
                    - childScores.size();
            return new Score(numerator / denominator, match);
        };
    }

    /**
     * Follows the same rules as "getAverageScore", and in addition if more than 1 children match above a score of 0.9,
     * it exponentially increases the overall score by using a 1.5 exponent
     *
     * @return the scoring function for ExponentialAverage
     */
    static ScoringFunction getExponentialAverageScore() {
        return (match, childScores) -> {
            List<Score> perfectMatchedElements = getPerfectMatchedElement(childScores);

            if (perfectMatchedElements.size() > 1 && getSumOfResult(perfectMatchedElements) > 1) {
                double numerator = getExponentiallyIncreasedValue(getSumOfResult(perfectMatchedElements))
                        + getSumOfResult(getNonPerfectMatchedElement(childScores))
                        + getUnmatchedChildScore(match);

                double denominator = getExponentiallyIncreasedValue(perfectMatchedElements.size())
                        + getChildCount(match)
                        - perfectMatchedElements.size();
                return new Score(numerator / denominator, match);
            } else
                return getAverageScore().apply(match, childScores);
        };
    }

    /**
     * This is the default scoring used to calculate the Document score by aggregating the child Element scores.
     * This combines the benefits of weights and exponential increase when calculating the average scores.
     *
     * @return the scoring function for ExponentialWeightedAverage
     */
    static ScoringFunction getExponentialWeightedAverageScore() {
        return (match, childScores) -> {
            List<Score> perfectMatchedElements = getPerfectMatchedElement(childScores);

            // Apply Exponent if match elements > 1
            if (perfectMatchedElements.size() > 1 && getSumOfWeightedResult(perfectMatchedElements) > 1) {
                List<Score> notPerfectMachedElements = getNonPerfectMatchedElement(childScores);
                double numerator = getExponentiallyIncreasedValue(getSumOfWeightedResult(perfectMatchedElements))
                        + getSumOfWeightedResult(notPerfectMachedElements)
                        + getUnmatchedChildScore(match);

                double denominator = getExponentiallyIncreasedValue(getSumOfWeights(perfectMatchedElements))
                        + getSumOfWeights(notPerfectMachedElements)
                        + getChildCount(match)
                        - childScores.size();
                return new Score(numerator / denominator, match);
            } else
                return getWeightedAverageScore().apply(match, childScores);
        };
    }

    /**
     * This method calculates the sum of the weighted results of a list of scores.
     *
     * @param childScoreList The list of scores.
     * @return The sum of the weighted results.
     */
    static double getSumOfWeightedResult(List<Score> childScoreList) {
        return (childScoreList.stream().mapToDouble(d -> d.getResult() * d.getMatch().getWeight())).sum();
    }

    /**
     * This method calculates the sum of the results of a list of scores.
     *
     * @param childScoreList The list of scores.
     * @return The sum of the results.
     */
    static double getSumOfResult(List<Score> childScoreList) {
        return (childScoreList.stream().mapToDouble(d -> d.getResult())).sum();
    }

    /**
     * This method calculates the sum of the weights of a list of scores.
     *
     * @param childScoreList The list of scores.
     * @return The sum of the weights.
     */
    static double getSumOfWeights(List<Score> childScoreList) {
        return (childScoreList.stream().mapToDouble(d -> d.getMatch().getWeight())).sum();
    }

    /**
     * This method calculates the exponentially increased value of a given value.
     *
     * @param value The input value.
     * @return The exponentially increased value.
     */
    static double getExponentiallyIncreasedValue(double value) {
        return Math.pow(value, EXPONENT);
    }

    /**
     * This method returns a list of scores from a given list that have a result less than the exponential increase threshold.
     *
     * @param childScoreList The list of scores.
     * @return The list of scores with a result less than the exponential increase threshold.
     */
    static List<Score> getNonPerfectMatchedElement(List<Score> childScoreList) {
        return childScoreList.stream()
                .filter(d -> d.getResult() < EXPONENTIAL_INCREASE_THRESHOLD)
                .collect(Collectors.toList());
    }

    /**
     * This method returns a list of scores from a given list that have a result greater than or equal to the exponential increase threshold.
     *
     * @param childScoreList The list of scores.
     * @return The list of scores with a result greater than or equal to the exponential increase threshold.
     */
    static List<Score> getPerfectMatchedElement(List<Score> childScoreList) {
        return childScoreList.stream()
                .filter(d -> d.getResult() >= EXPONENTIAL_INCREASE_THRESHOLD)
                .collect(Collectors.toList());
    }

    /**
     * This method returns the count of child objects of a given match that match with the other `Matchable` object.
     *
     * @param match The given match.
     * @return The count of matching child objects.
     */
    static double getChildCount(Match match) {
        return (double) match.getData().getChildCount(match.getMatchedWith());
    }

    /**
     * This method calculates the unmatched child score of a given match.
     *
     * @param match The given match.
     * @return The unmatched child score.
     */
    static double getUnmatchedChildScore(Match match) {
        long maxUnmatchedChildCount = match.getData().getUnmatchedChildCount(match.getMatchedWith());
        return DEFAULT_UNMATCHED_CHILD_SCORE * maxUnmatchedChildCount;
    }
}
