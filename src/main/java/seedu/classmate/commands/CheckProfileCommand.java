package seedu.classmate.commands;

import seedu.classmate.Major;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Ui;
import seedu.classmate.UserProfile;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Represents a command that displays the user's academic profile.
 * This includes their selected specialization and a list of completed modules.
 */
public class CheckProfileCommand extends Command {

    private static final Logger logger = Logger.getLogger(CheckProfileCommand.class.getName());
    private final UserProfile userProfile;

    /**
     * Constructs a {@code CheckProfileCommand} with the user's profile.
     *
     * @param userProfile The UserProfile object holding the state of academic progress.
     */
    public CheckProfileCommand(UserProfile userProfile) {
        assert userProfile != null : "UserProfile should not be null";
        this.userProfile = userProfile;
    }

    /**
     * Executes the command by printing the user's specialization and completed modules.
     *
     * @param major                   The Major instance (unused).
     * @param ui                      The Ui handler.
     * @param specialisationOverview  The overview of specialisations (unused).
     */
    @Override
    public void executeCommand(Major major, Ui ui, SpecialisationOverview specialisationOverview) {
        logger.info("Executing CheckProfileCommand");

        String specialization = userProfile.getSpecialization();
        ArrayList<String> completedModules = userProfile.getCompletedModules();

        System.out.println("Your Academic Profile:");
        Ui.printLine();

        System.out.println("Specialization: " + specialization);
        
        System.out.println("Completed Modules:");
        if (completedModules.isEmpty()) {
            System.out.println("  - No modules marked as completed yet.");
        } else {
            for (String moduleCode : completedModules) {
                System.out.println("  [DONE] " + moduleCode);
            }
        }

        Ui.printLine();
        System.out.println("Keep up the great work!");
    }
}
