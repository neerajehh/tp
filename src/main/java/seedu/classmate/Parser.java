package seedu.classmate;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses user input commands and returns the corresponding command description.
 * Maintains a mapping of valid commands to their descriptions.
 * The {@code parseCommand(String input)} method processes user input,
 * validates it, and checks whether it matches any known command.
 * If a valid command is found, the associated description is returned, else
 * return 'Unknown Message'.
 */
public class Parser {

    private static final Map<String, String> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("help", "Viewing help");
        COMMANDS.put("viewGradReq", "Print CEG graduation requirements");
        COMMANDS.put("printModuleInfo", "Print information about a specific module");
        COMMANDS.put("prereq", "Print module prerequisites");
        COMMANDS.put("viewModAvail", "Query module availability");
        COMMANDS.put("specialisations", "Print CEG specialisations");
        COMMANDS.put("specialisation", "Print specific CEG specialisation info");
    }

    /**
     * Parses the user's input and determines whether it matches a valid command.
     *
     * @param input The raw command string entered by the user.
     * @return The description associated with the command if it exists,
     *         or a message associated with the command if it exists.
     * @throws ClassMateException If the input provided is empty after trimming.
     */
    public static String parseCommand(String input) {

        String trimmed = input.trim();

        if (trimmed.isEmpty()) {
            throw new ClassMateException("Please enter a non-empty input!");
        }

        if (COMMANDS.containsKey(trimmed)) {
            return COMMANDS.get(trimmed);
        }

        return "Unknown command. Enter 'help for available commands.";
    }

    /**
     * Prints the list of ClassMate commands and their descriptions
     * to help users on how to use the application
     */
    public static void displayHelp() {
        System.out.println("Available commands:");
        System.out.println("- help: Viewing help");
        System.out.println("- view: Print CEG graduation requirements: " +
                "view course requirements and specialisations");
        System.out.println("- check: Query for module prerequisites: " +
                "check what prerequisites are needed to take the module");
        System.out.println("- show: Print module info: show details for a specific module");
        System.out.println("- query: Query module availability: " +
                "Boolean return yes or no if module can be taken in a certain semester");
        System.out.println("- specialisations: Print CEG specialisations: " +
                "view list of specialisations");
        System.out.println("- specialisations_info: Print CEG specialisations info: " +
                "view course specialisations and requirements in more detail");
    }
}
