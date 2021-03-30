package seedu.address.model.meeting;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a meeting with a client in the ClientBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidMeeting(String, String, String, String)}
 */
public class Meeting {

    public static final String MESSAGE_CONSTRAINTS =
            "Meetings should only contain date, start time, end time, place and they should not be blank";

    public static final String VALIDATION_REGEX = "[^\\s].*";
    public static final String TIME_REGEX = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";

    public final String date;
    public final String start;
    public final String end;
    public final String place;
    public final String meeting;

    /**
     * Constructs a {@code Meeting}.
     *
     * @param date A valid date.
     * @param start A valid start time.
     * @param end A valid end time.
     * @param place A valid place.
     */
    public Meeting(String date, String start, String end, String place) {
        requireAllNonNull(date, start, end, place);
        checkArgument(isValidMeeting(date, start, end, place), MESSAGE_CONSTRAINTS);
        this.date = date;
        this.start = start;
        this.end = end;
        this.place = place;
        this.meeting = date + " " + start + " " + end + " " + place;
    }

    /**
     * Returns a new meeting by parsing the given string.
     */
    public static Meeting newMeeting(String meeting) {
        try {
            return ParserUtil.parseMeeting(meeting);
        } catch (ParseException pe) {
            return null;
        }
    }

    /**
     * Returns true if the given strings can form a valid meeting.
     */
    public static boolean isValidMeeting(String date, String start, String end, String place) {
        return checkDate(date) && start.matches(TIME_REGEX)
                && end.matches(TIME_REGEX) && place.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid meeting.
     */
    public static boolean isValidMeeting(String meeting) {
        Meeting meet = newMeeting(meeting);
        if (meet == null) {
            return false;
        }
        return isValidMeeting(meet.date, meet.start, meet.end, meet.place);
    }

    /**
     * Returns true if a given string is a date in the valid format.
     */
    public static boolean checkDate(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }

    @Override
    public String toString() {
        return meeting;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Meeting // instanceof handles nulls
                && date.equals(((Meeting) other).date) // state check
                && start.equals(((Meeting) other).start) // state check
                && end.equals(((Meeting) other).end) // state check
                && place.equals(((Meeting) other).place)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, start, end, place);
    }

}
