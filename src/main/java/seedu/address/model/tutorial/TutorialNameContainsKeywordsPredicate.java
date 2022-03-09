package seedu.address.model.tutorial;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class TutorialNameContainsKeywordsPredicate implements Predicate<Tutorial> {
    private final List<String> keywords;

    public TutorialNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Tutorial tutorial) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tutorial.getTutorialName().name, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TutorialNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
