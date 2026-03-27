package seedu.classmate;

import org.junit.jupiter.api.Test;
import seedu.classmate.commands.CheckPrereqStatusCommand;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Unit tests for CheckPrereqStatusCommand.
 */
public class CheckPrereqStatusCommandTest {

    private Major major = new Major(new ArrayList<>());
    private SpecialisationOverview specOverview = new SpecialisationOverview(new HashMap<>());
    private Ui ui = new Ui();

    @Test
    void execute_emptyModuleCode_throwsException() {
        ArrayList<String> completed = new ArrayList<>();
        CheckPrereqStatusCommand cmd = new CheckPrereqStatusCommand("", completed);
        assertThrows(ClassMateException.class,
                () -> cmd.executeCommand(major, ui, specOverview));
    }

    @Test
    void execute_moduleNotFound_throwsException() {
        ArrayList<String> completed = new ArrayList<>();
        CheckPrereqStatusCommand cmd = new CheckPrereqStatusCommand("INVALID123", completed);
        assertThrows(ClassMateException.class,
                () -> cmd.executeCommand(major, ui, specOverview));
    }

    @Test
    void execute_moduleWithNoPrereqs_doesNotThrow() {
        ArrayList<String> completed = new ArrayList<>();
        CheckPrereqStatusCommand cmd = new CheckPrereqStatusCommand("MA1511", completed);
        assertDoesNotThrow(() -> cmd.executeCommand(major, ui, specOverview));
    }

    @Test
    void execute_moduleWithAllPrereqsCompleted_doesNotThrow() {
        ArrayList<String> completed = new ArrayList<>();
        completed.add("CS2040C");
        CheckPrereqStatusCommand cmd = new CheckPrereqStatusCommand("CS2113", completed);
        assertDoesNotThrow(() -> cmd.executeCommand(major, ui, specOverview));
    }

    @Test
    void execute_moduleWithNoPrereqsCompleted_doesNotThrow() {
        ArrayList<String> completed = new ArrayList<>();
        CheckPrereqStatusCommand cmd = new CheckPrereqStatusCommand("CS2113", completed);
        assertDoesNotThrow(() -> cmd.executeCommand(major, ui, specOverview));
    }

    @Test
    void execute_mixedPrereqCompletion_doesNotThrow() {
        ArrayList<String> completed = new ArrayList<>();
        completed.add("CG1111A");
        CheckPrereqStatusCommand cmd = new CheckPrereqStatusCommand("CG2271", completed);
        assertDoesNotThrow(() -> cmd.executeCommand(major, ui, specOverview));
    }
}

