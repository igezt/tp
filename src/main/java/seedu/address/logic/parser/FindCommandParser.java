package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.editpersoncommandsparser.PersonCommandParser;
import seedu.address.logic.parser.editpersoncommandsparser.PersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser extends PersonCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        // String[] nameKeywords = trimmedArgs.split("\\s+");

        PersonDescriptor descriptor = this.looseParseForTags(args);

        return new FindCommand(new PersonContainsKeywordsPredicate(descriptor));
    }

    @Override
    public Optional<Index> parseIndex(String index) throws ParseException {
        return Optional.empty();
    }

    @Override
    protected String getMessageUsage() {
        return FindCommand.MESSAGE_USAGE;
    }
}
