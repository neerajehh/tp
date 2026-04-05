package seedu.classmate.commands;

import java.util.logging.Logger;

import seedu.classmate.Major;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Storage;
import seedu.classmate.Ui;
import seedu.classmate.UserProfile;

// this command removes a specialisation from user profile
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
        // parallel logic from setSpecialisationCommand
        // call on method to removeSpecialisation from UserProfile class
    }
}
