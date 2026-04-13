package seedu.classmate;

import java.util.ArrayList;

/**
 * Represents the user's personal academic profile within the application.
 * This model class holds the state of the user's completed modules and their chosen academic specialisations.
 */
public class UserProfile {
    // @@author Michael-coding06
    private static final int MAX_SPECIALISATIONS = 2;

    // User data to be loaded from txt files via Storage
    // and initialised into ArrayLists
    private ArrayList<String> userCompletedModules;
    private ArrayList<String> userSpecialisations;

    /**
     * Constructs a {@code UserProfile} object containing the user's academic history.
     *
     * @param loadedModules A list of previously completed module codes (can be null or empty).
     * @param loadedSpecs   A list of previously selected specialisation names (can be null or empty).
     */
    public UserProfile(ArrayList<String> loadedModules, ArrayList<String> loadedSpecs) {
        this.userCompletedModules = loadedModules != null ? loadedModules : new ArrayList<>();
        this.userSpecialisations = loadedSpecs != null ? loadedSpecs : new ArrayList<>();
    }

    /*
     * SECTION: Methods to deal with user's completedModules
     */

    /**
     * Retrieves the list of module codes the user has completed.
     *
     * @return An {@code ArrayList<String>} representing the completed module codes.
     */
    public ArrayList<String> getUserCompletedModules() {
        return userCompletedModules;
    }

    /**
     * Marks a specific module as completed by adding it to the user's profile.
     *
     * @param module The module to mark as done.
     * @throws ClassMateException If the module is already completed or prerequisites are not met.
     */
    public void markModuleDone(Module module) throws ClassMateException {
        // String code = moduleCode.toUpperCase();
    
        if (userCompletedModules.contains(module.getModuleCode())) {
            throw new ClassMateException(module.getModuleCode() + " is already marked as completed!");
        }
        ArrayList<String> prereqs = module.getPrerequisites();

        if (!userCompletedModules.containsAll(prereqs)) {
            throw new ClassMateException(
                "Cannot mark " + module.getModuleCode() +" done. Please ensure you have done all of its prerequisites"
            );
        }
        // Module module = major.findModule(code);
        userCompletedModules.add(module.getModuleCode());
    }

    // @@author neerajehh
    public void unmarkModuleDone(String moduleCode) throws ClassMateException {
        String code = moduleCode.toUpperCase();
        if (!userCompletedModules.contains(code)) {
            throw new ClassMateException(code + " is not in your completed modules list.");
        }
        userCompletedModules.remove(code);
    }
    // @@author

    /*
     * SECTION: Methods to deal with user's specialisations
     */

    /**
     * Retrieves the list of specialisations the user has currently selected.
     *
     * @return An {@code ArrayList<String>} representing the names of chosen specialisations.
     */
    public ArrayList<String> getUserSpecialisations() {
        return userSpecialisations;
    }

    /**
     * Adds an academic specialisation to the user's profile.
     *
     * @param specName The string name of the specialisation to add.
     * @throws ClassMateException If the user has already reached the maximum allowed specialisations,
     *                            or if they are attempting to add a duplicate specialisation.
     */
    public void addSpecialisation(String specName) throws ClassMateException {
        if (userSpecialisations.size() >= MAX_SPECIALISATIONS) {
            throw new ClassMateException("You can only select up to " + MAX_SPECIALISATIONS + " specialisations.");
        }
        if (userSpecialisations.contains(specName)) {
            throw new ClassMateException("You have already selected this specialisation.");
        }
        userSpecialisations.add(specName);
    }
    // @@author

    // @@author lauwn-mower
    /**
     * Removes an existing specialisation from the user's profile.
     * The removal check is case-insensitive to account for user input variation.
     *
     * @param specName The string name of the specialisation to remove.
     * @throws ClassMateException If the profile currently has no specialisations, or if the
     *                            requested specialisation name is not found in the profile.
     */
    public void removeSpecialisation(String specName) throws ClassMateException {
        // Guard clause to check for empty input
        if (userSpecialisations.isEmpty()) {
            throw new ClassMateException("You haven't selected any specialisations to remove.");
        }

        // Check for a case-insensitive match
        String specToRemove = null;
        for (String spec : userSpecialisations) {
            if (spec.equalsIgnoreCase(specName.trim())) {
                specToRemove = spec;
                break;
            }
        }
        if (specToRemove == null) {
            throw new ClassMateException("Specialisation '" + specName + "' not found in your profile.");
        }

        userSpecialisations.remove(specToRemove);
    }
    // @@author
}
