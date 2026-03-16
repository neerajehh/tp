package seedu.classmate;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents the CEG major requirements.
 * Stores and manages a list of core modules required to fulfil the major.
 */
public class Major {

    /** Store major requirements as a list of Module objects */
    private ArrayList<Module> coreModules;

    /**
     * Constructs a {@code Major} object and initializes list
     * of core modules required for CEG major.
     */
    public Major() {
        this.coreModules = new ArrayList<>();
        setupCEGModules();
    }

    /**
     * Returns a formatted string containing the list of core modules
     * required to fulfil the CEG major.
     *
     * @return A string listing all core modules.
     */
    @Override
    public String toString() {
        if (coreModules.isEmpty()) {
            return "Core Modules List is currently empty.";
        }
        String moduleListHeader = "Here is a list of modules required to fulfill CEG Major: ";
        String moduleList = coreModules.stream()
                .map(Module::toString)
                .collect(Collectors.joining("\n"));
        return moduleListHeader + moduleList;
    }

    /**
     * Initializes the list of core modules and their prerequisites for CEG major.
     */
    private void setupCEGModules() {
        // Initialise Module objects
        Module ma1511 = new Module("MA1511", "Engineering Calculus");
        Module ma1512 = new Module("MA1512", "Differential Equations for Engineering");
        Module ma1508e = new Module("MA1508E", "Linear Algebra for Engineering");
        Module eg2401a = new Module("EG2401A", "Engineering Professionalism");
        Module cg1111a = new Module("CG1111A", "Engineering Principles and Practice I");
        Module cg2111a = new Module("CG2111A", "Engineering Principles and Practice II");
        Module cs1231 = new Module("CS1231", "Discrete Structures");
        Module cg2023 = new Module("CG2023", "Signals & Systems");
        Module cg2027 = new Module("CG2027", "Transistor-level Digital Circuit");
        Module cg2028 = new Module("CG2028", "Computer Organization");
        Module ee2026 = new Module("EE2026", "Digital Design");
        Module cs2040c = new Module("CS2040C", "Data Structures and Algorithms");
        Module cs2113 = new Module("CS2113", "Software Engineering & Object-Oriented Programming");
        Module cs2107 = new Module("CS2107", "Introduction to Information Security");
        Module cg2271 = new Module("CG2271", "Real-time Operating System");
        Module cg3201 = new Module("CG3201", "Machine Learning and Deep Learning");
        Module cg3207 = new Module("CG3207", "Computer Architecture");
        Module ee4204 = new Module("EE4204", "Computer Networks");

        // Set up semesters
        ma1511.setSemester("1");
        ma1512.setSemester("2");
        ma1508e.setSemester("2");
        cg2111a.setSemester("2");
        cg2023.setSemester("2");
        cg2027.setSemester("1");
        cg2028.setSemester("2");
        ee2026.setSemester("1");
        cg2271.setSemester("1");
        cg3201.setSemester("2");
        cg3207.setSemester("1");
        ee4204.setSemester("2");

        // Set up prerequisites
        cg2111a.addPrerequisite("CG1111A");
        cg2111a.addPrerequisite("CS1010");
        cg2023.addPrerequisite("MA1511");
        cg2023.addPrerequisite("MA1512");
        cg2027.addPrerequisite("CG1111A");
        cg2028.addPrerequisite("CG2027");
        ee2026.addPrerequisite("CG1111A");
        cs2040c.addPrerequisite("CS1010");
        cs2113.addPrerequisite("CS2040C");
        cs2107.addPrerequisite("CS1010");
        cg2271.addPrerequisite("CG1111A");
        cg2271.addPrerequisite("CS2040C");
        cg3201.addPrerequisite("EE2211");
        cg3201.addPrerequisite("CS2040C");
        cg3207.addPrerequisite("CG2028");
        ee4204.addPrerequisite("CS2113");
        ee4204.addPrerequisite("CG2271");

        // Add to ArrayList
        coreModules.add(ma1511);
        coreModules.add(ma1512);
        coreModules.add(ma1508e);
        coreModules.add(eg2401a);
        coreModules.add(cg1111a);
        coreModules.add(cg2111a);
        coreModules.add(cs1231);
        coreModules.add(cg2023);
        coreModules.add(cg2027);
        coreModules.add(cg2028);
        coreModules.add(ee2026);
        coreModules.add(cs2040c);
        coreModules.add(cs2113);
        coreModules.add(cs2107);
        coreModules.add(cg2271);
        coreModules.add(cg3201);
        coreModules.add(cg3207);
        coreModules.add(ee4204);
    }

    /**
     * Finds a module by its module code.
     *
     * @param code The module code to search for.
     * @return The module if found, null otherwise.
     */
    public Module findModule(String code) {
        for (Module m : coreModules) {
            if (m.getModuleCode().equals(code)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Finds all modules that have the given module as a prerequisite.
     *
     * @param prereqCode The prerequisite module code to search for.
     * @return A list of modules that require the given module.
     */
    public ArrayList<Module> findModulesWithPrereq(String prereqCode) {
        ArrayList<Module> modules = new ArrayList<>();
        for (Module module : coreModules) {
            if (module.getPrerequisites().contains(prereqCode)) {
                modules.add(module);
            }
        }
        return modules;
    }
}
