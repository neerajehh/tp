package seedu.classmate;

import org.junit.jupiter.api.Test;

import seedu.classmate.commands.ByeCommand;
import seedu.classmate.commands.Command;
import seedu.classmate.commands.HelpCommand;
import seedu.classmate.commands.MarkDoneCommand;
import seedu.classmate.commands.PrereqCommand;
import seedu.classmate.commands.PrintModuleInfoCommand;
import seedu.classmate.commands.QueryModuleAvailabilityCommand;
import seedu.classmate.commands.SpecialisationInfoCommand;
import seedu.classmate.commands.ViewDoneCommand;
import seedu.classmate.commands.ViewGradReqsCommand;
import seedu.classmate.commands.ViewSpecialisationsCommand;
import seedu.classmate.commands.CheckProfileCommand;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * Contains unit tests for the Parser class.
 * Tests whether user input is correctly parsed into Command objects.
 */
public class ParserTest {

    private final ArrayList<String> completedModules = new ArrayList<>();
    private final Storage storage = new Storage();
    private final Ui ui = new Ui();
    private final UserProfile userProfile = new UserProfile();
    private final Major major = new Major(new ArrayList<>());
    private final SpecialisationOverview specOverview = new SpecialisationOverview(new HashMap<>());

    @Test
    public void testHelpCommand() {
        Command command = Parser.parse("help", completedModules, storage, userProfile);
        assertTrue(command instanceof HelpCommand);
    }

    @Test
    public void testEmptyCommand() {
        Exception exception = assertThrows(ClassMateException.class,
                () -> Parser.parse("", completedModules, storage, userProfile));
        assertEquals("Please enter a non-empty input!", exception.getMessage());
    }

    @Test
    public void testUnknownCommand() {
        Exception exception = assertThrows(ClassMateException.class,
                () -> Parser.parse("unknownCommand", completedModules, storage, userProfile));
        assertEquals("Unknown command. Enter 'help' for available commands.",
                exception.getMessage());
    }

    @Test
    public void parse_byeInput_returnsByeCommand() {
        String input = "bye";
        Command result = Parser.parse(input, completedModules, storage, userProfile);
        assertInstanceOf(ByeCommand.class, result);
    }

    @Test
    public void parse_ValidCommandWithMixedCase() {
        String userInput = "ViEwPrErEqS Cs2113";
        Command output = Parser.parse(userInput, completedModules, storage, userProfile);
        assertInstanceOf(PrereqCommand.class, output, "Output is of type PrereqCommand");
    }

    @Test
    public void parse_ValidCommandWithInvalidArgument_throwsException() {
        String userInput = "viewprereqs Cs211";
        Command command = Parser.parse(userInput, completedModules, storage, userProfile);

        ClassMateException exception = assertThrows(ClassMateException.class,
                () -> command.executeCommand(major, ui, specOverview));
        assertEquals("Module CS211 not found", exception.getMessage(),
                "Error message informs the user that the module is not found");
    }

    @Test
    public void parse_ExactValidCommandWithInvalidArgument_throwsException() {
        String userInput = "viewPreReqs Cs211";
        Command command = Parser.parse(userInput, completedModules, storage, userProfile);

        ClassMateException exception = assertThrows(ClassMateException.class,
                () -> command.executeCommand(major, ui, specOverview));
        assertEquals("Module CS211 not found", exception.getMessage(),
                "Error message informs the user that the module is not found");
    }

    @Test
    public void parse_InvalidCommandWithMixedCaseArgument_throwsException() {
        String userInput = "aCommand Cs2113";
        ClassMateException exception = assertThrows(ClassMateException.class,
                () -> Parser.parse(userInput, completedModules, storage, userProfile));
        assertEquals("Unknown command. Enter 'help' for available commands.", exception.getMessage(),
                "Error message informs the user that the command is invalid");
    }

    @Test
    public void parse_ValidCommandWithLeadingAndTrailingWhitespace_doesNotThrow() {
        String input = "   viewDone ";
        Command command = Parser.parse(input, completedModules, storage, userProfile);
        assertInstanceOf(ViewDoneCommand.class, command);
    }

    @Test
    public void parse_ValidCommandWithWhitespaceInBetween_doesNotThrow() {
        String input = "markDone     CG2271";
        Command command = Parser.parse(input, completedModules, storage, userProfile);
        assertInstanceOf(MarkDoneCommand.class, command);
    }

    @Test
    public void testViewGradeReqsCommand() {
        Command command = Parser.parse("viewGradReqs", completedModules, storage, userProfile);
        assertInstanceOf(ViewGradReqsCommand.class, command);
    }

    @Test
    public void testSpecialisationInfoCommand() {
        Command command = Parser.parse("viewSpecialisationInfo 2", completedModules, storage, userProfile);
        assertInstanceOf(SpecialisationInfoCommand.class, command);
    }

    @Test
    public void testViewModuleInfoCommand() {
        Command command = Parser.parse("viewModuleInfo CS2113", completedModules, storage, userProfile);
        assertInstanceOf(PrintModuleInfoCommand.class, command);
    }

    @Test
    public void testQueryModuleAvailabilityCommand() {
        Command command = Parser.parse("queryModuleAvailability CG2023 sem1", completedModules, storage, userProfile);
        assertInstanceOf(QueryModuleAvailabilityCommand.class, command);
    }

    @Test
    public void testViewSpecialisationsCommand() {
        Command command = Parser.parse("viewSpecialisations", completedModules, storage, userProfile);
        assertInstanceOf(ViewSpecialisationsCommand.class, command);
    }

    @Test
    public void testMarkDoneCommand() {
        Command command = Parser.parse("markDone CS2113", completedModules, storage, userProfile);
        assertInstanceOf(MarkDoneCommand.class, command);
    }

    @Test
    public void testViewDoneCommand() {
        Command command = Parser.parse("viewDone", completedModules, storage, userProfile);
        assertInstanceOf(ViewDoneCommand.class, command);
    }

    @Test
    public void testCheckProfileCommand() {
        Command command = Parser.parse("checkProfile", completedModules, storage, userProfile);
        assertInstanceOf(CheckProfileCommand.class, command);
    }
}
