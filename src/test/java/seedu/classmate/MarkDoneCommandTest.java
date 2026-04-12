package seedu.classmate;
import seedu.classmate.commands.MarkDoneCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Unit tests for MarkDoneCommand.
 */
public class MarkDoneCommandTest {

    private Major major;
    private SpecialisationOverview specOverview;
    private Ui ui;
    private Storage storage;
    private UserProfile userProfile;

    @BeforeEach
    public void setUp() {
        ArrayList<Module> modules = new ArrayList<>();
        Module cs2113 = new Module("CS2113", "Software Engineering & OOP", 4);
        modules.add(cs2113);
        major = new Major(modules);
        specOverview = new SpecialisationOverview(new HashMap<>());
        ui = new Ui();
        storage = new Storage("data/testModules.txt", "data/testSpecs.txt");
        userProfile = new UserProfile(new ArrayList<>(), new ArrayList<>());
    }

    @Test
    void execute_validModule_marksAsDone() {
        MarkDoneCommand cmd = new MarkDoneCommand("CS2113", userProfile, storage);
        assertDoesNotThrow(() -> cmd.executeCommand(major, ui, specOverview));
        assertTrue(userProfile.getUserCompletedModules().contains("CS2113"));
    }

    @Test
    void execute_emptyModuleCode_throwsException() {
        MarkDoneCommand cmd = new MarkDoneCommand("", userProfile, storage);
        assertThrows(ClassMateException.class,
                () -> cmd.executeCommand(major, ui, specOverview));
    }

    @Test
    void execute_alreadyMarkedDone_doesNotDuplicate() {
        // Mark done the first time
        Module cs2113 = new Module("CS2113", "Software Engineering & OOP", 4);
        assertDoesNotThrow(() -> userProfile.markModuleDone(cs2113));

        // Mark done the second time
        MarkDoneCommand cmd = new MarkDoneCommand("CS2113", userProfile, storage);

        // To throw exception if markDone is more than once
        assertThrows(ClassMateException.class,
                () -> cmd.executeCommand(major, ui, specOverview));
    }

    @Test
    void execute_moduleNotInMajor_doesNotAdd() {
        // Mark an invalid module as done
        MarkDoneCommand cmd = new MarkDoneCommand("CS9999", userProfile, storage);

        // Throw exception and invalidate module
        assertThrows(ClassMateException.class,
                () -> cmd.executeCommand(major, ui, specOverview));
        assertFalse(userProfile.getUserCompletedModules().contains("CS9999"));
    }
}
