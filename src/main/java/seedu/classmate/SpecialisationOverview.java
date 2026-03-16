package seedu.classmate;

import java.util.ArrayList;

/**
 * Represents an overview of all available CEG specialisations.
 *
 * This class maintains a list of all supported specialisations and provides
 * functionality to display the available specialisations, retrieve a specific
 * specialisation, and print detailed infromation about a selected specialisation.
 */
public class SpecialisationOverview {
    private ArrayList<Specialisation> specs;

    /**
     * Constructs a SpecialisationOverview object and initialises the list of available CEG specialisations.
     *
     * The predefined specialisations include:
     * Internet of Things, Advanced Electronics, Space Technology,
     * Industry 4.0, and Robotics.
     */
    public SpecialisationOverview() {
        specs = new ArrayList<>();
        specs.add(new Specialisation("Internet of Things"));
        specs.add(new Specialisation("Advanced Electronics"));
        specs.add(new Specialisation("Space Technology"));
        specs.add(new Specialisation("Industry 4.0"));
        specs.add(new Specialisation("Robotics"));
    }

    /**
     * Displays all available specialisations to the user.
     *
     * Each specialisation is printed with a corresponding number that
     * can be used by the user to select and view more details about a specific specialisation.
     */
    public void listAllSpecialisations() {
        for (int specialisationIndex = 0; specialisationIndex < specs.size(); specialisationIndex++) {
            System.out.println((specialisationIndex + 1) + ". "
                    + specs.get(specialisationIndex).getSpecialisationName());
        }
        System.out.println("Enter <view specialisationNumber> to preview specialisation information.");
    }

    /**
     * Displays detailed information about the selected specialisation.
     *
     * The information printed includes the specialisation name, description,
     * core modules, and elective modules.
     *
     * @param selectedSpecialisation The specialisation whose details are to be displayed.
     */
    public void displaySpecialisationDetails(Specialisation selectedSpecialisation) {
        System.out.println("Specialisation: " + selectedSpecialisation.getSpecialisationName() + "\n");

        System.out.println("Description: " + selectedSpecialisation.getSpecialisationDescription() + "\n");

        System.out.println("Core Modules:");
        for (Module coreModule : selectedSpecialisation.getSpecialisationCoreModules()) {
            System.out.println(coreModule.getModuleCode() + " : " + coreModule.getModuleName());
        }

        System.out.println();

        System.out.println("Elective Modules:");
        for (Module electiveModule : selectedSpecialisation.getSpecialisationElectiveModules()) {
            System.out.println(electiveModule.getModuleCode() + " : " + electiveModule.getModuleName());
        }
    }

    /**
     * Retrieves the specialisation corresponding to the given number.
     *
     * @param specialisationNumber The number representing the desired specialisation in the list.
     * @return The Specialisation object associated with the given number.
     * @throws ClassMateException If the provided number is outside the range of available specialisations.
     */
    public Specialisation getSpecialisationDetails(int specialisationNumber) {
        if (specialisationNumber < 0 || specialisationNumber >= specs.size()) {
            throw new ClassMateException("Invalid specialisation number. Please choose a number between 1 and "
                    + specs.size());
        }
        return specs.get(specialisationNumber - 1);
    }

}
