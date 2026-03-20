package seedu.classmate;

/**
 * Handles all user interface interactions for the ClassMate application.
 * This class is responsible for displaying messages to the user.
 */
public class Ui {

    public static void printLine() {
        System.out.println("_".repeat(30));
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

    //TODO: major, module, specialisation related printing

}
