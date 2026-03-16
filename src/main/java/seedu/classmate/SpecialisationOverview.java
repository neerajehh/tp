package seedu.classmate;

import java.util.ArrayList;

public class SpecialisationOverview {
    private ArrayList<Specialisation> specs;
    private static final String internetOfThingsDescription = "IoT and its suite of enabling hardware and software " +
            "components, referred to as IoT systems, are perceived as one of the most influential technologies in " +
            "the modern era in both industry and academia";

    private static final String advancedElectronicsDescription = "This specialisation in Advanced Electronics (AE) " +
            "will introduce students to industry practices related to semiconductor fabrication, chip manufacturing, " +
            "IC design and prototyping";

    private static final String spaceTechDescription = "The Space Technology specialisation will equip students for " +
            "satellite related industries and many other industries such as aerospace, automotive and all the " +
            "related commercial products";

    private static final String industryDescription = "The specialisation allows students to learn about machines " +
            "augmented with sensors which communicate with each other, servers, the cloud, and people to enhance " +
            "autonomy, visualization and decision making";

    private static final String roboticsDescription = " The specialisation allows students to take a hands-on " +
            "approach to learning, through small projects in the modules and a final year project on robotics. " +
            "Students will need to design and construct robotic systems or their components";

    public SpecialisationOverview() {
        specs = new ArrayList<>();
        specs.add(new Specialisation("Internet of Things"));
        specs.add(new Specialisation("Advanced Electronics"));
        specs.add(new Specialisation("Space Technology"));
        specs.add(new Specialisation("Industry 4.0"));
        specs.add(new Specialisation("Robotics"));
    }

    public void listAllSpecialisations() {
        for (int specialisationIndex = 0; specialisationIndex < specs.size(); specialisationIndex++) {
            System.out.println((specialisationIndex + 1) + ". " + specs.get(specialisationIndex).getSpecialisationName());
        }
        System.out.println("Enter <view specialisationNumber> to preview specialisation information.");
    }

    public void displaySpecialisationDetails(Specialisation selectedSpecialisation) {
        System.out.println("Specialisation: " + selectedSpecialisation.getSpecialisationName());

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

    public Specialisation getSpecialisationDetails(int specialisationNumber) {
        if (specialisationNumber < 0 || specialisationNumber >= specs.size()) {
            throw new ClassMateException("Invalid specialisation number. Please choose a number between 1 and " + specs.size());
        }
        return specs.get(specialisationNumber - 1);
    }

}
