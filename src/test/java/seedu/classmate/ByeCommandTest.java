package seedu.classmate;
import seedu.classmate.commands.ByeCommand;
import seedu.classmate.commands.Command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ByeCommandTest {
    @Test
    public void isExit_byeCommand_returnsTrue() {
        Command bye = new ByeCommand();
        assertTrue(bye.isExit(), "ByeCommand should signal an exit.");
    }
}
