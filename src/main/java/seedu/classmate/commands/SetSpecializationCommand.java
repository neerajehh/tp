package seedu.classmate.commands;

import seedu.classmate.Major;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Ui;
import seedu.classmate.UserProfile;
import seedu.classmate.ClassMateException;

import java.util.logging.Logger;

/**
 * Represents a command to set the user's academic specialization.
 */
public class SetSpecializationCommand extends Command {

    private static final Logger logger = Logger.getLogger(SetSpecializationCommand.class.getName());
    private final String newSpecialization;
    private final UserProfile userProfile;

    public SetSpecializationCommand(String args, UserProfile userProfile) {
        this.newSpecialization = args.trim();
        this.userProfile = userProfile;
    }

    @Override
    public void executeCommand(Major major, Ui ui, SpecialisationOverview specialisationOverview) {
        if (newSpecialization.isEmpty()) {
            throw new ClassMateException("Please provide a specialization name. " +
                                         "Format: setspecialization <NAME>");
        }

        logger.info("Setting specialization to: " + newSpecialization);
        
        // Update the profile (this automatically saves to specialization.txt)
        userProfile.setSpecialization(newSpecialization);

        System.out.println("Success! Your specialization has been set to: " + newSpecialization);
        Ui.printLine();
    }
}
