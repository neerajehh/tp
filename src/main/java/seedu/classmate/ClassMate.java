package seedu.classmate;
import seedu.classmate.commands.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ClassMate {
    private static final Logger logger = Logger.getLogger(ClassMate.class.getName());
    private static final Ui ui = new Ui();
    private static final ModulesLoader modulesLoader = new ModulesLoader();

    /**
     * Main entry-point for the java.classmate.Classmate application.
     */
    public static void main(String[] args) {
        setupLogger();

        ArrayList<Module> coreModulesList = modulesLoader.loadCoreModules();
        HashMap<String, ArrayList<Module>> specialisationMap = modulesLoader.loadSpecialisationModules();

        ui.showWelcome();
        logger.info("ClassMate application started.");

        // Import Major and Specialisation info from txt files
        Major major = new Major(coreModulesList);
        SpecialisationOverview specOverview = new SpecialisationOverview(specialisationMap);

        // Load user data from txt files via Storage
        Storage storage = new Storage();
        ArrayList<String> loadedModules = storage.loadCompletedModules();
        ArrayList<String> loadedSpecs = storage.loadSpecialisations();
        UserProfile userProfile = new UserProfile(loadedModules, loadedSpecs);

        // Flag to determine whether to exit program
        boolean isExit = false;

        while (!isExit) {
            // Reading of inputs handled by Ui
            String input = ui.readCommand();

            // Guard clause to check for empty input
            if (input == null) {
                break;
            }

            assert input != null : "Input should not be null";
            logger.info("User input: " + input);

            try {
                // Delegate to Parser to identify command and execute command
                Command command = Parser.parse(input, loadedModules, storage, userProfile);
                command.executeCommand(major, ui, specOverview);

                isExit = command.isExit();
            } catch (ClassMateException e) {
                ui.showError(e.getMessage());
            }
        }
        logger.info("ClassMate application exited.");
    }

    /**
     * Configures the application's logging settings.
     * For v2.1, this silences console logs to keep the CLI clean for the user.
     * To edit method to enable logging when needed
     */
    private static void setupLogger() {
        LogManager.getLogManager().reset();
        Logger globalLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        // Change to Level.ALL if logging is needed for debugging
        globalLogger.setLevel(Level.OFF);
    }
}
