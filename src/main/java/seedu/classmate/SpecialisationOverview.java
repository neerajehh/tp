package seedu.classmate;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents an overview of all available CEG specialisations.
 *
 * This class maintains a list of all supported specialisations and provides
 * functionality to display the available specialisations, retrieve a specific
 * specialisation, and print detailed infromation about a selected specialisation.
 */
public class SpecialisationOverview {
    private static ArrayList<Specialisation> specs;

    /**
     * Constructs a SpecialisationOverview object and initialises the list of available CEG specialisations.
     *
     * The predefined specialisations include:
     * Internet of Things, Advanced Electronics, Space Technology,
     * Industry 4.0, and Robotics.
     */
    public SpecialisationOverview(HashMap<String, ArrayList<Module>> specialisationMap) {
        specs = new ArrayList<>();

        for (String specName : specialisationMap.keySet()) {
            specs.add(new Specialisation(specName, specialisationMap.get(specName)));
        }
    }

    /**
     * Getter for obtaining the list of specialisations.
     *
     * @return The Array List of specialisations.
     */
    public ArrayList<Specialisation> getSpecialisations() {
        return specs;
    }

    /**
     * Retrieves the specialisation corresponding to the given number.
     *
     * @param specialisationNumber The number representing the desired specialisation in the list.
     * @return The Specialisation object associated with the given number.
     * @throws ClassMateException If the provided number is outside the range of available specialisations.
     */
    public Specialisation getSpecialisationDetails(int specialisationNumber) {
        if (specialisationNumber < 1 || specialisationNumber > specs.size()) {
            throw new ClassMateException("Invalid specialisation number. Please choose a number between 1 and "
                    + specs.size());
        }
        return specs.get(specialisationNumber - 1);
    }

    public Module findSpecialisationModule(String moduleCode) {
        for (Specialisation spec : specs) {
            for (Module module : spec.getSpecialisationCoreModules()) {
                if (module.getModuleCode().equals(moduleCode)) {
                    return module;
                }
            }

            for (Module module : spec.getSpecialisationElectiveModules()) {
                if (module.getModuleCode().equals(moduleCode)) {
                    return module;
                }
            }
        }

        return null;
    }
}
