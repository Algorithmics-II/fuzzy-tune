package usb.finalproject.fuzzy.matcher.domain;

/**
 * The `Score` class represents the result of matching two Documents, Elements, or Tokens.
 * It also holds a reference to a `Match` object, which is used to aggregate the score with a `ScoringFunction`.
 */
public class Score {
    private double result;
    private Match match;

    /**
     * This is the constructor of the `Score` class.
     *
     * @param result The result of the match.
     * @param match  The reference to the `Match` object.
     */
    public Score(double result, Match match) {
        this.result = result;
        this.match = match;
    }

    /**
     * This method returns the result of the match.
     *
     * @return The result of the match.
     */
    public double getResult() {
        return result;
    }

    /**
     * This method returns the reference to the `Match` object.
     *
     * @return The reference to the `Match` object.
     */
    public Match getMatch() {
        return match;
    }

    /**
     * This method returns a string representation of the `Score` object.
     *
     * @return A string representation of the `Score` object.
     */
    @Override
    public String toString() {
        return "Score{" +
                "result=" + result +
                ", match=" + match +
                '}';
    }
}
