package seedu.classmate;

import java.util.Scanner;
import java.util.logging.Logger;

public class ClassMate {
    private static final Logger logger = Logger.getLogger(ClassMate.class.getName());

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
        logger.info("ClassMate application started.");
        Scanner in = new Scanner(System.in);
        Major major = new Major();
        assert major != null : "Major should not be null";
        while (true) {
            String input = in.nextLine();
            assert input != null : "Input should not be null";
            logger.info("User input: " + input);
            try {
                Command command = Parser.parse(input);
                assert command != null : "Command should not be null";
                switch (command.getCommandWord()) {
                case "help":
                    printHelp();
                    break;
                case "bye":
                    goodbyeMessage();
                    logger.info("ClassMate application exited.");
                    return;
                case "viewGradReq":
                    System.out.println(major);
                    break;
                case "prereq":
                    String moduleCode = command.getArgs();
                    assert moduleCode != null : "Module code should not be null";
                    logger.info("Fetching prereqs for: " + moduleCode);
                    Module module = major.findModule(moduleCode);
                    System.out.println(module.printPrereqTree(major));
                    break;
                case "printmoduleinfo":
                    String[] moduleCodes = command.getArgs().split("\\s+");
                    assert moduleCodes.length > 0 : "Module codes should not be empty";
                    logger.info("Printing module info for: " + command.getArgs());
                    System.out.println("Module Info for " + command.getArgs());
                    for (String code : moduleCodes) {
                        Module m = major.findModule(code.trim());
                        if (m != null) {
                            System.out.println(m.printInfo());
                        } else {
                            System.out.println("Module " + code + " not found.");
                        }
                    }
                    break;
                case "querymoduleavailability":
                    String[] availArgs = command.getArgs().split("\\s+");
                    logger.info("Checking availability for: " + command.getArgs());
                    if (availArgs.length < 2) {
                        throw new ClassMateException("Format: queryModuleAvailability <module code> <sem1/sem2>");
                    }
                    assert availArgs.length >= 2 : "Availability check requires module code and semester";
                    Module availModule = major.findModule(availArgs[0].trim());
                    if (availModule != null) {
                        System.out.println(availModule.checkAvailability(availArgs[1].trim()));
                    } else {
                        System.out.println("Module " + availArgs[0] + " not found.");
                    }
                    break;
                case "specialisations":
                    System.out.println("Listing all specifications:");
                    break;
                case "specialisation":
                    String specialisationName = command.getArgs();
                    assert specialisationName != null : "Specialisation name should not be null";
                    logger.info("Fetching specialisation: " + specialisationName);
                    System.out.println("Listing details for " + specialisationName);
                    Specialisation spec = new Specialisation(specialisationName);
                    System.out.println(spec);
                    break;
                default:
                    throw new ClassMateException("Unknown command.");
                }
            } catch (ClassMateException e) {
                logger.warning("ClassMateException: " + e.getMessage());
                System.out.println(e.getMessage());
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
        System.out.println("Command: printModuleInfo <module code(s)>");
        System.out.println("- Show information for one or more modules");
        System.out.println("Command: queryModuleAvailability <module code> <sem1/sem2>");
        System.out.println("- Check if a module is available in a specific semester");
        System.out.println("Command: specialisations");
        System.out.println("- List all CEG specialisations");
        System.out.println("Command: specialisation <name>");
        System.out.println("- Show detailed info for a specialisation");
        System.out.println("Command: bye");
        System.out.println("- Exit ClassMate");
    }

    private static void goodbyeMessage() {
        System.out.println("Goodbye! Happy course planning!");
    }
}

