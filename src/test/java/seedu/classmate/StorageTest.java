package seedu.classmate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

/**
 * Contains unit tests for the Storage class.
 */
public class StorageTest {
    // @@author gohsa5458
    private static final String TEST_MODULES_FILE = "data/testModules.txt";
    private static final String TEST_SPEC_FILE = "data/testSpecs.txt";
    private final Storage storage = new Storage(TEST_MODULES_FILE, TEST_SPEC_FILE);

    @AfterEach
    void cleanup() {
        // Clean up both test files to ensure a fresh environment for each test
        File modulesFile = new File(TEST_MODULES_FILE);
        if (modulesFile.exists()) {
            modulesFile.delete();
        }
        File specFile = new File(TEST_SPEC_FILE);
        if (specFile.exists()) {
            specFile.delete();
        }
    }

    @Test
    void testReturnEmptyListWhenFileNotExists() {
        ArrayList<String> loadedModules = storage.loadCompletedModules();
        ArrayList<String> loadedSpecs = storage.loadSpecialisations();

        assertTrue(loadedModules.isEmpty());
        assertTrue(loadedSpecs.isEmpty());
    }

    @Test
    void testSaveEmptyProfileCreatesEmptyFiles() {
        UserProfile emptyProfile = new UserProfile(new ArrayList<>(), new ArrayList<>());

        assertDoesNotThrow(() -> storage.saveUserProfile(emptyProfile));

        ArrayList<String> loadedModules = storage.loadCompletedModules();
        ArrayList<String> loadedSpecs = storage.loadSpecialisations();

        assertEquals(0, loadedModules.size());
        assertEquals(0, loadedSpecs.size());
    }
    // @@author

    // @@author lauwn-mower
    @Test
    void testSaveAndLoadUserProfile() {
        // Create dummy data and inject it into a UserProfile
        ArrayList<String> modules = new ArrayList<>();
        modules.add("CS2113");
        modules.add("CG2023");

        ArrayList<String> specs = new ArrayList<>();
        specs.add("Space Technology");

        UserProfile profile = new UserProfile(modules, specs);

        // Save using assertDoesNotThrow since the method now throws ClassMateException
        assertDoesNotThrow(() -> storage.saveUserProfile(profile));

        // Load the data back out
        ArrayList<String> loadedModules = storage.loadCompletedModules();
        ArrayList<String> loadedSpecs = storage.loadSpecialisations();

        // Verify modules using assertions
        assertEquals(2, loadedModules.size());
        assertTrue(loadedModules.contains("CS2113"));
        assertTrue(loadedModules.contains("CG2023"));

        // Verify specialisations using assertions
        assertEquals(1, loadedSpecs.size());
        assertTrue(loadedSpecs.contains("Space Technology"));
    }
    // @@author
}
