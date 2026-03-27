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
    public Major(ArrayList<Module> coreModules) {
        this.coreModules = coreModules;
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
