package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_HIDE_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTORIALS;

import seedu.address.model.Model;

/**
 * Lists all tutorials in the address book to the user.
 */
public class ListTutorialCommand extends Command {

    public static final String COMMAND_WORD = "list_class";

    public static final String MESSAGE_SUCCESS = "Listed all classes";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTutorialList(PREDICATE_SHOW_ALL_TUTORIALS);
        model.updateFilteredPersonList(PREDICATE_HIDE_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS, true);
    }
}
