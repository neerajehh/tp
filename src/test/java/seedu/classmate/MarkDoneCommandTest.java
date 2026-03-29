package seedu.classmate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.classmate.commands.MarkDoneCommand;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit tests for MarkDoneCommand.
 */
public class MarkDoneCommandTest {

    private Major major;
    private SpecialisationOverview specOverview;
    private Ui ui;
    private Storage storage;
    private ArrayList<String> completedModules;

    @BeforeEach
    public void setUp() {
        ArrayList<Module> modules = new ArrayList<>();
        Module cs2113 = new Module("CS2113", "Software Engineering & OOP");
        modules.add(cs2113);
        major = new Major(modules);
        specOverview = new SpecialisationOverview(new HashMap<>());
        ui = new Ui();
        storage = new Storage("data/testMarkDone.txt");
        completedModules = new ArrayList<>();
    }

    @Test
    void execute_validModule_marksAsDone() {
        MarkDoneCommand cmd = new MarkDoneCommand("CS2113", completedModules, storage);
        cmd.executeCommand(major, ui, specOverview);
        assertTrue(completedModules.contains("CS2113"));
    }

    @Test
    void execute_emptyModuleCode_throwsException() {
        MarkDoneCommand cmd = new MarkDoneCommand("", completedModules, storage);
        assertThrows(ClassMateException.class,
                () -> cmd.executeCommand(major, ui, specOverview));
    }

    @Test
    void execute_alreadyMarkedDone_doesNotDuplicate() {
        completedModules.add("CS2113");
        MarkDoneCommand cmd = new MarkDoneCommand("CS2113", completedModules, storage);
        cmd.executeCommand(major, ui, specOverview);
        long count = completedModules.stream().filter(m -> m.equals("CS2113")).count();
        assertTrue(count == 1);
    }

    @Test
    void execute_moduleNotInMajor_doesNotAdd() {
        MarkDoneCommand cmd = new MarkDoneCommand("CS9999", completedModules, storage);
        cmd.executeCommand(major, ui, specOverview);
        assertFalse(completedModules.contains("CS9999"));
    }
}
