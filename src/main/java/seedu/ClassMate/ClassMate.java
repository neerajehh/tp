package seedu.ClassMate;

import java.util.Scanner;

public class ClassMate {
    /**
     * Main entry-point for the java.classmate.Classmate application.
     */
    public static void main(String[] args) {
        String logo = "  ____ _               __  __       _\n"
                + " / ___| | __ _ ___ ___|  \\/  | __ _| |_ ___\n"
                + "| |   | |/ _` / __/ __| |\\/| |/ _` | __/ _ \\\n"
                + "| |___| | (_| \\__ \\__ \\ |  | | (_| | ||  __/\n"
                + " \\____|_|\\__,_|___/___/_|  |_|\\__,_|\\__\\___|\n";

        System.out.println("Welcome to\n" + logo);
        System.out.println("I'm your CEG course planning assistant!");
        System.out.println("Type 'help' to see available commands.");

        Scanner in = new Scanner(System.in);
        
        while (true) {
            String input = in.nextLine();

            if (input.equalsIgnoreCase("help")) {
                printHelp();
            } else if (input.equalsIgnoreCase("bye")) {
                System.out.println("Goodbye! Happy course planning!");
                break;
            } else {
                System.out.println("Unknown command. Type 'help' to see available commands.");
            }
        }
    }

    /**
     * Prints the list of ClassMate commands and their descriptions
     * to help users on how to use the application
     */
    private static void printHelp() {
        System.out.println("List of commands:");

        System.out.println("Command: view grad req");
        System.out.println("- Print CEG graduation requirements");

        System.out.println("Command: prereq <module code>");
        System.out.println("- Show prerequisites for a module");

        System.out.println("Command: module <module code>");
        System.out.println("- Show information for a module");

        System.out.println("Command: available <module code> <semester>");
        System.out.println("- Check if module is available in a specific semester");

        System.out.println("Command: specialisations");
        System.out.println("- List all CEG specialisations");

        System.out.println("Command: specialisation <name>");
        System.out.println("- Show detailed info for a specialisation");

        System.out.println("Command: bye");
        System.out.println("- Exit ClassMate");

    }
}
