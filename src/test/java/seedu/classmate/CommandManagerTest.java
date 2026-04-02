package seedu.classmate;

import org.junit.jupiter.api.Test;
import seedu.classmate.commands.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandManagerTest {

    private final ArrayList<String> completedModules = new ArrayList<>();
    private final Storage storage = new Storage("data/testModules.txt");
    private final UserProfile userProfile = new UserProfile();

    @Test
    public void createHelpCommand() {
        Command command = CommandManager.createCommand("help", "", completedModules, storage, userProfile);
        assertInstanceOf(HelpCommand.class, command);
    }

    @Test
    public void createByeCommand() {
        Command command = CommandManager.createCommand("bye", "", completedModules, storage, userProfile);
        assertInstanceOf(ByeCommand.class, command);
    }

    @Test
    public void createViewGradReqsCommand() {
        Command command = CommandManager.createCommand(
                "viewgradreqs", "", completedModules, storage, userProfile);
        assertInstanceOf(ViewGradReqsCommand.class, command);
    }

    @Test
    public void createViewModuleInfoCommand() {
        Command command = CommandManager.createCommand(
                "viewmoduleinfo", "CS2113", completedModules, storage, userProfile);
        assertInstanceOf(PrintModuleInfoCommand.class, command);
    }

    @Test
    public void createQueryModuleAvailabilityCommand() {
        Command command = CommandManager.createCommand(
                "querymoduleavailability", "CS2113 sem1", completedModules, storage, userProfile);
        assertInstanceOf(QueryModuleAvailabilityCommand.class, command);
    }

    @Test
    public void createViewSpecialisationsCommand() {
        Command command = CommandManager.createCommand(
                "viewspecialisations", "", completedModules, storage, userProfile);
        assertInstanceOf(ViewSpecialisationsCommand.class, command);
    }

    @Test
    public void createSpecialisationInfoCommand() {
        Command command = CommandManager.createCommand(
                "viewspecialisationinfo", "1", completedModules, storage, userProfile);
        assertInstanceOf(SpecialisationInfoCommand.class, command);
    }

    @Test
    public void createMarkDoneCommand() {
        Command command = CommandManager.createCommand(
                "markdone", "CS2113", completedModules, storage, userProfile);
        assertInstanceOf(MarkDoneCommand.class, command);
    }

    @Test
    public void createViewDoneCommand() {
        Command command = CommandManager.createCommand(
                "viewdone", "", completedModules, storage, userProfile);
        assertInstanceOf(ViewDoneCommand.class, command);
    }

    @Test
    public void createCheckProfileCommand() {
        Command command = CommandManager.createCommand(
                "checkprofile", "", completedModules, storage, userProfile);
        assertInstanceOf(CheckProfileCommand.class, command);
    }

    @Test
    public void createPrereqCommand() {
        Command command = CommandManager.createCommand(
                "viewprereqs", "CS2113",  completedModules, storage, userProfile);
        assertInstanceOf(PrereqCommand.class, command);
    }

    @Test
    public void checkPrereqStatusCommand() {
        Command command = CommandManager.createCommand(
                "checkprereqstatus", "CS2113", completedModules, storage, userProfile);
        assertInstanceOf(CheckPrereqStatusCommand.class, command);
    }

    @Test
    public void createUnknownCommand() {
        ClassMateException exception = assertThrows(
                ClassMateException.class,
                () -> CommandManager.createCommand(
                        "unknownCommand", "", completedModules, storage, userProfile)
        );
        assertEquals("Unknown command. Enter 'help' for available commands.", exception.getMessage());
    }
}
