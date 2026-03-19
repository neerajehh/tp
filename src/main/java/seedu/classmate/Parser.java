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

        String[] components = trimmed.split("\\s+", 2);
        String commandWord = components[0].toLowerCase();
        String arguments = (components.length > 1) ? components[1].trim() : "";

        switch (commandWord) {
        case "help":
            return new HelpCommand();

        case "bye":
            return new ByeCommand();

        case "viewgradreqs":
            return new ViewGradReqsCommand();

        case "viewprereqs":
            return new PrereqCommand(arguments);

        case "printmoduleinfo":
            return new PrintModuleInfoCommand(arguments);

        case "querymoduleavailability":
            return new QueryModuleAvailabilityCommand(arguments);

        case "viewspecialisations":
            return new ViewSpecialisationsCommand();

        case "viewspecialisationinfo":
            return new SpecialisationInfoCommand(arguments);

        default:
            throw new ClassMateException("Unknown command. Enter 'help' for available commands.");
        }
    }

}

