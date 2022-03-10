package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_HIDE_ALL_TUTORIALS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS_IN_ADDRESSBOOK;

import seedu.address.model.Model;

/**
 * Lists all students in the address book to the user.
 */
public class ListStudentCommand extends Command {

    public static final String COMMAND_WORD = "list_student";

    public static final String MESSAGE_SUCCESS = "Listed all students";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTutorialList(PREDICATE_HIDE_ALL_TUTORIALS);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_STUDENTS_IN_ADDRESSBOOK);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
