package seedu.classmate.commands;

import seedu.classmate.Major;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Ui;

/**
 * Represents a Command that terminates the program after displaying a goodbye message.
 */
public class ByeCommand extends Command {

    public ByeCommand() {
        this.isExit = true; // this.isExit is inherited from Command
    }

    /**
     * Displays the goodbye message to the user.
     *
     * @param major The {@code Major} instance (unused).
     * @param ui The {@code Ui} handler used to show messages.
     * @param specialisationOverview The overview of specialisations (unused).
     */
    @Override
    public void executeCommand(Major major, Ui ui, SpecialisationOverview specialisationOverview) {
        Ui.showGoodbye();
    }
}
