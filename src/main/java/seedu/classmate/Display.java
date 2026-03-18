package seedu.classmate;

/**
 * Handles all user interface interactions for the ClassMate application.
 * This class is responsible for displaying messages to the user.
 */
public class Display {

    /**
     * Displays the welcome message and application logo.
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
    }

    /**
     * Displays the goodbye message when the program exits.
     */
    public void showGoodbye() {
        System.out.println("Goodbye! Happy course planning!");
    }

    /**
     * Prints the list of ClassMate commands and their descriptions
     * to help users on how to use the application
     */
    public static void displayHelp() {
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
    }

}
