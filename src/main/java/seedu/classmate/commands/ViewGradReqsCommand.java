package seedu.classmate.commands;

import seedu.classmate.Major;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Ui;

/**
 * Represents a command that displays the graduation requirements of the CEG major.
 */
public class ViewGradReqsCommand extends Command{

    /**
     * Executes the command to display the CEG graduation requirements.
     * Retrieves the core module list from the {@code Major} instance and delegates
     * the formatting and console output to the {@code Ui} handler.
     *
     * @param major                  The {@code Major} instance containing the graduation requirement data.
     * @param ui                     The {@code Ui} handler used to print the requirements to the user.
     * @param specialisationOverview The overview of all specialisations (unused in this command).
     */
    @Override
    public void executeCommand(Major major, Ui ui, SpecialisationOverview specialisationOverview) {
        ui.showGraduationRequirements(major.getCoreModules());
    }
}
