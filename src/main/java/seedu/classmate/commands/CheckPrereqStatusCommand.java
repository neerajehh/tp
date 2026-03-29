package seedu.classmate.commands;
// @@author neerajehh

import seedu.classmate.ClassMateException;
import seedu.classmate.Major;
import seedu.classmate.Module;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Ui;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Represents a command that checks the prerequisite completion status of a specified module.
 * Displays which prerequisites the user has completed and which are still outstanding.
 */
public class CheckPrereqStatusCommand extends Command {

    private static final Logger logger = Logger.getLogger(CheckPrereqStatusCommand.class.getName());
    private final String moduleCode;
    private final ArrayList<String> completedModules;

    /**
     * Constructs a {@code CheckPrereqStatusCommand} with the given module code
     * and the list of completed modules.
     *
     * @param args             The module code entered by the user.
     * @param completedModules The list of modules the user has completed.
     */
    public CheckPrereqStatusCommand(String args, ArrayList<String> completedModules) {
        assert args != null : "Args should not be null";
        assert completedModules != null : "Completed modules list should not be null";
        this.moduleCode = args.trim().toUpperCase();
        this.completedModules = completedModules;
    }

    /**
     * Executes the command by checking and displaying the prerequisite completion
     * status of the specified module.
     *
     * @param major                  The {@code Major} instance containing module data.
     * @param ui                     The {@code Ui} handler (unused).
     * @param specialisationOverview The overview of specialisations (unused).
     * @throws ClassMateException If the module code is empty or the module is not found.
     */
    @Override
    public void executeCommand(Major major, Ui ui, SpecialisationOverview specialisationOverview) {
        if (moduleCode.isEmpty()) {
            throw new ClassMateException(
                    "Please provide a module code. Format: checkPrereqStatus <MODULE_CODE>");
        }

        logger.info("Checking prereq status for: " + moduleCode);

        Module module = major.findModule(moduleCode);

        if (module == null) {
            throw new ClassMateException("Module not found: " + moduleCode);
        }

        ArrayList<String> prerequisites = module.getPrerequisites();

        if (prerequisites.isEmpty()) {
            System.out.println(moduleCode + " has no prerequisites. You can take it anytime!");
            return;
        }

        ArrayList<String> completed = new ArrayList<>();
        ArrayList<String> notCompleted = new ArrayList<>();

        for (String prereq : prerequisites) {
            if (completedModules.contains(prereq)) {
                completed.add(prereq);
            } else {
                notCompleted.add(prereq);
            }
        }

        printPrereqStatus(completed, notCompleted);
    }

    /**
     * Prints the prerequisite completion status to the user.
     * Shows completed prerequisites and outstanding prerequisites separately.
     *
     * @param completed    List of completed prerequisite module codes.
     * @param notCompleted List of prerequisite module codes not yet completed.
     */
    private void printPrereqStatus(ArrayList<String> completed, ArrayList<String> notCompleted) {
        assert completed != null : "Completed list should not be null";
        assert notCompleted != null : "Not completed list should not be null";

        System.out.println("Prerequisite Status for " + moduleCode + ":");
        Ui.printLine();

        if (!completed.isEmpty()) {
            System.out.println("Completed prerequisites:");
            for (String prereq : completed) {
                System.out.println("  [DONE] " + prereq);
            }
        }

        if (!notCompleted.isEmpty()) {
            System.out.println("Outstanding prerequisites:");
            for (String prereq : notCompleted) {
                System.out.println("  [TODO] " + prereq);
            }
        }

        Ui.printLine();

        if (notCompleted.isEmpty()) {
            System.out.println("You have completed all prerequisites for " + moduleCode + "!");
        } else {
            System.out.println("You still need to complete " + notCompleted.size()
                    + " prerequisite(s) before taking " + moduleCode + ".");
        }
    }
}

// @@author
