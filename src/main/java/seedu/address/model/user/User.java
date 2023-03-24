package seedu.address.model.user;


import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.model.event.Event;
import seedu.address.model.event.UniqueEventList;
import seedu.address.model.person.Person;
import seedu.address.model.person.fields.Address;
import seedu.address.model.person.fields.CommunicationChannel;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Faculty;
import seedu.address.model.person.fields.Favorite;
import seedu.address.model.person.fields.Gender;
import seedu.address.model.person.fields.Major;
import seedu.address.model.person.fields.Modules;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Race;
import seedu.address.model.person.fields.subfields.Tag;


/**
 * Store all relevant information about the User.
 */
public class User extends Person {

    //todo: Add event list!
    private final UniqueEventList events;

    /**
     * Every field must be present and not null. This constructor accepts a List of events.
     */
    public User(Name name, Phone phone, Email email, Address address, Gender gender,
                Major major, Modules modules, Race race, Set<Tag> tags, CommunicationChannel comms,
                Favorite favorite, Faculty faculty, List<Event> events) {
        super(name, phone, email, address, gender, major, modules, race, tags, comms, favorite, faculty);
        this.events = new UniqueEventList();
        this.events.setEvents(events);
    }

    /**
     * Every field must be present and not null. This constructor accepts a UniqueEventList of events.
     */
    public User(Name name, Phone phone, Email email, Address address, Gender gender,
                Major major, Modules modules, Race race, Set<Tag> tags, CommunicationChannel comms,
                Favorite favorite, Faculty faculty, UniqueEventList events) {
        super(name, phone, email, address, gender, major, modules, race, tags, comms, favorite, faculty);
        Objects.requireNonNull(events);
        this.events = events;
    }

    /**
     * Constructor for an empty {@code User}.
     */
    public User() {
        super(new Name("Neo"));
        events = new UniqueEventList();
    }

    public boolean hasEvent(Event e) {
        return this.events.contains(e);
    }

    public void addEvent(Event e) {
        this.events.add(e);
    }

    public UniqueEventList getEvents() {
        return this.events;
    }

    public boolean isValidEventIndex(Index index) {
        return this.events.isValidEventIndex(index);
    }
}

