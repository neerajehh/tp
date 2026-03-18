package seedu.classmate;

import seedu.classmate.commands.ByeCommand;
import seedu.classmate.commands.Command;
import seedu.classmate.commands.HelpCommand;
import seedu.classmate.commands.PrereqCommand;
import seedu.classmate.commands.PrintModuleInfoCommand;
import seedu.classmate.commands.QueryModuleAvailabilityCommand;
import seedu.classmate.commands.SpecialisationInfoCommand;
import seedu.classmate.commands.ViewGradReqsCommand;
import seedu.classmate.commands.ViewSpecialisationsCommand;

/**
 * Parses user input and converts it into the corresponding {@code Command} object
 * The input is first trimmed and split into a command word and its arguments.
 * Based on the command word, the corresponding {@code Command} is instantiated.
 */
public class Parser {

    /**
     * Parses the user's input and returns the corresponding {@code Command}.
     *
     * @param input The raw command string entered by the user.
     * @return A {@code Command} object representing the user's input.
     * @throws ClassMateException If the input provided is empty or the command is unknown.
     */
    public static Command parse(String input) {

        String trimmed = input.trim();

        if (trimmed.isEmpty()) {
            throw new ClassMateException("Please enter a non-empty input!");
        }

        String[] arguments = trimmed.split("\\s+", 2);
        String commandWord = arguments[0];
        String args = arguments.length > 1 ? arguments[1] : "";

        switch (commandWord) {
        case "help":
            return new HelpCommand();

        case "bye":
            return new ByeCommand();

        case "viewGradReqs":
            return new ViewGradReqsCommand();

        case "viewPrereqs":
            return new PrereqCommand(args);

        case "printModuleInfo":
            return new PrintModuleInfoCommand(args);

        case "queryModuleAvailability":
            return new QueryModuleAvailabilityCommand(args);

        case "viewSpecialisations":
            return new ViewSpecialisationsCommand();

        case "viewSpecialisationInfo":
            return new SpecialisationInfoCommand(args);

        default:
            throw new ClassMateException("Unknown command. Enter 'help' for available commands.");
        }
    }

}

