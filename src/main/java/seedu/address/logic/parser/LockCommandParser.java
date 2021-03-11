package seedu.address.logic.parser;


import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.LockCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class LockCommandParser implements Parser<LockCommand> {

    @Override
    public LockCommand parse(String args) throws ParseException {
        String[] passwords = args.split("\\s+");
        if (passwords.length < 1 || passwords.length > 2) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, LockCommand.MESSAGE_USAGE));
        }
        //Length 1: User only provided new password.
        if (passwords.length == 1) {
            return new LockCommand(passwords[0]);
        }

        //Length 2: With old and new password.
        return new LockCommand(passwords[0], passwords[1]);
    }
}
