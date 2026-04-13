package seedu.classmate.commands;

import java.util.logging.Logger;

import seedu.classmate.ClassMateException;
import seedu.classmate.Major;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Storage;
import seedu.classmate.Ui;
import seedu.classmate.UserProfile;

/**
 * Represents a command to remove an academic specialisation from the user's profile.
 * This command updates the in-memory user profile and persists the changes to the disk.
 */
public class RemoveSpecialisationCommand extends Command {

    private static final Logger logger = Logger.getLogger(RemoveSpecialisationCommand.class.getName());
    private final String specToRemove;
    private final UserProfile userProfile;
    private final Storage storage;

    /**
     * Constructs a {@code RemoveSpecialisationCommand} with the specified arguments.
     *
     * @param args        The raw string input containing the name of the specialisation to remove.
     * @param userProfile The {@code UserProfile} object containing the user's current academic state.
     * @param storage     The {@code Storage} object responsible for saving the updated profile to disk.
     */
    public RemoveSpecialisationCommand(String args, UserProfile userProfile, Storage storage) {
        this.specToRemove = args.trim();
        this.userProfile = userProfile;
        this.storage = storage;
    }

    /**
     * Executes the command to remove the specified specialisation.
     * It validates the user input, updates the {@code UserProfile} model, saves the new state
     * via the {@code Storage} component, and outputs a success message to the console.
     *
     * @param major                  The {@code Major} instance containing
     *                               curriculum requirements (unused in this command).
     * @param ui                     The {@code Ui} handler for
     *                               user interactions (unused directly in this command's execution).
     * @param specialisationOverview The overview containing data for
     *                               all available specialisations (unused in this command).
     * @throws ClassMateException If the provided specialisation name is empty
     *                               or if the removal process fails within the profile.
     */
    @Override
    public void executeCommand(Major major, Ui ui, SpecialisationOverview specialisationOverview) {
        if (specToRemove.isEmpty()) {
            throw new ClassMateException("Please provide a specialisation name. " +
                    "Format: removeSpecialisation <NAME>");
        }

        logger.info("Removing specialisation from profile: " + specToRemove);

        if (specialisationOverview.getSpecialisationByName(specToRemove) == null) {
            throw new ClassMateException("Invalid specialisation name: " + specToRemove);
        }

        userProfile.removeSpecialisation(specToRemove);

        storage.saveUserProfile(userProfile);

        System.out.println("    Success! Removed specialisation: " + specToRemove);
        System.out.println("    You now have "
                                + userProfile.getUserSpecialisations().size()
                                + " specialisation(s) selected.");
    }
}
