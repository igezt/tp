package seedu.address.logic.parser.editpersoncommandsparser;

/**
 * Stricter version of PersonDescriptor to use for Edit purposes.
 */
public class EditPersonDescriptor extends PersonDescriptor {

    /**
     * Creates a copy of EditPersonDescriptor of the descriptor toCopy
     */
    public EditPersonDescriptor(PersonDescriptor toCopy) {
        super(toCopy);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPersonDescriptor)) {
            return false;
        }

        // state check
        EditPersonDescriptor e = (EditPersonDescriptor) other;

        return getName().equals(e.getName())
                && getPhone().equals(e.getPhone())
                && getEmail().equals(e.getEmail())
                && getAddress().equals(e.getAddress())
                && getTags().equals(e.getTags())
                && getGender().equals(e.getGender())
                && getMajor().equals(e.getMajor())
                && getRace().equals(e.getRace())
                && getModules().equals(e.getModules())
                && getComms().equals(e.getComms());
    }
}
