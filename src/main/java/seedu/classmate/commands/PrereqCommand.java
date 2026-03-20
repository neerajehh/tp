package seedu.classmate.commands;

import seedu.classmate.ClassMateException;
import seedu.classmate.Major;
import seedu.classmate.Module;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Ui;

/**
 * Represents a command that displays the prerequisite tree of a specified module.
 */
public class PrereqCommand extends Command{

    private final String moduleCode;

    /**
     * Constructs a {@code PrereqCommand} with the given module code.
     *
     * @param args The module code entered by the user.
     */
    public PrereqCommand(String args) {
        this.moduleCode = args.trim();
    }

    /**
     * Displays the prerequisite tree of the specified module.
     *
     * @param major The {@code Major} instance containing module data.
     * @param ui The {@code Ui} handler (unused).
     * @param specialisationOverview The overview of specialisations (unused).
     * @throws ClassMateException If no module code is provided.
     */
    @Override
    public void executeCommand(Major major, Ui ui, SpecialisationOverview specialisationOverview) {
        if (moduleCode.isEmpty()) {
            throw new ClassMateException("Please provide a module code: viewPrereqs <MODULE_CODE>");
        }

        Module module = major.findModule(moduleCode);

        if (module == null) {
            System.out.println("Module " + moduleCode + " not found.");
            return;
        }

        assert module != null;
        System.out.println(module.printPrereqTree(major));
    }
}
