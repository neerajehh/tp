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
     * @throws ClassMateException If the given name does not correspond to a valid CEG specialisation.
     */
    public Specialisation(String name) {
        this.specialisationName = name;
        this.specialisationCoreModules = new ArrayList<>();
        this.specialisationElectiveModules = new ArrayList<>();
        setupCEGSpecialisationModules();
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
    private void setupCEGSpecialisationModules() {
        Module cs3237 = new Module("CS3237", "Introduction to Internet of Things");
        cs3237.addPrerequisites("CG2028", "CS1010");
        cs3237.setSemester("1");

        Module ee4211 = new Module("EE4211", "Data Science for the Internet of Things");
        ee4211.addPrerequisites("EE2211/EE2213", "ST2334");
        ee4211.setSemester("1");

        Module ee4409 = new Module("EE4409", "Modern Microelectronics Devices and Sensors");
        ee4409.addPrerequisites("CG2027");
        ee4409.setSemester("2");

        Module cg4002 = new Module("CG4002", "Computer Engineering Capstone Project");
        cg4002.addPrerequisites("CS12113");
        cg4002.setSemester("1/2");

        Module cs4222 = new Module("CS4222", "Wireless Networking");
        cs4222.addPrerequisites("EE4204", "ST2334");
        cs4222.setSemester("2");

        Module ee4204 = new Module("EE4204", "Computer Networks");
        ee4204.addPrerequisites("None");
        ee4204.setSemester("1/2");

        Module ee4216 = new Module("EE4216", "Hardware for Internet of Things");
        ee4216.addPrerequisites("EE2026");
        ee4216.setSemester("1");

        Module ee4218 = new Module("EE4218", "Embedded Hardware System Design");
        ee4218.addPrerequisites("CG2028");
        ee4218.setSemester("2");

        Module cs3244 = new Module("CS3244", "Machine Learning");
        cs3244.addPrerequisites("CS2040C", "ST2334", "MA1508E", "MA1511", "MA1512");
        cs3244.setSemester("1/2");

        Module ee4002DR = new Module("EE4002D/R", "Design/Research Capstone");
        ee4002DR.addPrerequisites("Check with your faculty");
        ee4002DR.setSemester("1/2");

        Module cp4106 = new Module("CP4106", "Computing Project - IOT related");
        cp4106.addPrerequisites("Check with your faculty");
        cp4106.setSemester("1/2");

        Module ee3408c = new Module("EE3408C", "Integrated Analog Design");
        ee3408c.addPrerequisites("CG2027");
        ee3408c.setSemester("1");

        Module ee3431c = new Module("EE3431C", "Microelectronic Materials and Devices");
        ee3431c.addPrerequisites("None");
        ee3431c.setSemester("1/2");

        Module ee4407 = new Module("EE4407", "Analog Electronics");
        ee4407.addPrerequisites("CG2027");
        ee4407.setSemester("1");

        Module ee4415 = new Module("EE4415", "Integrated Digital Design");
        ee4415.addPrerequisites("EE2026");
        ee4415.setSemester("2");

        Module ee5507 = new Module("EE5507", "Analog IC Design");
        ee5507.addPrerequisites("EE3408C");
        ee5507.setSemester("2");

        Module cg3207 = new Module("CG3207", "Computer Architecture");
        cg3207.addPrerequisites("CG2028");
        cg3207.setSemester("1");

        Module ee4435 = new Module("EE4435", "Modern Transistors and Memory Devices");
        ee4435.addPrerequisites("CG2027");
        ee4435.setSemester("1");

        Module ee4436 = new Module("EE4436", "Fabrication Process Technology");
        ee4436.addPrerequisites("CG2027");
        ee4436.setSemester("1");

        Module ee4437 = new Module("EE4437", "Photonics - Principles and Applications");
        ee4437.addPrerequisites("CG2027");
        ee4437.setSemester("2");

        Module ee4438 = new Module("EE4438", "Solar Cells and Modules");
        ee4438.addPrerequisites("CG2027");
        ee4438.setSemester("2");

        Module ee3105 = new Module("EE3105", "Beyond Sky - New Space Technology and Application");
        ee3105.addPrerequisites("Check with your faculty");
        ee3105.setSemester("1");

        Module ee3131c = new Module("EE3131C", "Communication Systems");
        ee3131c.addPrerequisites("CG2023");
        ee3131c.setSemester("2");

        Module ee3104c = new Module("EE3104C", "Introduction to RF and Microwave Systems");
        ee3104c.addPrerequisites("PC2020");
        ee3104c.setSemester("1");

        Module ee3331c = new Module("EE3331C", "Feedback Control Systems");
        ee3331c.addPrerequisites("CG2023");
        ee3331c.setSemester("1/2");

        Module ee4115 = new Module("EE4115", "Remote Sensing and Analysis with Deep Learning Technique");
        ee4115.addPrerequisites("EE2211/EE2213", "CG2023", "MA1508E");
        ee4115.setSemester("2");

        Module ee4314 = new Module("EE4314", "Eyes from above: Guidance, Navigation and Control");
        ee4314.addPrerequisites("EE2211");
        ee4314.setSemester("1");

        Module ee4503 = new Module("EE4503", "Power Electronics for Sustainable Energy Technologies");
        ee4503.addPrerequisites("EE2022");
        ee4503.setSemester("2");

        Module ee4101 = new Module("EE4101", "RF Communications");
        ee4101.addPrerequisites("PC2020");
        ee4101.setSemester("1");

        Module ee3306 = new Module("EE3306", "Introduction to Cyber Physical Systems");
        ee3306.addPrerequisites("Check with your faculty");
        ee3306.setSemester("1");

        Module cs4212 = new Module("EE4212", "Computer Vision");
        cs4212.addPrerequisites("MA1508E", "EE3731C/EE4704");
        cs4212.setSemester("1");

        Module ee4302 = new Module("EE4302", "Advanced Control Systems");
        ee4302.addPrerequisites("Check with your faculty");
        ee4302.setSemester("1/2");

        Module ee4307 = new Module("EE4307", "Control System Design and Simulation");
        ee4307.addPrerequisites("Check with your faculty");
        ee4307.setSemester("1/2");

        Module ee4311 = new Module("EE4311", "Fuzzy Logic and Neuro Fuzzy Systems");
        ee4311.addPrerequisites("Check with your faculty");
        ee4311.setSemester("1");

        Module ee4312 = new Module("EE4312", "Artificial Neural Networks");
        ee4312.addPrerequisites("Check with your faculty");
        ee4312.setSemester("2");

        Module ee4315 = new Module("EE4315", "Intelligent Industrial Control Systems");
        ee4315.addPrerequisites("Check with your faculty");
        ee4315.setSemester("1/2");

        Module me3242 = new Module("ME3242", "Automation");
        me3242.addPrerequisites("Check with your faculty");
        me3242.setSemester("1/2");

        Module me4262 = new Module("ME4262", "Automation in Manufacturing");
        me4262.addPrerequisites("Check with your faculty");
        me4262.setSemester("2");

        Module me4248 = new Module("ME4248", "Manufacturing Simulation and Data Communication");
        me4248.addPrerequisites("Check with your faculty");
        me4248.setSemester("1/2");

        Module me4246 = new Module("ME4246", "Modern Control System");
        me4246.addPrerequisites("Check with your faculty");
        me4246.setSemester("1/2");

        Module me5405 = new Module("ME5405", "Machine Vision");
        me5405.addPrerequisites("EE3331C/ME2142");
        me5405.setSemester("1/2");

        Module cn4227r = new Module("CN4227R", "Advanced Process Control");
        cn4227r.addPrerequisites("Check with your faculty");
        cn4227r.setSemester("1/2");

        Module cn4221r = new Module("CN4221R", "Control of Industrial Processes");
        cn4221r.addPrerequisites("CN3121");
        cn4221r.setSemester("2");

        Module rb4301 = new Module("RB4301", "Robot Learning");
        rb4301.addPrerequisites("Check with your faculty");
        rb4301.setSemester("1/2");

        Module bn3203 = new Module("BN3203", "Robotics in Rehabilitation");
        bn3203.addPrerequisites("Check with your faculty");
        bn3203.setSemester("1/2");

        Module bn4207 = new Module("BN4207", "Microrobotics");
        bn4207.addPrerequisites("Check with your faculty");
        bn4207.setSemester("2");

        Module bn4601 = new Module("BN4601", "Intelligent Medical Robotics");
        bn4601.addPrerequisites("Check with your faculty");
        bn4601.setSemester("1/2");

        Module ee3305 = new Module("EE3305", "Robotic System Design");
        ee3305.addPrerequisites("Check with your faculty");
        ee3305.setSemester("1");

        Module ee4305 = new Module("EE4305", "Fuzzy/Neural Systems for Intelligent Robotics");
        ee4305.addPrerequisites("Check with your faculty");
        ee4305.setSemester("1/2");

        Module ee4308 = new Module("EE4308", "Autonomous Robot Systems");
        ee4308.addPrerequisites("EE3331C/ME3142/ME2142/RB2203");
        ee4308.setSemester("2");

        Module ee4309 = new Module("EE4309", "Robot Perception");
        ee4309.addPrerequisites("EE4704/EE3731C");
        ee4309.setSemester("1");

        Module ee4705 = new Module("EE4705", "Human-Robot Interaction");
        ee4705.addPrerequisites("EE2211/EE2213", "EE3331C/ME3142/ME2142/EE3305/ME3243/RB2203");
        ee4705.setSemester("1");

        Module me2143 = new Module("ME2143", "Sensors and Actuators");
        me2143.addPrerequisites("ME2104");
        me2143.setSemester("1");

        Module me4242 = new Module("ME4242", "Soft Robotics");
        me4242.addPrerequisites("Check with your faculty");
        me4242.setSemester("1");

        Module me4245 = new Module("ME4245", "Robot Mechanics and Control");
        me4245.addPrerequisites("ME2142/ME3142/EE3331C");
        me4245.setSemester("1");

        Module me5406 = new Module("ME5406", "Deep Learning for Robotics");
        me5406.addPrerequisites("Check with your faculty");
        me5406.setSemester("2");

        Module mle4228 = new Module("MLE4228", "Materials for Robotic Sensing and Actuation");
        mle4228.addPrerequisites("Check with your faculty");
        mle4228.setSemester("1");
        
        switch (specialisationName) {
        case "Internet of Things":
            specialisationDescription = internetOfThingsDescription;
            electiveRequirements = "Choose any two courses, or totaling at least 8 units, from the list below:";

            specialisationCoreModules.add(cs3237);
            specialisationCoreModules.add(ee4211);
            specialisationCoreModules.add(ee4409);

            specialisationElectiveModules.add(cg4002);
            specialisationElectiveModules.add(cs4222);
            specialisationElectiveModules.add(ee4204);
            specialisationElectiveModules.add(ee4216);
            specialisationElectiveModules.add(ee4218);
            specialisationElectiveModules.add(cs3244);
            specialisationElectiveModules.add(ee4002DR);
            specialisationElectiveModules.add(cp4106);
            break;

        case "Advanced Electronics":
            specialisationDescription = advancedElectronicsDescription;
            electiveRequirements = "Choose any three courses, or totaling at least 12 units, from the list below:";
            specialisationCoreModules.add(ee3408c);
            specialisationCoreModules.add(ee3431c);

            specialisationElectiveModules.add(ee4218);
            specialisationElectiveModules.add(ee4407);
            specialisationElectiveModules.add(ee4415);
            specialisationElectiveModules.add(ee5507);
            specialisationElectiveModules.add(cg3207);
            specialisationElectiveModules.add(ee4409);
            specialisationElectiveModules.add(ee4435);
            specialisationElectiveModules.add(ee4436);
            specialisationElectiveModules.add(ee4437);
            specialisationElectiveModules.add(ee4438);
            break;

        case "Space Technology":
            specialisationDescription = spaceTechDescription;
            electiveRequirements = "Choose any two courses, or totaling at least 8 units, from the list below:";
            specialisationCoreModules.add(ee3105);
            specialisationCoreModules.add(ee4002DR);

            specialisationElectiveModules.add(ee3131c);
            specialisationElectiveModules.add(ee3104c);
            specialisationElectiveModules.add(ee3331c);
            specialisationElectiveModules.add(ee4115);
            specialisationElectiveModules.add(ee4218);
            specialisationElectiveModules.add(ee4314);
            specialisationElectiveModules.add(ee4503);
            specialisationElectiveModules.add(ee4101);
            break;

        case "Industry 4.0":
            specialisationDescription = industryDescription;
            electiveRequirements = "Choose any three courses, or totaling at least 12 units, from the list below:";
            specialisationCoreModules.add(ee3331c);
            specialisationCoreModules.add(ee3306);

            specialisationElectiveModules.add(ee4211);
            specialisationElectiveModules.add(cs4212);
            specialisationElectiveModules.add(ee4302);
            specialisationElectiveModules.add(ee4307);
            specialisationElectiveModules.add(ee4311);
            specialisationElectiveModules.add(ee4312);
            specialisationElectiveModules.add(ee4314);
            specialisationElectiveModules.add(ee4315);
            specialisationElectiveModules.add(me3242);
            specialisationElectiveModules.add(me4262);
            specialisationElectiveModules.add(me4248);
            specialisationElectiveModules.add(me4246);
            specialisationElectiveModules.add(me5405);
            specialisationElectiveModules.add(cn4227r);
            specialisationElectiveModules.add(cn4221r);
            specialisationElectiveModules.add(rb4301);
            break;

        case "Robotics":
            specialisationDescription = roboticsDescription;
            electiveRequirements = "12 Units from electives AND a Capstone (8 Units) in Robotics OR, 20 Units " +
                    "from electives:";

            specialisationElectiveModules.add(bn3203);
            specialisationElectiveModules.add(bn4207);
            specialisationElectiveModules.add(bn4601);
            specialisationElectiveModules.add(ee3305);
            specialisationElectiveModules.add(ee4115);
            specialisationElectiveModules.add(ee4305);
            specialisationElectiveModules.add(ee4308);
            specialisationElectiveModules.add(ee4309);
            specialisationElectiveModules.add(ee4311);
            specialisationElectiveModules.add(ee4312);
            specialisationElectiveModules.add(ee4314);
            specialisationElectiveModules.add(ee4705);
            specialisationElectiveModules.add(me2143);
            specialisationElectiveModules.add(me4242);
            specialisationElectiveModules.add(me4245);
            specialisationElectiveModules.add(me5406);
            specialisationElectiveModules.add(mle4228);
            specialisationElectiveModules.add(rb4301);
            break;

        default:
            throw new ClassMateException("This is not a CEG specialisation");
        }
    }
}
