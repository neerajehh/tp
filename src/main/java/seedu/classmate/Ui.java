package seedu.classmate;

import java.util.ArrayList;

/**
 * Handles all user interface interactions for the ClassMate application.
 * This class is responsible for displaying messages to the user.
 */
public class Ui {
    private final java.util.Scanner in;

    public Ui() {
        this.in = new java.util.Scanner(System.in);
    }

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

    public static void showGoodbye() {
        System.out.println("Goodbye! Happy course planning!");
        printLine();
    }

    public static void showHelp() {
        System.out.println("Available commands:\n" +
                "- help: " +
                "Viewing help\n" +
                "- viewGradReqs: " +
                "Print CEG graduation requirements\n" +
                "- viewPrereqs MODULE_CODE: " +
                "View the prerequisite tree of a specific module\n" +
                "- viewModuleInfo MODULE_CODE: " +
                "Show details for a specific module\n" +
                "- queryModuleAvailability MODULE_CODE SEM<1/2>: " +
                "Check if a module MODULE_CODE is available in a certain semester SEM<1/2>\n" +
                "- viewSpecialisations: " +
                "View list of all CEG specialisations\n" +
                
                "- checkPrereqStatus MODULE_CODE: Check prereq completion status\n" +
                "- viewSpecialisationInfo SPECIALISATION_INDEX: " +
                "View specific requirements for a specialisation. See viewSpecialisation for index");
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
}
