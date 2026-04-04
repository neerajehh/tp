package seedu.classmate;
// @@author neerajehh

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
    private static final Logger logger = Logger.getLogger(Storage.class.getName());
    private final String filePath;

    public Storage() {
        this.filePath = "data/completedModules.txt";
    }

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of completed module codes to a file.
     *
     * @param completedModules The list of completed module codes to save.
     */
    public void save(ArrayList<String> completedModules) {
        assert completedModules != null : "Completed modules list should not be null";
        try {
            File dir = new File("data");
            if (!dir.exists()) {
                dir.mkdir();
            }
            FileWriter writer = new FileWriter(filePath);
            for (String moduleCode : completedModules) {
                writer.write(moduleCode + "\n");
            }
            writer.close();
            logger.info("Saved " + completedModules.size() + " completed modules.");
        } catch (IOException e) {
            logger.warning("Failed to save completed modules: " + e.getMessage());
            System.out.println("Warning: Could not save completed modules.");
        }
    }

    /**
     * Loads the list of completed module codes from a file.
     *
     * @return An ArrayList of completed module codes.
     */
    public ArrayList<String> load() {
        ArrayList<String> completedModules = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                logger.info("No save file found. Starting fresh.");
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
            System.out.println("Warning: Could not load completed modules.");
        }
        return completedModules;
    }
}

// @@author
