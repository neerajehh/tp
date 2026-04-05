package seedu.classmate.commands;

import seedu.classmate.ClassMateException;
import seedu.classmate.Ui;
import seedu.classmate.Major;
import seedu.classmate.Module;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Storage;
import seedu.classmate.UserProfile;

import java.util.logging.Logger;

/**
 * Marks a module as completed by the user.
 */
// @@author neerajehh
public class MarkDoneCommand extends Command {
    private static final Logger logger = Logger.getLogger(MarkDoneCommand.class.getName());
    private final String moduleCode;
    private final UserProfile userProfile;
    private final Storage storage;

    /**
     * Constructs a MarkDoneCommand with the given module code.
     *
     * @param args The module code to mark as done.
     * @param userProfile The user's profile containing their progress.
     * @param storage The storage object to save completed modules.
     */
    public MarkDoneCommand(String args, UserProfile userProfile, Storage storage) {
        assert args != null : "Args should not be null";
        assert userProfile != null : "UserProfile should not be null";
        assert storage != null : "Storage should not be null";
        this.moduleCode = args.trim().toUpperCase();
        this.userProfile = userProfile;
        this.storage = storage;
    }

    @Override
    public void executeCommand(Major major, Ui display, SpecialisationOverview specOverview) {
        // Guard clause to check if invalid module input
        if (moduleCode.isEmpty()) {
            throw new ClassMateException("Please provide a module code. Format: markDone <MODULE_CODE>");
        }

        // Guard clause to check if module is not under Major
        Module module = major.findModule(moduleCode);
        if (module == null) {
            throw new ClassMateException("Module " + moduleCode + " not found in the curriculum.");
        }

        // Module exists and can be marked as done
        userProfile.markModuleDone(moduleCode); // Throws ClassMateException if already done

        logger.info("Marked " + moduleCode + " as done.");
        System.out.println("Successfully marked " + moduleCode + " as done!");
    }
}
// @@author
