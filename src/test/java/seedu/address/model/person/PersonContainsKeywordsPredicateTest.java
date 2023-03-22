package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

public class PersonContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateName = "first second";
        String secondPredicateMajor = "Computer Science";
        PersonContainsKeywordsPredicate firstPredicate = new PersonContainsKeywordsPredicate(
                new EditPersonDescriptorBuilder().withName(firstPredicateKeyword).build());
        PersonContainsKeywordsPredicate secondPredicate = new PersonContainsKeywordsPredicate(
                new EditPersonDescriptorBuilder().withName(secondPredicateName)
                        .withMajor(secondPredicateMajor).build());

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonContainsKeywordsPredicate firstPredicateCopy = new PersonContainsKeywordsPredicate(
                new EditPersonDescriptorBuilder().withName(firstPredicateKeyword).build());
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {

        // Zero keywords
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(
                new EditPersonDescriptorBuilder().withName("").build());
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));

        // One keyword
        predicate = new PersonContainsKeywordsPredicate(
                new EditPersonDescriptorBuilder().withName("Alice").build());
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new PersonContainsKeywordsPredicate(
                new EditPersonDescriptorBuilder().withName("Alice Bob").build());
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new PersonContainsKeywordsPredicate(
                new EditPersonDescriptorBuilder().withName("Alice").build());
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new PersonContainsKeywordsPredicate(
                new EditPersonDescriptorBuilder().withName("aLiCe boB").build());
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));



    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {


        // Non-matching keyword
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(
                new EditPersonDescriptorBuilder().withName("Carol").build());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match name and email, but does not match phone
        predicate = new PersonContainsKeywordsPredicate(
                new EditPersonDescriptorBuilder().withName("Alic").withEmail("alice@gmail.com")
                        .withPhone("15").build());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").build()));
    }
}
