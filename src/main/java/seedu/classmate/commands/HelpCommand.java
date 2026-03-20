package seedu.classmate.commands;

import seedu.classmate.Major;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Ui;

/**
 * Represents a Command that displays the list of available commands to the user.
 */
public class HelpCommand extends Command {

    /**
     * Displays the help message containing all support commands.
     *
     * @param major The {@code Major} instance (unused).
     * @param ui The {@code Ui} handler used to show messages.
     * @param specialisationOverview The overview of specialisations (unused).
     */
    @Override
    public void executeCommand(Major major, Ui ui, SpecialisationOverview specialisationOverview) {
        Ui.showHelp();
    }
}
