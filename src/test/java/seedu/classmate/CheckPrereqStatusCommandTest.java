package seedu.classmate;

import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    public void setUp() {
        ArrayList<Module> modules = new ArrayList<>();

        Module ma1511 = new Module("MA1511", "Engineering Calculus");
        Module cs2113 = new Module("CS2113", "Software Engineering & Object-Oriented Programming");
        cs2113.addPrerequisite("CS2040C");

        Module cg2271 = new Module("CG2271", "Real-time Operating System");
        cg2271.addPrerequisite("CS2040C");

        modules.add(ma1511);
        modules.add(cs2113);
        modules.add(cg2271);

        major = new Major(modules);

        HashMap<String, ArrayList<Module>> iotMap = new HashMap<>();
        ArrayList<Module> iotModules = new ArrayList<>();

        Module cs3244 = new Module("CS3244", "Machine Learning");
        cs3244.addPrerequisite("CS1010");
        cs3244.addPrerequisite("ST2334");
        cs3244.addPrerequisite("MA1508E");
        cs3244.addPrerequisite("MA1511");
        cs3244.addPrerequisite("MA1512");

        iotModules.add(cs3244);
        iotMap.put("Internet of Things", iotModules);

        specOverview = new SpecialisationOverview(iotMap);
    }


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

    @Test
    void execute_lowercaseModuleCode_doesNotThrow() {
        ArrayList<String> completed = new ArrayList<>();
        CheckPrereqStatusCommand cmd = new CheckPrereqStatusCommand("cg2271", completed);
        assertDoesNotThrow(() -> cmd.executeCommand(major, ui, specOverview),
                "Command should handle user's lowercase input by converting it to uppercase");
    }

    @Test
    void execute_checkPrereqStatusModuleInSpecialisationOnly_doesNotThrow() {
        ArrayList<String> completed = new ArrayList<>();
        CheckPrereqStatusCommand cmd = new CheckPrereqStatusCommand("CS3244", completed);
        assertDoesNotThrow(() -> cmd.executeCommand(major, ui, specOverview),
                "Command should search specialisation if the module is not found in major");
    }

    @Test
    void execute_whitespaceModuleCode_doesNotThrow() {
        ArrayList<String> completed = new ArrayList<>();
        CheckPrereqStatusCommand cmd = new CheckPrereqStatusCommand("  CG2271 ", completed);
        assertDoesNotThrow(() -> cmd.executeCommand(major, ui, specOverview),
                "Command should trim whitespace in the user input and still detect the module code");
    }
}

