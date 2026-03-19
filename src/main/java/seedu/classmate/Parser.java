package seedu.classmate;

import seedu.classmate.commands.Command;

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
    public static Command parse(String input) throws ClassMateException {

        String trimmed = input.trim();

        if (trimmed.isEmpty()) {
            throw new ClassMateException("Please enter a non-empty input!");
        }

        String[] components = trimmed.split("\\s+", 2);

        assert components.length > 0 && components.length <= 2 : "The split method has failed to split the user " +
                "input into at most 2 components";

        String commandWord = components[0].toLowerCase();
        String arguments = (components.length > 1) ? components[1].trim().toUpperCase() : "";

        return CommandManager.createCommand(commandWord, arguments);
    }

}

