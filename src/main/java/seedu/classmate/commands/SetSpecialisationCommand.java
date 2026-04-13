package seedu.classmate.commands;

import seedu.classmate.Major;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Storage;
import seedu.classmate.Ui;
import seedu.classmate.UserProfile;
import seedu.classmate.ClassMateException;
import seedu.classmate.Specialisation;

import java.util.logging.Logger;
// @@author Michael-coding06
/**
 * Represents a command to set the user's academic specialisation.
 */
public class SetSpecialisationCommand extends Command {

    private static final Logger logger = Logger.getLogger(SetSpecialisationCommand.class.getName());
    private final String newSpecialisation;
    private final UserProfile userProfile;
    private final Storage storage;

    public SetSpecialisationCommand(String args, UserProfile userProfile, Storage storage) {
        this.newSpecialisation = args.trim();
        this.userProfile = userProfile;
        this.storage = storage;
    }

    @Override
    public void executeCommand(Major major, Ui ui, SpecialisationOverview specialisationOverview) {
        if (newSpecialisation.isEmpty()) {
            throw new ClassMateException("Please provide a specialisation name. " +
                                         "Format: setspecialisation <NAME>");
        }

        logger.info("Setting specialisation to: " + newSpecialisation);

        Specialisation spec = specialisationOverview.getSpecialisationByName(newSpecialisation);
        if (spec == null) {
            throw new ClassMateException("Invalid specialisation name.");
        }

        // Update the profile (this automatically saves to specialisation.txt)
        userProfile.addSpecialisation(spec.getSpecialisationName());
        storage.saveUserProfile(userProfile);

        System.out.println("Success! Your specialisation has been set to: " + newSpecialisation);
        Ui.printLine();
    }
}
// @@author
