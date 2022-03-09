package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteClassCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorial.TutorialName;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class DeleteClassCommandParser implements Parser<DeleteClassCommand>  {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteClassCommand
     * and returns an DeleteClassCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteClassCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TUTORIALNAME);

        Index index;

        if (argMultimap.getValue(PREFIX_NAME).isPresent() && argMultimap.getPreamble() == "") {
            TutorialName tutorialName = ParserUtil.parseTutorialName(argMultimap.getValue(PREFIX_TUTORIALNAME).get());
            return new DeleteClassCommand(null, tutorialName, true);
        } else {
            try {
                index = ParserUtil.parseIndex(args);
                return new DeleteClassCommand(index, null, false);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
            }
        }
    }
}
