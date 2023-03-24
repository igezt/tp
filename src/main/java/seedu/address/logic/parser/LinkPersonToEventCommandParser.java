package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LinkPersonToEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class LinkPersonToEventCommandParser implements Parser<LinkPersonToEventCommand> {

    /**
     * Parses user input and creates a new {@code TabCommand}.
     */
    @Override
    public LinkPersonToEventCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkPersonToEventCommand.MESSAGE_USAGE));
        }
        String[] args = trimmedArgs.split("\\s+");
        Index index = ParserUtil.parseIndex(args[0]);
        String personName = args[1];
        return new LinkPersonToEventCommand(index, personName);
    }
}
