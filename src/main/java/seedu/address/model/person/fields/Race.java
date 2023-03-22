package seedu.address.model.person.fields;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents a Person's race in the address book.
 */
public class Race {

    public static final String MESSAGE_CONSTRAINTS = "Race should only contain alphabets and spaces";
    public static final String VALIDATION_REGEX = "^[A-Za-z\\s]+$$";
    public final String race;

    /**
     * Constructs a {@code Race}.
     *
     * @param race A valid race.
     */
    public Race(String race) {
        requireNonNull(race);
        //checkArgument(isValidRace(race), MESSAGE_CONSTRAINTS);
        this.race = race;
    }

    /**
     * Returns if a given string is a valid race.
     */
    public static boolean isValidRace(String trimmedRace) {
        if (Objects.equals(trimmedRace, "")) {
            return true;
        }
        return trimmedRace.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a name is a substring of the characters of another name.
     */
    public boolean contains(Race race) {
        return this.race.toUpperCase().contains(race.race.toUpperCase());
    }

    @Override
    public String toString() {
        return this.race;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Race // instanceof handles nulls
                && this.race.equals(((Race) other).race)); // state check
    }

    @Override
    public int hashCode() {
        return this.race.hashCode();
    }

}
