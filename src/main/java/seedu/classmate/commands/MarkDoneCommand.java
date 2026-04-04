package seedu.classmate.commands;

import seedu.classmate.ClassMateException;
import seedu.classmate.Ui;
import seedu.classmate.Major;
import seedu.classmate.Module;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Storage;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Marks a module as completed by the user.
 */
// @@author neerajehh
public class MarkDoneCommand extends Command {
    private static final Logger logger = Logger.getLogger(MarkDoneCommand.class.getName());
    private final String moduleCode;
    private final ArrayList<String> completedModules;
    private final Storage storage;

    /**
     * Constructs a MarkDoneCommand with the given module code.
     *
     * @param args The module code to mark as done.
     * @param completedModules The list of completed modules.
     * @param storage The storage object to save completed modules.
     */
    public MarkDoneCommand(String args, ArrayList<String> completedModules, Storage storage) {
        assert args != null : "Args should not be null";
        assert completedModules != null : "Completed modules list should not be null";
        assert storage != null : "Storage should not be null";
        this.moduleCode = args.trim().toUpperCase();
        this.completedModules = completedModules;
        this.storage = storage;
    }

    @Override
    public void executeCommand(Major major, Ui display, SpecialisationOverview specOverview) {
        if (moduleCode.isEmpty()) {
            throw new ClassMateException("Please provide a module code. Format: markDone <MODULE_CODE>");
        }
        Module module = major.findModule(moduleCode);
        if (module == null) {
            System.out.println("Module " + moduleCode + " not found.");
            return;
        }
        if (completedModules.contains(moduleCode)) {
            System.out.println(moduleCode + " is already marked as done.");
            return;
        }
        completedModules.add(moduleCode);
        storage.save(completedModules);
        logger.info("Marked " + moduleCode + " as done.");
        System.out.println("Successfully marked " + moduleCode + " as done!");
    }
}
// @@author
