package seedu.classmate.commands;

import seedu.classmate.ClassMateException;
import seedu.classmate.Ui;
import seedu.classmate.Major;
import seedu.classmate.SpecialisationOverview;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Displays the list of completed modules.
 */
// @@author neerajehh
public class ViewDoneCommand extends Command {
    private static final Logger logger = Logger.getLogger(ViewDoneCommand.class.getName());
    private final ArrayList<String> completedModules;
    private final String args;

    /**
     * Constructs a ViewDoneCommand.
     *
     * @param completedModules The list of completed modules to display.
     * @param args             Any extra arguments provided by the user (should be empty).
     */
    public ViewDoneCommand(ArrayList<String> completedModules, String args) {
        assert completedModules != null : "Completed modules list should not be null";
        this.completedModules = completedModules;
        this.args = args.trim();
    }

    @Override
    public void executeCommand(Major major, Ui display, SpecialisationOverview specOverview) {
        if (!args.isEmpty()) {
            throw new ClassMateException("Format: viewDone (no arguments needed)");
        }
        logger.info("Viewing completed modules.");
        if (completedModules.isEmpty()) {
            System.out.println("You have not marked any modules as done yet.");
            return;
        }
        System.out.println("Completed modules:");
        for (String moduleCode : completedModules) {
            System.out.println("- " + moduleCode);
        }
    }
}
// @@author
