package seedu.classmate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.classmate.commands.QueryModuleAvailabilityCommand;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Unit tests for QueryModuleAvailabilityCommand.
 */
public class QueryModuleAvailabilityCommandTest {

    private Major major;
    private SpecialisationOverview specOverview;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        ArrayList<Module> modules = new ArrayList<>();
        Module cs2113 = new Module("CS2113", "Software Engineering & OOP");
        Module cg2023 = new Module("CG2023", "Signals & Systems");
        cg2023.setSemester("2");
        modules.add(cs2113);
        modules.add(cg2023);
        major = new Major(modules);
        specOverview = new SpecialisationOverview(new HashMap<>());
        ui = new Ui();
    }

    @Test
    void execute_validModuleBothSemesters_doesNotThrow() {
        QueryModuleAvailabilityCommand cmd =
                new QueryModuleAvailabilityCommand("CS2113 sem1");
        assertDoesNotThrow(() -> cmd.executeCommand(major, ui, specOverview));
    }

    @Test
    void execute_moduleNotFound_throwsException() {
        QueryModuleAvailabilityCommand cmd =
                new QueryModuleAvailabilityCommand("CS9999 sem1");
        assertThrows(ClassMateException.class,
                () -> cmd.executeCommand(major, ui, specOverview));
    }

    @Test
    void constructor_missingArguments_throwsException() {
        assertThrows(ClassMateException.class,
                () -> new QueryModuleAvailabilityCommand("CS2113"));
    }

    @Test
    void execute_sem2OnlyModule_doesNotThrow() {
        QueryModuleAvailabilityCommand cmd =
                new QueryModuleAvailabilityCommand("CG2023 sem2");
        assertDoesNotThrow(() -> cmd.executeCommand(major, ui, specOverview));
    }
}
