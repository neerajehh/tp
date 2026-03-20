package seedu.classmate.commands;

import seedu.classmate.ClassMateException;
import seedu.classmate.Major;
import seedu.classmate.Specialisation;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Ui;

/**
 * Represents a command that displays detailed information about a specific CEG specialisation.
 */
public class SpecialisationInfoCommand extends Command {

    private final String args;

    /**
     * Constructs a {@code SpecialisationInfoCommand} with the given arguments.
     *
     * @param args The user input representing the specialisation index.
     */
    public SpecialisationInfoCommand(String args) {
        this.args = args.trim();
    }

    /**
     * Displays information about the specified specialisation.
     *
     * @param major The {@code Major} instance (unused).
     * @param ui The {@code Ui} handler (unused).
     * @param specialisationOverview The overview containing all specialisations.
     * @throws ClassMateException If the input is empty or not a valid number (1 to 5).
     */
    @Override
    public void executeCommand(Major major, Ui ui, SpecialisationOverview specialisationOverview) {

        if (args.isEmpty()) {
            throw new ClassMateException("Please provide a specialisation index: viewSpecialisationInfo <INDEX>");
        }

        try {
            int index = Integer.parseInt(args);

            Specialisation spec = specialisationOverview.getSpecialisationDetails(index);
            specialisationOverview.displaySpecialisationDetails(spec);

        } catch (NumberFormatException e) {
            throw new ClassMateException("Specialisation index must be a number.");
        }
    }
}
