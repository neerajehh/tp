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
        assert moduleCode != null : "Module code should not be null";
        assert moduleName != null : "Module name should not be null";

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
        assert prereqCode != null : "Prerequisite code cannot be null";
        assert !prereqCode.trim().isEmpty() : "Prerequisite code cannot be empty";

        boolean isDuplicate = prerequisites.contains(prereqCode);
        if (!isDuplicate) {
            this.prerequisites.add(prereqCode);
        }
    }

    public void addPrerequisites(String... prereqCodes) {
        assert prereqCodes != null : "Prerequisite array should not be null";

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

    public String getModuleCode() {
        return this.moduleCode;
    }

    /**
     * Returns the list of prerequisite module codes.
     *
     * @return An {@code ArrayList} containing prerequisite module codes.
     */
    public ArrayList<String> getPrerequisites() {
        return prerequisites;
    }

    public String printPrereqTree(Major major) {
        assert major != null : "Major object should not be null";
        
        StringBuilder sb = new StringBuilder();

        ArrayList<Module> parents = major.findModulesWithPrereq(moduleCode);

        if (!parents.isEmpty()) {
            StringBuilder parentModules = new StringBuilder();
            for (Module module : parents) {
                parentModules.append(module.getModuleCode()).append("  ");
            }
            sb.append(parentModules).append("\n");
            printPrereqTreeHelper(major, "", true, false, sb);
        } else {
            printPrereqTreeHelper(major, "", true, true, sb);
        }

        return sb.toString();
    }

    private void printPrereqTreeHelper(Major major, String prefix, 
            boolean isLast, boolean isFirst, StringBuilder sb) {
        sb.append(prefix);
        if (!isFirst) {  
            sb.append(isLast ? "└── " : "├── ");
        }
        sb.append(moduleCode).append("\n");

        String newPrefix = prefix + (isLast ? "    " : "│   ");

        for (int i = 0; i < prerequisites.size(); i++) {
            String prereqCode = prerequisites.get(i);
            Module prereqModule = major.findModule(prereqCode);

            boolean lastChild = (i == prerequisites.size() - 1);

            if (prereqModule != null) {
                prereqModule.printPrereqTreeHelper(major, newPrefix, lastChild, false, sb);
            } else {
                sb.append(newPrefix)
                    .append(lastChild ? "└── " : "├── ")
                    .append(prereqCode)
                    .append("\n");
            }
        }
    }
}
