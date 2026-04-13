package seedu.classmate.commands;

import seedu.classmate.ClassMateException;
import seedu.classmate.Major;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Storage;
import seedu.classmate.Ui;
import seedu.classmate.UserProfile;

import java.util.logging.Logger;

// @@author neerajehh
public class UnmarkDoneCommand extends Command {
    private static final Logger logger = Logger.getLogger(UnmarkDoneCommand.class.getName());
    private final String moduleCode;
    private final UserProfile userProfile;
    private final Storage storage;

    public UnmarkDoneCommand(String args, UserProfile userProfile, Storage storage) {
        this.moduleCode = args.trim().toUpperCase();
        this.userProfile = userProfile;
        this.storage = storage;
    }

    @Override
    public void executeCommand(Major major, Ui display, SpecialisationOverview specOverview) {
        if (moduleCode.isEmpty()) {
            throw new ClassMateException("Please provide a module code. Format: unmarkDone <MODULE_CODE>");
        }
        userProfile.unmarkModuleDone(moduleCode);
        storage.saveUserProfile(userProfile);
        logger.info("Unmarked " + moduleCode + " as done.");
        System.out.println("Successfully unmarked " + moduleCode + " from your completed modules!");
    }
}
// @@author
