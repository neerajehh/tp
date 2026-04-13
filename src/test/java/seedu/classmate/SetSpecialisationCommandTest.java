package seedu.classmate;
import seedu.classmate.commands.SetSpecialisationCommand;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class SetSpecialisationCommandTest {

    private static final String TEST_MODULES_FILE = "data/testModules.txt";
    private static final String TEST_SPECS_FILE = "data/testSpecs.txt";

    private Major major;
    private SpecialisationOverview specOverview;
    private Ui ui;
    private Storage storage;
    private UserProfile userProfile;

    @BeforeEach
    public void setUp() {
        // Initialize dummy curriculum data
        major = new Major(new ArrayList<>());
        specOverview = new SpecialisationOverview(new HashMap<>());
        ui = new Ui();

        // Initialize test storage and empty profile
        storage = new Storage(TEST_MODULES_FILE, TEST_SPECS_FILE);
        userProfile = new UserProfile(new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Creates a SpecialisationOverview with valid specialisation names for validation checks.
     */
    private SpecialisationOverview createValidSpecOverview() {
        HashMap<String, ArrayList<Module>> specMap = new HashMap<>();
        specMap.put("Robotics", new ArrayList<>());
        specMap.put("Internet of Things", new ArrayList<>());
        specMap.put("Space Technology", new ArrayList<>());
        specMap.put("Industry 4.0", new ArrayList<>());
        specMap.put("Advanced Electronics", new ArrayList<>());
        specMap.put("Others", new ArrayList<>());
        return new SpecialisationOverview(specMap);
    }

    @AfterEach
    void cleanup() {
        // Delete test files after each run so tests don't interfere with each other
        new File(TEST_MODULES_FILE).delete();
        new File(TEST_SPECS_FILE).delete();
    }

    // Boundary values: 0, 1, 2, 3

    // Test if user can successfully add one specialisation
    @Test
    void execute_validSpecialisation_addsSuccessfully() {
        SetSpecialisationCommand cmd = new SetSpecialisationCommand("Robotics", userProfile, storage);

        assertDoesNotThrow(() -> cmd.executeCommand(major, ui, createValidSpecOverview()));

        ArrayList<String> specs = userProfile.getUserSpecialisations();
        assertEquals(1, specs.size());
        assertTrue(specs.contains("Robotics"));
    }

    // Test if user can successfully add two specialisations
    @Test
    void execute_twoValidSpecialisations_addsBothSuccessfully() {
        SetSpecialisationCommand cmd1 = new SetSpecialisationCommand("Robotics", userProfile, storage);
        SetSpecialisationCommand cmd2 = new SetSpecialisationCommand("Internet of Things", userProfile, storage);

        assertDoesNotThrow(() -> {
            cmd1.executeCommand(major, ui, createValidSpecOverview());
            cmd2.executeCommand(major, ui, createValidSpecOverview());
        });

        ArrayList<String> specs = userProfile.getUserSpecialisations();
        assertEquals(2, specs.size());
        assertTrue(specs.contains("Robotics"));
        assertTrue(specs.contains("Internet of Things"));
    }

    // Test if empty specialisation indicated
    @Test
    void execute_emptySpecialisation_throwsException() {
        SetSpecialisationCommand cmd = new SetSpecialisationCommand("", userProfile, storage);

        ClassMateException exception = assertThrows(ClassMateException.class,
                () -> cmd.executeCommand(major, ui, specOverview));

        assertEquals("Please provide a specialisation name. Format: setspecialisation <NAME>", exception.getMessage());
    }

    // Test if 3 specs added
    @Test
    void execute_threeSpecialisations_throwsLimitException() {
        SetSpecialisationCommand cmd1 = new SetSpecialisationCommand("Robotics", userProfile, storage);
        SetSpecialisationCommand cmd2 = new SetSpecialisationCommand("Internet of Things", userProfile, storage);
        SetSpecialisationCommand cmd3 = new SetSpecialisationCommand("Space Technology", userProfile, storage);

        // First two adds should succeed
        assertDoesNotThrow(() -> {
            cmd1.executeCommand(major, ui, createValidSpecOverview());
            cmd2.executeCommand(major, ui, createValidSpecOverview());
        });

        // Third add should hit the specialisation limit and throw exception
        ClassMateException exception = assertThrows(ClassMateException.class,
                () -> cmd3.executeCommand(major, ui, createValidSpecOverview()));

        assertEquals("You can only select up to 2 specialisations.", exception.getMessage());
    }

    // Test for duplicate specialisation entry
    @Test
    void execute_duplicateSpecialisation_throwsException() {
        SetSpecialisationCommand cmd1 = new SetSpecialisationCommand("Robotics", userProfile, storage);
        SetSpecialisationCommand cmd2 = new SetSpecialisationCommand("Robotics", userProfile, storage);

        // First one should succeed
        assertDoesNotThrow(() -> cmd1.executeCommand(major, ui, createValidSpecOverview()));

        // Second one (duplicate) should throw your ClassMateException
        ClassMateException exception = assertThrows(ClassMateException.class,
                () -> cmd2.executeCommand(major, ui, createValidSpecOverview()));

        assertEquals("You have already selected this specialisation.", exception.getMessage());
    }

    // Test for invalid specialisation entry
    @Test
    void execute_invalidSpecialisation_throwsException() {
        SetSpecialisationCommand cmd = new SetSpecialisationCommand("NotASpec", userProfile, storage);

        ClassMateException exception = assertThrows(ClassMateException.class,
                () -> cmd.executeCommand(major, ui, specOverview));

        assertEquals("Invalid specialisation name.", exception.getMessage());
        assertTrue(userProfile.getUserSpecialisations().isEmpty());
    }
}
