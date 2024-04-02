package usb.finalproject.fuzzy.matcher.domain;

import java.util.List;
import java.util.function.BiFunction;

/**
 * The `Matchable` interface is implemented by `Document`, `Element`, and `Token` classes.
 * This interface enables matching and scoring these objects.
 */
public interface Matchable {

    /**
     * This method returns the count of child objects of this object that match with the other `Matchable` object.
     *
     * @param other The other `Matchable` object to compare with.
     * @return The count of matching child objects.
     */
    public long getChildCount(Matchable other);

    /**
     * This method returns the scoring function used to calculate the score of a match.
     * The scoring function is a `BiFunction` that takes a `Match` object and a list of `Score` objects, and returns a `Score` object.
     *
     * @return The scoring function.
     */
    public BiFunction<Match, List<Score>, Score> getScoringFunction();

    /**
     * This method returns the weight of this `Matchable` object.
     * The weight is used in the scoring function to calculate the score of a match.
     *
     * @return The weight of this `Matchable` object.
     */
    public double getWeight();

    /**
     * This method returns the count of child objects of this object that do not match with the other `Matchable` object.
     *
     * @param other The other `Matchable` object to compare with.
     * @return The count of unmatched child objects.
     */
    public long getUnmatchedChildCount(Matchable other);
}
