package seedu.address.model.tutorial;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tutorial.exceptions.DuplicateTutorialException;

/**
 * A list of Tutorials that enforces the uniqueness between its elements and does not allow nulls.
 * A Tutorial is considered unique by comparing using {@code Tutorial#isSameTutorial}.
 * Adding and updating a Tutorial uses {@code Tutorial#isSameTutorial(Tutorial)} to ensure
 * that the Tutorial being added is unique in terms of identity.
 * However, the removal of a Tutorial uses {@code Tutorial#equals(Object)} to ensure that the
 * Tutorial with exactly the same fields is removed.
 * TODO: implement the remove and setter methods.
 *
 * @see Tutorial#isSameTutorial(Tutorial)
 */
public class UniqueTutorialList {
    private final ObservableList<Tutorial> internalList = FXCollections.observableArrayList();
    private final ObservableList<Tutorial> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tutorial as the argument,
     * uses {@code Tutorial#isSameTutorial(Tutorial)}
     */
    public boolean contains(Tutorial toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTutorial);
    }

    /**
     * Adds a tutorial to the list.
     * The tutorial must not already exist in the list.
     */
    public void add(Tutorial toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTutorialException();
        }
        internalList.add(toAdd);
    }

    /**
     * Returns the backing list as an unmodifiable list.
     */
    public ObservableList<Tutorial> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Replaces the contents of this list with {@code tutorials},
     * which must not contain duplicate tutorials.
     */
    public void setTutorials(List<Tutorial> tutorials) {
        requireAllNonNull(tutorials);
        if (!tutorialsAreUnique(tutorials)) {
            throw new DuplicateTutorialException();
        }
        internalList.setAll(tutorials);
    }

    private boolean tutorialsAreUnique(List<Tutorial> tutorials) {
        for (int i = 0; i < tutorials.size() - 1; i++) {
            for (int j = i + 1; j < tutorials.size(); j++) {
                if (tutorials.get(i).isSameTutorial(tutorials.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
