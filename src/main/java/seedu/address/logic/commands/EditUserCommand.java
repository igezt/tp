package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Optional;
import java.util.Set;

import javafx.beans.property.ReadOnlyObjectProperty;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.editpersoncommandsparser.EditPersonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.event.UniqueEventList;
import seedu.address.model.person.fields.Address;
import seedu.address.model.person.fields.CommunicationChannel;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Favorite;
import seedu.address.model.person.fields.Gender;
import seedu.address.model.person.fields.Major;
import seedu.address.model.person.fields.Modules;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Race;
import seedu.address.model.person.fields.subfields.Tag;
import seedu.address.model.user.User;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditUserCommand extends Command {

    public static final String COMMAND_WORD = "edituser";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited User: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the user. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters:"
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    private final EditPersonDescriptor editUserDescriptor;

    /**
     * @param editPersonDescriptor details to edit the person with
     */
    public EditUserCommand(EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);
        this.editUserDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ReadOnlyObjectProperty<User> userWrapper = model.getUserData().getData();

        User user = userWrapper.getValue();
        User editedUser = createEditedUser(user, editUserDescriptor);


        model.setUser(editedUser);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedUser));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static User createEditedUser(User user, EditPersonDescriptor editPersonDescriptor) {
        assert user != null;

        Name updatedName = editPersonDescriptor.getName().orElse(user.getName());

        Optional<Phone> newPhone = editPersonDescriptor.getPhone();
        Optional<Phone> updatedPhone = newPhone.isEmpty() ? user.getPhone() : newPhone;

        Email updatedEmail = editPersonDescriptor.getEmail().orElse(user.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(user.getAddress());
        Gender updatedGender = editPersonDescriptor.getGender().orElse(user.getGender());
        Major updatedMajor = editPersonDescriptor.getMajor().orElse(user.getMajor());
        Modules updatedModules = editPersonDescriptor.getModules().orElse(user.getModules());
        Race updatedRace = editPersonDescriptor.getRace().orElse(user.getRace());
        CommunicationChannel updatedComms = editPersonDescriptor.getComms().orElse(user.getComms());
        Favorite currentFavorite = user.getIsFavorite();
        UniqueEventList currentEvents = user.getEvents();

        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(user.getTags());

        return new User(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedGender, updatedMajor,
                updatedModules, updatedRace, updatedTags, updatedComms, currentFavorite, currentEvents);
    }

    public EditPersonDescriptor getEditUserDescriptor() {
        return this.editUserDescriptor;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditUserCommand)) {
            return false;
        }

        // state check
        EditUserCommand e = (EditUserCommand) other;
        return this.getEditUserDescriptor().equals(e.getEditUserDescriptor());
    }

}
