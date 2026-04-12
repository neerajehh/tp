package seedu.classmate.commands;

import seedu.classmate.ClassMateException;
import seedu.classmate.Ui;
import seedu.classmate.Major;
import seedu.classmate.Module;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Storage;
import seedu.classmate.UserProfile;

import java.util.ArrayList;
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
        if (moduleCode.isEmpty()) {
            throw new ClassMateException("Please provide a module code. Format: markDone <MODULE_CODE>");
        }

        Module module = major.findModule(moduleCode);
        if (module == null) {
            module = specOverview.findSpecialisationModule(moduleCode);
        }
        if (module == null) {
            throw new ClassMateException("Module " + moduleCode + " not found in the curriculum.");
        }

        ArrayList<String> prerequisites = module.getPrerequisites();
        ArrayList<String> completedModules = userProfile.getUserCompletedModules();
        ArrayList<String> missingPrereqs = new ArrayList<>();
        for (String prereq : prerequisites) {
            if (!completedModules.contains(prereq)) {
                missingPrereqs.add(prereq);
            }
        }
        if (!missingPrereqs.isEmpty()) {
            throw new ClassMateException("Cannot mark " + moduleCode
                    + " as done. You have not completed the following prerequisite(s): "
                    + String.join(", ", missingPrereqs));
        }

        userProfile.markModuleDone(moduleCode);
        storage.saveUserProfile(userProfile);

        logger.info("Marked " + moduleCode + " as done.");
        System.out.println("Successfully marked " + moduleCode + " as done!");
    }
}
// @@author
