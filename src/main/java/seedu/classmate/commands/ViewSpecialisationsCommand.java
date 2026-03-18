package seedu.classmate.commands;

import seedu.classmate.Display;
import seedu.classmate.Major;
import seedu.classmate.SpecialisationOverview;

/**
 * Represents a command that displays a list of all available CEG specialisations.
 */
public class ViewSpecialisationsCommand extends Command {

    /**
     * Displays all available specialisations.
     *
     * @param major The {@code Major} instance (unused).
     * @param display The {@code Display} handler (unused).
     * @param specialisationOverview The overview of all specialisations.
     */
    @Override
    public void executeCommand(Major major, Display display, SpecialisationOverview specialisationOverview) {
        System.out.println("List of all CEG Specialisations:");
        SpecialisationOverview.listAllSpecialisations();
    }
}
