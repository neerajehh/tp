package seedu.classmate.commands;

import seedu.classmate.Major;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Ui;

/**
 * Represents a command that displays the graduation requirements of the CEG major.
 */
public class ViewGradReqsCommand extends Command{

    /**
     * Displays the graduant requirements.
     *
     * @param major The {@code Major} instance containing the graduation requirement data.
     * @param ui The {@code Ui} handler (unused).
     * @param specialisationOverview The overview of all specialisations (unused).
     */
    @Override
    public void executeCommand(Major major, Ui ui, SpecialisationOverview specialisationOverview) {
        System.out.println(major);
    }
}
