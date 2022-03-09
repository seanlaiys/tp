package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;

import javafx.collections.transformation.FilteredList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tutorial.Tutorial;

public class AddClassCommand extends Command {
    public static final String COMMAND_WORD = "add_class";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a class to the address book. "
            + "Parameters: "
            + PREFIX_TUTORIALNAME + "TUTORIAL NAME "
            + PREFIX_VENUE + "VENUE "
            + PREFIX_DAY + "DAY "
            + PREFIX_TIME + "TIME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TUTORIALNAME + "T04 "
            + PREFIX_VENUE + "LT13 "
            + PREFIX_DAY + "Monday "
            + PREFIX_TIME + "1300 ";

    public static final String MESSAGE_SUCCESS = "New class added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLASS = "This class already exists in the camNUS";

    private final Tutorial toAdd;

    /**
     * Creates an AddClassCommand to add the specified {@code Tutorial}
     */
    public AddClassCommand(Tutorial tutorial) {
        requireNonNull(tutorial);
        toAdd = tutorial;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        toAdd.setStudentsList((FilteredList<Person>) model.getFilteredPersonList());

        if (model.hasTutorial(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLASS);
        }

        model.addTutorial(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddClassCommand) other).toAdd));
    }
}
