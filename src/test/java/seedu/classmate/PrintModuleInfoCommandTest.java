package seedu.classmate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.classmate.commands.PrintModuleInfoCommand;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Unit tests for PrintModuleInfoCommand.
 */
public class PrintModuleInfoCommandTest {

    private Major major;
    private SpecialisationOverview specOverview;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        ArrayList<Module> modules = new ArrayList<>();
        Module cs2113 = new Module("CS2113", "Software Engineering & OOP");
        cs2113.addPrerequisite("CS2040C");
        modules.add(cs2113);
        major = new Major(modules);
        specOverview = new SpecialisationOverview(new HashMap<>());
        ui = new Ui();
    }

    @Test
    void execute_validModule_doesNotThrow() {
        PrintModuleInfoCommand cmd = new PrintModuleInfoCommand("CS2113");
        assertDoesNotThrow(() -> cmd.executeCommand(major, ui, specOverview));
    }

    @Test
    void execute_moduleNotFound_throwsException() {
        PrintModuleInfoCommand cmd = new PrintModuleInfoCommand("CS9999");
        assertThrows(ClassMateException.class,
                () -> cmd.executeCommand(major, ui, specOverview));
    }

    @Test
    void execute_emptyModuleCode_throwsException() {
        PrintModuleInfoCommand cmd = new PrintModuleInfoCommand("");
        assertThrows(ClassMateException.class,
                () -> cmd.executeCommand(major, ui, specOverview));
    }

    @Test
    void execute_moduleWithPrereqs_doesNotThrow() {
        PrintModuleInfoCommand cmd = new PrintModuleInfoCommand("CS2113");
        assertDoesNotThrow(() -> cmd.executeCommand(major, ui, specOverview));
    }
}
