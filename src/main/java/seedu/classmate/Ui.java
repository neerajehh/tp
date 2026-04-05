package seedu.classmate;

import java.util.ArrayList;

/**
 * Handles all user interface interactions for the ClassMate application.
 * This class is responsible for displaying messages to the user.
 */
public class Ui {
    private final java.util.Scanner in;

    /**
     * Constructs a {@code Ui} object and initializes the scanner to read from standard input.
     */
    public Ui() {
        this.in = new java.util.Scanner(System.in);
    }

    /**
     * Prints a standardized separator line to the console to visually group output.
     */
    public static void printLine() {
        System.out.println("_".repeat(30));
    }

    /**
     * Prints the input prompt and reads the user's raw command.
     * This allows for distinction between user input and program output, enhancing Ui.
     * @return The raw string input, or null if there is no more input.
     */
    public String readCommand() {
        printLine();
        System.out.print("ClassMate > "); // Input prompt '>'
        if (in.hasNextLine()) {
            return in.nextLine();
        }
        return null; // Signal the main loop to break
    }

    /**
     * Displays the application's welcome logo and initial greeting to the user.
     */
    public void showWelcome() {
        String logo = "  ____ _               __  __       _\n"
                + " / ___| | __ _ ___ ___|  \\/  | __ _| |_ ___\n"
                + "| |   | |/ _` / __/ __| |\\/| |/ _` | __/ _ \\\n"
                + "| |___| | (_| \\__ \\__ \\ |  | | (_| | ||  __/\n"
                + " \\____|_|\\__,_|___/___/_|  |_|\\__,_|\\__\\___|\n";

        System.out.println("Welcome to\n" + logo);
        System.out.println("I'm your CEG course planning assistant!");
        System.out.println("Type 'help' to see available commands.");
        printLine();
    }

    /**
     * Displays the farewell message when the user exits the application.
     */
    public static void showGoodbye() {
        System.out.println("Goodbye! Happy course planning!");
        printLine();
    }

    /**
     * Prints list of available commands and their descriptions to guide the user.
     */
    public static void showHelp() {
        System.out.println("""
                Available commands:
                - help: Viewing help
                - bye: Exit the application
                - viewGradReqs: Print CEG graduation requirements
                - viewPrereqs MODULE_CODE: View the prerequisite tree of a specific module
                - viewModuleInfo MODULE_CODE: Show details for a specific module
                - queryModuleAvailability MODULE_CODE SEM<1/2>: 
                    Check if a module MODULE_CODE is available in a certain semester SEM<1/2>
                - viewSpecialisations: View list of all CEG specialisations
                - viewSpecialisationInfo SPECIALISATION_INDEX: View specific requirements for a specialisation. 
                                                               See viewSpecialisations for index
                - checkPrereqStatus MODULE_CODE: Check prereq completion status
                
                --- Profile & Progress Tracking ---
                - markDone MODULE_CODE: Mark a specific module as completed
                - viewDone: View a list of all your completed modules
                - setSpecialisation NAME: Add an academic specialisation to your profile (You can select up to 2)
                - removeSpecialisation NAME: Remove a specialisation from your profile
                - checkProfile: View your comprehensive academic profile, 
                                including remaining major core and specialisation progress""");
        printLine();
    }

    /**
     * Prints a standardized error message to the user.
     * * DESIGN RATIONALE: Adheres to the Single Responsibility Principle (SRP) by
     * decoupling error detection from error presentation. Logic classes (Parser/Command)
     * throw {@code ClassMateException}, the coordinator class catches it, and this
     * method handles the final string formatting for the user.
     *
     * @param errorMessage The specific error detail to be displayed. Must not be null.
     */
    public void showError(String errorMessage) {
        // Assertion: Ensure error handler layer provides a message
        assert errorMessage != null : "Error message passed to UI should not be null";

        printLine();
        System.out.println("    (!) ERROR: " + errorMessage);
        printLine();
    }

    /**
     * Formats and prints the list of CEG major graduation requirements to the console.
     * If the provided list is empty, it notifies the user accordingly.
     *
     * @param coreModules The list of core {@code Module} objects to be displayed.
     */
    public void showGraduationRequirements(ArrayList<Module> coreModules) {
        printLine();
        if (coreModules.isEmpty()) {
            System.out.println("Core Modules List is currently empty.");
        } else {
            System.out.println("Here is a list of modules required to fulfill CEG Major:\n");
            for (Module m : coreModules) {
                System.out.println(m.getModuleCode() + " " + m.getModuleName());
            }
        }
        printLine();
    }

    //@@author VinayVR26
    /**
     * Displays all available specialisations to the user. Each specialisation is printed with a corresponding
     * number that can be used by the user to select and view more details about a specific specialisation.
     *
     * @param specs The list of specialisations
     */
    public static void showAllSpecialisations(ArrayList<Specialisation> specs) {
        printLine();
        System.out.println("List of all CEG Specialisations:");
        for (int specialisationIndex = 0; specialisationIndex < specs.size(); specialisationIndex++) {
            System.out.println((specialisationIndex + 1) + ". "
                    + specs.get(specialisationIndex).getSpecialisationName());
        }
        System.out.println("Enter <viewSpecialisationInfo [index]> to know more about a specialisation.");
        printLine();
    }

    /**
     * Retrieves the specialisation corresponding to the given number.
     *
     * @param selectedSpecialisation The {@code Specialisation} object representing the desired specialisation.
     */
    public void showSpecialisationDetails(Specialisation selectedSpecialisation) {
        printLine();
        System.out.println("Specialisation: " + selectedSpecialisation.getSpecialisationName() + "\n");
        System.out.println("Description: " + selectedSpecialisation.getSpecialisationDescription() + "\n");

        System.out.println("Core Modules:");
        for (Module coreModule : selectedSpecialisation.getSpecialisationCoreModules()) {
            System.out.println(coreModule.getModuleCode() + " : " + coreModule.getModuleName());
        }

        System.out.println();

        System.out.println("Elective Modules " + "(" + selectedSpecialisation.getElectiveRequirements() + "):");
        for (Module electiveModule : selectedSpecialisation.getSpecialisationElectiveModules()) {
            System.out.println(electiveModule.getModuleCode() + " : " + electiveModule.getModuleName());
        }
        printLine();
    }
    //@@author

    // @@author lauwn-mower
    // Helper methods for CheckProfile
    /**
     * Prints the user's completed modules alongside the remaining core major requirements.
     *
     * @param completed          A list of module codes the user has successfully completed.
     * @param remainingMajorCore A list of major core {@code Module} objects the user has yet to clear.
     */
    public void showBasicProfile(ArrayList<String> completed, ArrayList<Module> remainingMajorCore) {
        printLine();
        System.out.println("Your Academic Profile:");
        printLine();

        System.out.println("[COMPLETED MODULES]");
        if (completed.isEmpty()) {
            System.out.println("  - No modules marked as completed yet.");
        } else {
            for (String code : completed) {
                System.out.println("  [DONE] " + code);
            }
        }
        System.out.println();

        System.out.println("[MAJOR CORE REQUIREMENTS REMAINING]");
        if (remainingMajorCore.isEmpty()) {
            System.out.println("  - All Major Core requirements cleared! Great job!");
        } else {
            for (Module m : remainingMajorCore) {
                System.out.println("  - " + m.getModuleCode() + " : " + m.getModuleName());
            }
        }
        System.out.println();
    }

    /**
     * Prints the completion progress for a single specialisation, tracking both core and elective requirements.
     *
     * @param spec                    The {@code Specialisation} currently being tracked.
     * @param remainingCore           A list of core {@code Module} objects remaining for this specialisation.
     * @param remainingElectives      A list of available elective {@code Module} options the user can still take.
     * @param completedElectivesCount The integer count of specialisation electives the user has already cleared.
     */
    public void showSpecialisationProgress(Specialisation spec, ArrayList<Module> remainingCore,
                                           ArrayList<Module> remainingElectives, int completedElectivesCount) {
        printLine();
        System.out.println("SPECIALISATION: [" + spec.getSpecialisationName().toUpperCase() + "]");
        System.out.println();

        // Show progress regarding core spec modules
        System.out.println("Core Modules Remaining:");
        if (remainingCore.isEmpty()) {
            System.out.println("  - All Specialisation Core requirements cleared!");
        } else {
            for (Module m : remainingCore) {
                System.out.println("  - " + m.getModuleCode() + " : " + m.getModuleName());
            }
        }
        System.out.println();

        // Show progress regarding elective spec modules
        // Logic: Show completed electives and list of incomplete options user can consider
        System.out.println("Elective Modules (Completed: " + completedElectivesCount + ")");
        System.out.println("Requirement: " + spec.getElectiveRequirements());
        System.out.println("Remaining Options:");
        if (remainingElectives.isEmpty()) {
            System.out.println("  - No more electives available to take.");
        } else {
            for (Module m : remainingElectives) {
                System.out.println("  - " + m.getModuleCode() + " : " + m.getModuleName());
            }
        }
        printLine();
    }

    // @@author
}
