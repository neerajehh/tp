package seedu.classmate;
import org.junit.jupiter.api.Test;
import seedu.classmate.commands.Command;
import seedu.classmate.commands.HelpCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
}
