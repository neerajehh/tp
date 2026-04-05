package seedu.classmate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import seedu.classmate.commands.ByeCommand;
import seedu.classmate.commands.CheckPrereqStatusCommand;
import seedu.classmate.commands.CheckProfileCommand;
import seedu.classmate.commands.Command;
import seedu.classmate.commands.HelpCommand;
import seedu.classmate.commands.MarkDoneCommand;
import seedu.classmate.commands.PrereqCommand;
import seedu.classmate.commands.PrintModuleInfoCommand;
import seedu.classmate.commands.QueryModuleAvailabilityCommand;
import seedu.classmate.commands.SetSpecialisationCommand;
import seedu.classmate.commands.SpecialisationInfoCommand;
import seedu.classmate.commands.ViewDoneCommand;
import seedu.classmate.commands.ViewGradReqsCommand;
import seedu.classmate.commands.ViewSpecialisationsCommand;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandManagerTest {

    private static final String TEST_MODULES_FILE = "data/testModules.txt";
    private static final String TEST_SPECS_FILE = "data/testSpecs.txt";
    private final Storage storage = new Storage(TEST_MODULES_FILE, TEST_SPECS_FILE);
    private final UserProfile userProfile = new UserProfile(new ArrayList<>(), new ArrayList<>());

    @AfterEach
    void cleanup() {
        new File(TEST_MODULES_FILE).delete();
        new File(TEST_SPECS_FILE).delete();
    }

    // @@author VinayVR26
    @Test
    public void createHelpCommand() {
        Command command = CommandManager.createCommand("help", "", storage, userProfile);
        assertInstanceOf(HelpCommand.class, command);
    }

    @Test
    public void createByeCommand() {
        Command command = CommandManager.createCommand("bye", "", storage, userProfile);
        assertInstanceOf(ByeCommand.class, command);
    }
    // @@author

    // @@author gohsa5458
    @Test
    public void createViewGradReqsCommand() {
        Command command = CommandManager.createCommand(
                "viewgradreqs", "", storage, userProfile);
        assertInstanceOf(ViewGradReqsCommand.class, command);
    }

    @Test
    public void createViewModuleInfoCommand() {
        Command command = CommandManager.createCommand(
                "viewmoduleinfo", "CS2113", storage, userProfile);
        assertInstanceOf(PrintModuleInfoCommand.class, command);
    }

    @Test
    public void createQueryModuleAvailabilityCommand() {
        Command command = CommandManager.createCommand(
                "querymoduleavailability", "CS2113 sem1", storage, userProfile);
        assertInstanceOf(QueryModuleAvailabilityCommand.class, command);
    }

    @Test
    public void createViewSpecialisationsCommand() {
        Command command = CommandManager.createCommand(
                "viewspecialisations", "", storage, userProfile);
        assertInstanceOf(ViewSpecialisationsCommand.class, command);
    }

    @Test
    public void createSpecialisationInfoCommand() {
        Command command = CommandManager.createCommand(
                "viewspecialisationinfo", "1", storage, userProfile);
        assertInstanceOf(SpecialisationInfoCommand.class, command);
    }

    @Test
    public void createMarkDoneCommand() {
        Command command = CommandManager.createCommand(
                "markdone", "CS2113", storage, userProfile);
        assertInstanceOf(MarkDoneCommand.class, command);
    }

    @Test
    public void createViewDoneCommand() {
        Command command = CommandManager.createCommand(
                "viewdone", "", storage, userProfile);
        assertInstanceOf(ViewDoneCommand.class, command);
    }
    // @@author

    // @@author Michael-coding06
    @Test
    public void createCheckProfileCommand() {
        Command command = CommandManager.createCommand(
                "checkprofile", "", storage, userProfile);
        assertInstanceOf(CheckProfileCommand.class, command);
    }
    // @@author

    @Test
    public void createPrereqCommand() {
        Command command = CommandManager.createCommand(
                "viewprereqs", "CS2113", storage, userProfile);
        assertInstanceOf(PrereqCommand.class, command);
    }

    @Test
    public void checkPrereqStatusCommand() {
        Command command = CommandManager.createCommand(
                "checkprereqstatus", "CS2113", storage, userProfile);
        assertInstanceOf(CheckPrereqStatusCommand.class, command);
    }

    @Test
    public void createSetSpecialisationCommand() {
        Command command = CommandManager.createCommand(
                "setspecialisation", "Robotics", storage, userProfile);
        assertInstanceOf(SetSpecialisationCommand.class, command);
    }

    @Test
    public void createUnknownCommand() {
        ClassMateException exception = assertThrows(
                ClassMateException.class,
                () -> CommandManager.createCommand(
                        "unknownCommand", "", storage, userProfile)
        );
        assertEquals("Unknown command. Enter 'help' for available commands.", exception.getMessage());
    }
}
