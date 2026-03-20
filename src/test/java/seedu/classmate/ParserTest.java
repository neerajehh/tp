package seedu.classmate;
import org.junit.jupiter.api.Test;

import seedu.classmate.commands.ByeCommand;
import seedu.classmate.commands.Command;
import seedu.classmate.commands.HelpCommand;
import seedu.classmate.commands.PrereqCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


public class ParserTest {

    @Test
    public void testHelpCommand() {
        Command command = Parser.parse("help");

        assertTrue(command instanceof HelpCommand);
    }

    @Test
    public void testEmptyCommand() {
        Exception exception = assertThrows(ClassMateException.class,
                () -> Parser.parse(""));

        assertEquals("Please enter a non-empty input!", exception.getMessage());

    }

    @Test
    public void testUnknownCommand() {
        Exception exception = assertThrows(ClassMateException.class,
                () -> Parser.parse("unknownCommand"));

        assertEquals("Unknown command. Enter 'help' for available commands.",
                exception.getMessage());
    }

    @Test
    public void parse_byeInput_returnsByeCommand() {
        String input = "bye";
        Command result = Parser.parse(input);

        assertInstanceOf(ByeCommand.class, result);
    }

    @Test
    public void parseValidCommandWithMixedCase() {
        String userInput = "ViEwPrErEqS Cs2113";

        Command output = Parser.parse(userInput);
        assertInstanceOf(PrereqCommand.class, output, "Output is of type PrereqCommand");
    }

    @Test
    public void parseInvalidCommandWithMixedCase() {
        String userInput = "aCommand Cs2113";

        ClassMateException exception = assertThrows(ClassMateException.class,
                () -> Parser.parse(userInput));

        assertEquals("Unknown command. Enter 'help' for available commands.", exception.getMessage(),
                "Error message informs the user that the command is invalid");
    }
}
