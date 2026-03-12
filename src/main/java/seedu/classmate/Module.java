package seedu.classmate;

import java.util.ArrayList;

/**
 * Represents a university module consisting of a module code,
 * module name, and a list of prerequisite module(s).
 */
public class Module {
    private String moduleCode;
    private String moduleName;
    private ArrayList<String> prerequisites;

    /**
     * Constructs a {@code Module} with the specified module code and name.
     *
     * @param moduleCode The code of the module (e.g., CS2113).
     * @param moduleName The title of the module.
     * @throws ClassMateException If the module code or name is empty.
     */
    public Module(String moduleCode, String moduleName) {
        // Add guard clause against empty details
        if (moduleCode.trim().isEmpty() || moduleName.trim().isEmpty()) {
            throw new ClassMateException("Module details cannot be empty.");
        }

        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.prerequisites = new ArrayList<>();
    }

    /**
     * Adds a prerequisite module code to this module.
     * Duplicate prerequisites will not be added.
     *
     * @param prereqCode The module code of the prerequisite module.
     */
    public void addPrerequisite(String prereqCode) {
        // Add guard clause against duplicate prerequisite entry: See ModuleTest class
        boolean isDuplicate = prerequisites.contains(prereqCode);
        if (!isDuplicate) {
            this.prerequisites.add(prereqCode);
        }
    }

    public void addPrerequisites(String... prereqCodes) {
        if (prereqCodes == null) {
            return;
        }

        for (String prereqCode : prereqCodes) {
            addPrerequisite(prereqCode.trim());
        }
    }

    /**
     * Returns a string representation of the module.
     *
     * @return A string containing the module code and module title.
     */
    @Override
    public String toString() {
        return moduleCode + " " + moduleName;
    }

    /**
     * Returns the list of prerequisite module codes.
     *
     * @return An {@code ArrayList} containing prerequisite module codes.
     */
    public String toStringPrerequisites() {
        if (prerequisites.isEmpty()) {
            return moduleCode + " has no prerequisites.";
        }
        return "Prerequisites for " + moduleCode +
                ": " + String.join(", ", prerequisites);
    }

    /**
     * Returns the list of prerequisite module codes.
     *
     * @return An {@code ArrayList} containing prerequisite module codes.
     */
    public ArrayList<String> getPrerequisites() {
        return prerequisites;
    }
}
