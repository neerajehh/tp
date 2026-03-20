package seedu.classmate.commands;

import seedu.classmate.ClassMateException;
import seedu.classmate.Major;
import seedu.classmate.Module;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Ui;

/**
 * Represents a command that checks the availability of a module in a given semester.
 */
public class QueryModuleAvailabilityCommand extends Command {

    private final String moduleCode;
    private final String semester;

    /**
     * Constructs a {@code QueryModuleAvailabilityCommand} with the given arguments.
     *
     * @param args The user input containing the module code and semester.
     * @throws ClassMateException If the input does not contain both module code and semester.
     */
    public QueryModuleAvailabilityCommand(String args) {

        String[] parts = args.trim().split("\\s+");

        if (parts.length < 2) {
            throw new ClassMateException("Format: queryModuleAvailability <MODULE_CODE> <SEM>");
        }

        this.moduleCode = parts[0].toUpperCase();
        this.semester = parts[1].toLowerCase();
    }

    /**
     * Checks and displays the availability of the specified module in a given semester.
     *
     * @param major The {@code Major} instance containing module data.
     * @param display The {@code Ui} handler (unused).
     * @param specialisationOverview The overview of specialisations (unused).
     * @throws ClassMateException If the module is not found.
     */
    @Override
    public void executeCommand(Major major, Ui display, SpecialisationOverview specialisationOverview) {

        Module module = major.findModule(moduleCode);

        if (module == null) {
            throw new ClassMateException("Module not found: " + moduleCode);
        }

        String result = module.checkAvailability(semester);
        System.out.println(result);
    }
}
