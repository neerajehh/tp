package seedu.classmate.commands;

import seedu.classmate.Major;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Ui;

/**
 * Represents a command that displays a list of all available CEG specialisations.
 */
public class ViewSpecialisationsCommand extends Command {

    /**
     * Displays all available specialisations.
     *
     * @param major The {@code Major} instance (unused).
     * @param ui The {@code Ui} handler (unused).
     * @param specialisationOverview The overview of all specialisations.
     */
    @Override
    public void executeCommand(Major major, Ui ui, SpecialisationOverview specialisationOverview) {
        System.out.println("List of all CEG Specialisations:");
        SpecialisationOverview.listAllSpecialisations();
    }
}
