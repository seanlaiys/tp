package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.model.tutorial.TutorialNameContainsKeywordsPredicate;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNAME;

public class DeleteClassCommand extends Command {

    public static final String COMMAND_WORD = "delete_class";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the tutorial identified by the index number used in the displayed tutorial list,"
            + "or deletes the tutorial identified by tutorial name.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + PREFIX_TUTORIALNAME + "TUTORIAL NAME \n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TUTORIAL_SUCCESS = "Deleted Tutorial: %1$s";

    private final Index targetIndex;
    private final TutorialName tutorialName;
    private final boolean isDeleteByTutorialName;

    public DeleteClassCommand(Index targetIndex, TutorialName tutorialName, boolean isDeleteByTutorialName) {
        this.targetIndex = targetIndex;
        this.tutorialName = tutorialName;
        this.isDeleteByTutorialName = isDeleteByTutorialName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tutorial> lastShownList = model.getFilteredTutorialList();

        if (!isDeleteByTutorialName) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TUTORIAL_DISPLAYED_INDEX);
            }

            Tutorial tutorialToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deleteTutorial(tutorialToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_TUTORIAL_SUCCESS, tutorialToDelete));
        } else {
            //TutorialNameContainsKeywordsPredicate predicate = new TutorialNameContainsKeywordsPredicate();
            return null;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteClassCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteClassCommand) other).targetIndex)); // state check
    }
}

