package seedu.classmate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Handles reading and writing of completed modules to a save file.
 */
public class Storage {
    // @@author neerajehh
    private static final Logger logger = Logger.getLogger(Storage.class.getName());
    private static final String DATA_DIR = "data";
    private final String modulesFilePath;
    private final String specFilePath;

    /**
     * Default constructor initializes paths to the standard data directory.
     */
    public Storage() {
        this.modulesFilePath = DATA_DIR + "/completedModules.txt";
        this.specFilePath = DATA_DIR + "/specialisation.txt";
        ensureDirectoryExists();
    }

    /*
     * Overloaded constructor for testing purposes.
     * Allows injecting custom filepaths.
     */
    public Storage(String modulesFilePath, String specFilePath) {
        this.modulesFilePath = modulesFilePath;
        this.specFilePath = specFilePath;
    }

    private void ensureDirectoryExists() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }
    // @@author

    // @@author lauwn-mower
    /**
     * Loads the list of completed module codes from the modules save file.
     *
     * @return An ArrayList of completed module codes. Returns empty list if no file exists.
     */
    public ArrayList<String> loadCompletedModules() {
        ArrayList<String> completedModules = new ArrayList<>();
        try {
            File file = new File(modulesFilePath);
            if (!file.exists()) {
                logger.info("No saved files found. Starting fresh.");
                return completedModules;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    completedModules.add(line);
                }
            }
            scanner.close();
            logger.info("Loaded " + completedModules.size() + " completed modules.");
        } catch (IOException e) {
            logger.warning("Failed to load completed modules: " + e.getMessage());
        }
        return completedModules;
    }

    /**
     * Loads the list of selected specialisations from the specialisation save file.
     *
     * @return An ArrayList of specialisation names. Returns empty list if no file exists.
     */
    public ArrayList<String> loadSpecialisations() {
        ArrayList<String> specialisations = new ArrayList<>();
        try {
            File file = new File(specFilePath);
            if (!file.exists()) {
                logger.info("No specialisation save file found. Starting fresh.");
                return specialisations;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    specialisations.add(line);
                }
            }
            scanner.close();
            logger.info("Loaded " + specialisations.size() + " specialisations.");
        } catch (IOException e) {
            logger.warning("Failed to load specialisations: " + e.getMessage());
        }
        return specialisations;
    }

    /**
     * Saves the entire UserProfile state (both completed modules and specialisations) to disk.
     *
     * @param profile The UserProfile containing current user data.
     * @throws ClassMateException If an error occurs during file writing.
     */
    public void saveUserProfile(UserProfile profile) throws ClassMateException {
        assert profile != null : "UserProfile should not be null when saving";

        try {
            // 1. Save Modules
            FileWriter moduleWriter = new FileWriter(modulesFilePath);
            for (String moduleCode : profile.getUserCompletedModules()) {
                moduleWriter.write(moduleCode + System.lineSeparator());
            }
            moduleWriter.close();

            // 2. Save Specialisations
            FileWriter specWriter = new FileWriter(specFilePath);
            for (String spec : profile.getUserSpecialisations()) {
                specWriter.write(spec + System.lineSeparator());
            }
            specWriter.close();

            logger.info("Successfully saved UserProfile data to disk.");

        } catch (IOException e) {
            logger.warning("Failed to save UserProfile: " + e.getMessage());
            // Throw exception to be caught and printed safely by the Main/UI layer
            throw new ClassMateException("Error saving your profile data to disk.");
        }
    }
    // @@author
}
