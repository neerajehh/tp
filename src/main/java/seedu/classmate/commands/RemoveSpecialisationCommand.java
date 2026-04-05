package seedu.classmate.commands;

import java.util.logging.Logger;

import seedu.classmate.ClassMateException;
import seedu.classmate.Major;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Storage;
import seedu.classmate.Ui;
import seedu.classmate.UserProfile;

public class RemoveSpecialisationCommand extends Command {

    private static final Logger logger = Logger.getLogger(RemoveSpecialisationCommand.class.getName());
    private final String specToRemove;
    private final UserProfile userProfile;
    private final Storage storage;

    public RemoveSpecialisationCommand(String args, UserProfile userProfile, Storage storage) {
        this.specToRemove = args.trim();
        this.userProfile = userProfile;
        this.storage = storage;
    }

    @Override
    public void executeCommand(Major major, Ui ui, SpecialisationOverview specialisationOverview) {
        if (specToRemove.isEmpty()) {
            throw new ClassMateException("Please provide a specialisation name. " +
                    "Format: removespecialisation <NAME>");
        }

        logger.info("Removing specialisation from profile: " + specToRemove);

        userProfile.removeSpecialisation(specToRemove);

        storage.saveUserProfile(userProfile);

        System.out.println("    Success! Removed specialisation: " + specToRemove);
        System.out.println("    You now have "
                                + userProfile.getUserSpecialisations().size()
                                + " specialisation(s) selected.");
    }
}
