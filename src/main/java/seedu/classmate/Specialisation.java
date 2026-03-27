package seedu.classmate;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a CEG specialisation.
 * A Specialisation contains the name of the specialisation, its description,
 * a list of core modules, a list of elective modules, and the elective requirements
 * that CEG students must satisfy
 * When a Specialisation is created, the corresponding description and modules
 * are automatically initialised based on the specialisation name.
 */
public class Specialisation {

    private static final String internetOfThingsDescription = """
            IoT and its suite of enabling hardware and software components, referred to as IoT systems, are 
            perceived as one of the most influential technologies in the modern era in both industry and academia""";

    private static final String advancedElectronicsDescription = """
            This specialisation in Advanced Electronics (AE) will introduce students to industry practices 
            related to semiconductor fabrication, chip manufacturing, IC design and prototyping""";

    private static final String spaceTechDescription = """
            The Space Technology specialisation will equip students for satellite related industries and 
            many other industries such as aerospace, automotive and all the related commercial products""";

    private static final String industryDescription = """
            The specialisation allows students to learn about machines augmented with sensors which communicate 
            with each other, servers, the cloud, and people to enhance autonomy, visualization and decision making""";

    private static final String roboticsDescription = """
            The specialisation allows students to take a hands-on approach to learning, through small projects 
            in the modules and a final year project on robotics. Students will need to design and construct 
            robotic systems or their components""";

    private String specialisationName;
    private String specialisationDescription;
    private ArrayList<Module> specialisationCoreModules;
    private ArrayList<Module> specialisationElectiveModules;
    private String electiveRequirements;

    /**
     * Constructs a {@code Specialisation} with the specified name.
     * The constructor initializes the core and elective module lists
     * and populates them according to the given CEG specialisation.
     * @param name The name of the CEG specialisation.
     * @param allSpecialisationModules The array list of all modules belonging to a specific specialisation.
     * @throws ClassMateException If the given name does not correspond to a valid CEG specialisation.
     */
    public Specialisation(String name, ArrayList<Module> allSpecialisationModules) {
        this.specialisationName = name;
        this.specialisationCoreModules = new ArrayList<>();
        this.specialisationElectiveModules = new ArrayList<>();

        for (Module specModule : allSpecialisationModules) {
            if (specModule.getModuleType().equals("Core")) {
                this.specialisationCoreModules.add(specModule);
            } else {
                this.specialisationElectiveModules.add(specModule);
            }
        }

        setupExtraDataForThisSpecialisation();
    }

    /**
     * Returns the name of the specific specialisation.
     *
     * @return The specialisation name.
     */
    public String getSpecialisationName() {
        return specialisationName;
    }

    /**
     * Returns the list of core modules required for this specialisation.
     *
     * @return An {@code ArrayList} containing the core modules.
     */
    public String getSpecialisationDescription() {
        return specialisationDescription;
    }

    /**
     * Returns the list of core modules required for this specialisation.
     *
     * @return An {@code ArrayList} containing the core modules.
     */
    public ArrayList<Module> getSpecialisationCoreModules() {
        return specialisationCoreModules;
    }

    /**
     * Returns the list of elective modules available for this specialisation.
     *
     * @return An {@code ArrayList} containing the elective modules.
     */
    public ArrayList<Module> getSpecialisationElectiveModules() {
        return specialisationElectiveModules;
    }

    /**
     * Returns a formatted string representation of the specialisation core
     * and elective modules.
     *
     * The output includes all core specialisation modules followed by
     * the list of elective modules and their requirements.
     *
     * @return A formatted string displaying the modules in this specialisation.
     */
    @Override
    public String toString() {

        return "Core Specialisation Modules:\n\n" +
                specialisationCoreModules.stream()
                        .map(Module::printInfo)
                        .collect(Collectors.joining("\n")) +
                "\n\n" +
                "Elective Modules (" + electiveRequirements + ")\n" +
                specialisationElectiveModules.stream()
                        .map(Module::printInfo)
                        .collect(Collectors.joining("\n"));
    }

    /**
     * Initialises the description, core modules, and elective modules
     * for the specified CEG specialisation.
     *
     * This method creates all available module objects and assigns the appropriate
     * modules to the core and elective lists based on the specialisation name.
     *
     * @throws ClassMateException if the provided specialisation name does not match any valid CEG specialisation.
     */
    private void setupExtraDataForThisSpecialisation() {

        switch (this.specialisationName) {
        case "Internet of Things":
            this.specialisationDescription = internetOfThingsDescription;
            this.electiveRequirements = "Choose any two courses, or totaling at least 8 units, from the list below:";
            break;

        case "Advanced Electronics":
            this.specialisationDescription = advancedElectronicsDescription;
            this.electiveRequirements = "Choose any three courses, or totaling at least 12 units, from the list below:";
            break;

        case "Space Technology":
            this.specialisationDescription = spaceTechDescription;
            this.electiveRequirements = "Choose any two courses, or totaling at least 8 units, from the list below:";
            break;

        case "Industry 4.0":
            this.specialisationDescription = industryDescription;
            this.electiveRequirements = "Choose any three courses, or totaling at least 12 units, from the list below:";
            break;

        case "Robotics":
            this.specialisationDescription = roboticsDescription;
            this.electiveRequirements = "12 Units from electives AND a Capstone (8 Units) in Robotics OR, 20 Units " +
                    "from electives:";
            break;

        default:
            throw new ClassMateException("This is not a CEG specialisation");
        }
    }
}
