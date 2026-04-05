package seedu.classmate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserProfile {
    // @@author Michael-coding06
    private static final int MAX_SPECIALISATIONS = 2;

    // User data to be loaded from txt files via Storage
    // and initialised into ArrayLists
    private ArrayList<String> userCompletedModules;
    private ArrayList<String> userSpecialisations;
    private static final String MODULES_PATH = "data/completedModules.txt";
    private static final String SPEC_PATH = "data/specialization.txt";

    public UserProfile(ArrayList<String> loadedModules, ArrayList<String> loadedSpecs) {
        this.userCompletedModules = loadedModules != null ? loadedModules : new ArrayList<>();
        this.userSpecialisations = loadedSpecs != null ? loadedSpecs : new ArrayList<>();
    }

    /*
     * SECTION: Methods to deal with user's completedModules
     */
    public ArrayList<String> getUserCompletedModules() {
        return userCompletedModules;
    }

    public void markModuleDone(String moduleCode) throws ClassMateException {
        String code = moduleCode.toUpperCase();
        if (userCompletedModules.contains(code)) {
            throw new ClassMateException("Module " + code + " is already marked as completed!");
        }
        userCompletedModules.add(code);
    }

    /*
     * SECTION: Methods to deal with user's specialisations
     */
    public ArrayList<String> getUserSpecialisations() {
        return userSpecialisations;
    }

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
