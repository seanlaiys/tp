package seedu.address.model.tutorial;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import javafx.collections.transformation.FilteredList;
import seedu.address.model.assessment.AssessmentResultsList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniqueStudentsInTutorialList;

/**
 * Represents a Class (for a module) in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 * TODO: add methods for making changes to the studentsList or assessmentResultsList
 */
public class Tutorial {
    // Identity fields
    private final TutorialName tutorialName;
    private final Day day;
    private final Time time;
    private final Venue venue;

    // Data fields
    private UniqueStudentsInTutorialList studentsList;
    private final AssessmentResultsList assessmentResultsList;

    /**
     * Every field must be present and not null.
     *
     * @param name the tutorial's name.
     * @param v the venue the tutorial is hosted at.
     * @param d the day of the week the tutorial falls on.
     * @param t the time the tutorial starts.
     */
    public Tutorial(TutorialName name, Venue v, Day d, Time t) {
        requireAllNonNull(name, d, t);
        tutorialName = name;
        venue = v;
        day = d;
        time = t;
        assessmentResultsList = new AssessmentResultsList(name);
    }

    /**
     * Every field must be present and not null.
     *
     * @param name the tutorial's name.
     * @param v the venue the tutorial is hosted at.
     * @param d the day of the week the tutorial falls on.
     * @param t the time the tutorial starts.
     * @param allStudents the allStudents list in the ModelManager.
     */
    public Tutorial(TutorialName name, Venue v, Day d, Time t, FilteredList<Person> allStudents) {
        requireAllNonNull(name, d, t);
        tutorialName = name;
        venue = v;
        day = d;
        time = t;
        studentsList = new UniqueStudentsInTutorialList(allStudents, name);
        assessmentResultsList = new AssessmentResultsList(name);
    }

    public TutorialName getTutorialName() {
        return tutorialName;
    }

    public Day getDay() {
        return day;
    }

    public Time getTime() {
        return time;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setStudentsList(FilteredList<Person> allStudents) {
        studentsList = new UniqueStudentsInTutorialList(allStudents, tutorialName);
    }

    /**
     * Returns true if given object is a Tutorial.
     */
    public static boolean isTutorial(Object object) {
        return object instanceof Tutorial;
    }

    /**
     * Returns true if both tutorials have the same name.
     * This defines a weaker notion of equality between two tutorials (used for adding and editing tutorials).
     */
    public boolean isSameTutorial(Tutorial other) {
        if (this == other) {
            return true;
        }
        return other != null && other.getTutorialName().equals(getTutorialName());
    }

    /**
     * Returns true if both tutorials have the same identity and data fields.
     * This defines a stronger notion of equality between two tutorials (used for deleting tutorials).
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tutorial)) {
            return false;
        }

        Tutorial otherTut = (Tutorial) other;
        return otherTut.getTutorialName().equals(getTutorialName())
                && otherTut.getDay().equals(getDay())
                && otherTut.getTime().equals(getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(tutorialName, day, time);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTutorialName())
                .append("; Venue: ")
                .append(getVenue())
                .append("; Day: ")
                .append(getDay())
                .append("; Time: ")
                .append(getTime());
        return builder.toString();
    }
}
