package seedu.classmate.commands;

import seedu.classmate.Display;
import seedu.classmate.Major;
import seedu.classmate.SpecialisationOverview;

/**
 * Represents a Command that displays the list of available commands to the user.
 */
public class HelpCommand extends Command {

    /**
     * Displays the help message containing all support commands.
     *
     * @param major The {@code Major} instance (unused).
     * @param display The {@code Display} handler used to show messages.
     * @param specialisationOverview The overview of specialisations (unused).
     */
    @Override
    public void executeCommand(Major major, Display display, SpecialisationOverview specialisationOverview) {
        Display.displayHelp();
    }
}
