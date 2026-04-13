package seedu.classmate.commands;

import seedu.classmate.Major;
import seedu.classmate.Module;
import seedu.classmate.Specialisation;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Ui;
import seedu.classmate.UserProfile;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Represents a command that displays the user's academic profile.
 * This includes their completed modules, remaining major core modules, and detailed
 * progress tracking for their selected specialisation(s).
 */
public class CheckProfileCommand extends Command {

    // @@author Michael-coding06
    private static final Logger logger = Logger.getLogger(CheckProfileCommand.class.getName());
    private final UserProfile userProfile;

    /**
     * Constructs a {@code CheckProfileCommand} with the user's profile.
     *
     * @param userProfile The UserProfile object holding the state of academic progress.
     */
    public CheckProfileCommand(UserProfile userProfile) {
        assert userProfile != null : "UserProfile should not be null";
        this.userProfile = userProfile;
    }
    // @@author

    /**
     * Executes the command to evaluate and print the user's academic progress.
     * It calculates the remaining major core requirements and delegates the
     * specialisation progress calculation to a helper method, using the {@code Ui}
     * component for output formatting.
     *
     * @param major                   The Major instance (unused).
     * @param ui                      The Ui handler.
     * @param specialisationOverview  The overview of specialisations (unused).
     */
    @Override
    public void executeCommand(Major major, Ui ui, SpecialisationOverview specialisationOverview) {
        logger.info("Executing CheckProfileCommand");

        // @@author lauwn-mower
        ArrayList<String> completedModules = userProfile.getUserCompletedModules();

        // Calculate remaining CEG major modules
        ArrayList<Module> remainingMajorModules = new ArrayList<>();
        for (seedu.classmate.Module m : major.getCoreModules()) {
            if (!completedModules.contains(m.getModuleCode())) {
                remainingMajorModules.add(m);
            }
        }

        ui.showBasicProfile(completedModules, remainingMajorModules);

        // Calculate remaining specialisation modules
        ArrayList<String> userSpecialisations = userProfile.getUserSpecialisations();

        if (userSpecialisations.isEmpty()) {
            System.out.println("No specialisations selected yet.");
            Ui.printLine();
            return;
        }

        for (String specName : userSpecialisations) {
            // Find the actual Specialisation object based on the user's saved string
            Specialisation spec = specialisationOverview.getSpecialisationByName(specName);

            if (spec != null) {
                calculateAndShowSpecProgress(spec, completedModules, ui);
            } else {
                logger.warning("Could not find specialisation data for: " + specName);
            }
        }

        System.out.println("\nKeep up the great work!");
        Ui.printLine();
    }

    /**
     * Calculates the completion status of core and elective modules for a specific specialisation.
     * It tracks how many electives have been completed and filters out completed core requirements,
     * delegating the final presentation to the {@code Ui} class.
     *
     * @param spec           The {@code Specialisation} object to evaluate progress against.
     * @param completedCodes The list of module codes the user has successfully completed.
     * @param ui             The {@code Ui} handler responsible for formatting and displaying the progress.
     */
    private void calculateAndShowSpecProgress(Specialisation spec, ArrayList<String> completedCodes, Ui ui) {
        // Calculate remaining Core modules
        ArrayList<Module> remainingSpecCore = new ArrayList<>();
        for (Module m : spec.getSpecialisationCoreModules()) {
            if (!completedCodes.contains(m.getModuleCode())) {
                remainingSpecCore.add(m);
            }
        }

        // Calculate Electives by counting no. of completed mods and listing remaining mods
        ArrayList<Module> remainingElectives = new ArrayList<>();
        int completedElectivesCount = 0;

        for (Module m : spec.getSpecialisationElectiveModules()) {
            if (completedCodes.contains(m.getModuleCode())) {
                completedElectivesCount++;
            } else {
                remainingElectives.add(m);
            }
        }

        // Pass calculated data to Ui to format and print
        ui.showSpecialisationProgress(spec, remainingSpecCore, remainingElectives, completedElectivesCount);
    }

    // @@author
}
