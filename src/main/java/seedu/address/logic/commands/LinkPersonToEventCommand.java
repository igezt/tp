package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

public class LinkPersonToEventCommand extends Command {

    public static final String MESSAGE_USAGE = "";
    private Index index;
    private String personName;

    /**
     * Creates an LinkPersonToEventCommand to link the specified {@code Person} to {@code Event}
     * @param index index of the event being linked to.
     * @param personName name of person that the event is being linked to.
     */
    public LinkPersonToEventCommand(Index index, String personName) {
        this.index = index;
        this.personName = personName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Event event = model.getEvent(this.index);
        return null;
    }
}
