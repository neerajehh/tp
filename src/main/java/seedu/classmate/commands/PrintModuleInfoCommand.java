package seedu.classmate.commands;

import seedu.classmate.ClassMateException;
import seedu.classmate.Major;
import seedu.classmate.Module;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Ui;

/**
 * Represents a command that displays information about a specified module.
 */
public class PrintModuleInfoCommand extends Command {

    private final String moduleCode;

    /**
     * Constructs a {@code PrintModuleInfoCommand} with the givne module code.
     *
     * @param args The module code entered by the user.
     */
    public PrintModuleInfoCommand(String args) {
        this.moduleCode = args.trim().toUpperCase();
    }

    /**
     * Displays detailed information of the specified module.
     *
     * @param major The {@code Major} instance containing module data.
     * @param ui The {@code Ui} handler (unused).
     * @param specialisationOverview The overview of all specialisations (unused).
     * @throws ClassMateException If the module code is empty or the module is not found.
     */
    @Override
    public void executeCommand(Major major, Ui ui, SpecialisationOverview specialisationOverview) {
        if (moduleCode.isEmpty()) {
            throw new ClassMateException("Please provide a module code!");
        }

        Module module = major.findModule(moduleCode);

        if (module == null) {
            throw new ClassMateException("Module not found: " + moduleCode);
        }

        System.out.println(module.printInfo());
    }
}
