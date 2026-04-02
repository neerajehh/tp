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
    private ArrayList<String> completedModules;

    @BeforeEach
    public void setUp() {
        ArrayList<Module> modules = new ArrayList<>();
        Module cs2113 = new Module("CS2113", "Software Engineering & OOP");
        cs2113.addPrerequisite("CS2040C");
        modules.add(cs2113);
        major = new Major(modules);

        HashMap<String, ArrayList<Module>> roboticsMap = new HashMap<>();
        ArrayList<Module> roboticsModules = new ArrayList<>();

        Module cs3244 = new Module("EE4705", "Human-Robot Interaction");
        cs3244.addPrerequisite("EE2211");
        cs3244.addPrerequisite("EE3331C");

        roboticsModules.add(cs3244);
        roboticsMap.put("Internet of Things", roboticsModules);

        specOverview = new SpecialisationOverview(roboticsMap);
        ui = new Ui();

        completedModules = new ArrayList<>();
        completedModules.add("CS2040C");
    }

    @Test
    void execute_validModule_doesNotThrow() {
        PrintModuleInfoCommand cmd = new PrintModuleInfoCommand("CS2113", new java.util.ArrayList<>());
        assertDoesNotThrow(() -> cmd.executeCommand(major, ui, specOverview));
    }

    @Test
    void execute_moduleNotFound_throwsException() {
        PrintModuleInfoCommand cmd = new PrintModuleInfoCommand("CS9999", new java.util.ArrayList<>());
        assertThrows(ClassMateException.class,
                () -> cmd.executeCommand(major, ui, specOverview));
    }

    @Test
    void execute_emptyModuleCode_throwsException() {
        PrintModuleInfoCommand cmd = new PrintModuleInfoCommand("", new java.util.ArrayList<>());
        assertThrows(ClassMateException.class,
                () -> cmd.executeCommand(major, ui, specOverview));
    }

    @Test
    void execute_moduleWithPrereqs_doesNotThrow() {
        PrintModuleInfoCommand cmd = new PrintModuleInfoCommand("CS2113", new java.util.ArrayList<>());
        assertDoesNotThrow(() -> cmd.executeCommand(major, ui, specOverview));
    }

    @Test
    void execute_moduleInSpecialisationOnly_doesNotThrow() {
        PrintModuleInfoCommand cmd = new PrintModuleInfoCommand("EE4705", new java.util.ArrayList<>());
        assertDoesNotThrow(() -> cmd.executeCommand(major, ui, specOverview));
    }

    @Test
    void execute_lowerCaseModuleInSpecialisationOnly_doesNotThrow() {
        PrintModuleInfoCommand cmd = new PrintModuleInfoCommand("ee4705", new java.util.ArrayList<>());
        assertDoesNotThrow(() -> cmd.executeCommand(major, ui, specOverview));
    }

    @Test
    void execute_whitespaceModuleCode_doesNotThrow() {
        PrintModuleInfoCommand cmd = new PrintModuleInfoCommand("    EE4705  ", new java.util.ArrayList<>());
        assertDoesNotThrow(() -> cmd.executeCommand(major, ui, specOverview));
    }

    @Test
    void execute_moduleWithCompletedPrerequisites_doesNotThrow() {
        PrintModuleInfoCommand cmd = new PrintModuleInfoCommand("CS2113", completedModules);
        assertDoesNotThrow(() -> cmd.executeCommand(major, ui, specOverview));
    }
}
