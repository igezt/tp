package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.logic.parser.editpersoncommandsparser.PersonDescriptor;


/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {
    private final PersonDescriptor descriptor;

    public PersonContainsKeywordsPredicate(PersonDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public boolean test(Person person) {
        return person.contains(this.descriptor);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsKeywordsPredicate // instanceof handles nulls
                && descriptor.equals(((PersonContainsKeywordsPredicate) other).descriptor)); // state check
    }

}
