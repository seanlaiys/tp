package seedu.address.model.tutorial;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DayOfWeek;

/**
 * Represents the day of week which a Tutorial is on in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDay(String)}
 */
public class Day {
    public static final String MESSAGE_CONSTRAINTS = "Days should be provided as a number which is their position in "
            + "the week (e.g. Monday should be provided as 1).";
    public static final String VALIDATION_REGEX = "^[1-7]";
    public final String day;

    /**
     * Constructs a {@code Day}.
     *
     * @param value represents the position of the day in the week.
     */
    public Day(String value) {
        requireNonNull(value);
        checkArgument(isValidDay(value), MESSAGE_CONSTRAINTS);
        day = DayOfWeek.of(Integer.parseInt(value)).toString();
    }

    /**
     * Returns true if given String represents an integer between 1 and 7.
     */
    public boolean isValidDay(String value) {
        return value.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return day;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Day // instanceof handles nulls
                && day.equals(((Day) other).day)); // state check
    }

    @Override
    public int hashCode() {
        return day.hashCode();
    }
}
