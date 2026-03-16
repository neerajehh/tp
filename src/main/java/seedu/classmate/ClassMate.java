package seedu.classmate;

import java.util.Scanner;
import java.util.logging.Logger;

public class ClassMate {
    private static final Logger logger = Logger.getLogger(ClassMate.class.getName());
    private static SpecialisationOverview specOverview = new SpecialisationOverview();
    private static final Display display = new Display();

    /**
     * Main entry-point for the java.classmate.Classmate application.
     */
    public static void main(String[] args) {
        display.showWelcome();
        logger.info("ClassMate application started.");
        Scanner in = new Scanner(System.in);
        Major major = new Major();
        assert major != null : "Major should not be null";
        while (true) {
            String input = in.nextLine();
            assert input != null : "Input should not be null";
            logger.info("User input: " + input);
            try {
                String trimmedInput = input.trim();
                assert trimmedInput != null : "Command should not be null";
                String commandWord = trimmedInput.split("\\s+")[0];
                String arguments = trimmedInput.length() > commandWord.length()
                        ? input.trim().substring(commandWord.length()).trim()
                        : "";

                switch (commandWord) {
                case "help":
                    Parser.displayHelp();
                    break;

                case "":
                    throw new ClassMateException("Please input a non-empty command!");

                case "bye":
                    display.showGoodbye();
                    logger.info("ClassMate application exited.");
                    return;

                case "viewGradReq":
                    System.out.println(major);
                    break;

                case "prereq":
                    if (arguments.isEmpty()) {
                        throw new ClassMateException("Please provide a module code. Format: prereq <module code>");
                    }
                    // assert arguments != null : "Module code should not be null";
                    logger.info("Fetching prereqs for: " + arguments);
                    Module module = major.findModule(arguments);
                    if (module != null) {
                        System.out.println(module.printPrereqTree(major));
                    } else {
                        System.out.println("Module " + arguments + " not found.");
                    }
                    break;

                case "printModuleInfo":
                    if (arguments.isEmpty()) {
                        throw new ClassMateException("Please provide a module code. " +
                                "Format: printModuleInfo <module code>");
                    }
                    String[] moduleCodes = arguments.split("\\s+");
                    assert moduleCodes.length > 0 : "Module codes should not be empty";
                    logger.info("Printing module info for: " + arguments);
                    System.out.println("Module Info for " + arguments);
                    for (String code : moduleCodes) {
                        Module m = major.findModule(code.trim());
                        if (m != null) {
                            System.out.println(m.printInfo());
                        } else {
                            System.out.println("Module " + code + " not found.");
                        }
                    }
                    break;

                case "queryModuleAvailability":
                    String[] availArgs = arguments.split("\\s+");
                    logger.info("Checking availability for: " + arguments);
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
                    System.out.println("List of all CEG Specialisations:");
                    specOverview.listAllSpecialisations();
                    break;
                case "view":
                    try {
                        int specNumber = Integer.parseInt(arguments.trim());
                        Specialisation selectedSpecialisation = specOverview.getSpecialisationDetails(specNumber);
                        specOverview.displaySpecialisationDetails(selectedSpecialisation);
                        //System.out.println(selectedSpecialisation);
                    } catch (ClassMateException e) {
                        System.err.println(e.getMessage());
                    }
                    break;

                case "specialisation":
                    if (arguments.isEmpty()) {
                        System.out.println("Please provide a specialisation name. " +
                                "Format: specialisations_info <specialisation name>");
                        break;
                    }
                    assert arguments != null : "Specialisation name should not be null";
                    logger.info("Fetching specialisation: " + arguments);
                    System.out.println("Listing details for " + arguments + "\n");
                    Specialisation spec = new Specialisation(arguments);
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

}
