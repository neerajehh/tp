package seedu.classmate;

import java.util.ArrayList;

/**
 * Represents a university module consisting of a module code,
 * module name, and a list of prerequisite module(s).
 */
public class Module {
    private String moduleCode;
    private String moduleName;
    private int moduleUnit;
    private String moduleType;
    private ArrayList<String> prerequisites;
    private String semester;

    /**
     * Constructs a {@code Module} with the specified module code and name.
     *
     * @param moduleCode The code of the module (e.g., CS2113).
     * @param moduleName The title of the module.
     * @throws ClassMateException If the module code or name is empty.
     */
    public Module(String moduleCode, String moduleName, int moduleUnit) {
        assert moduleCode != null : "Module code should not be null";
        assert moduleName != null : "Module name should not be null";
        assert moduleUnit > 0 : "Module unit should be greater than 0";
        if (moduleCode.trim().isEmpty() || moduleName.trim().isEmpty()) {
            throw new ClassMateException("Module details cannot be empty.");
        }
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleType = "Core";
        this.prerequisites = new ArrayList<>();
        this.moduleUnit = moduleUnit;
        this.semester = "1/2";
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

    /**
     * Sets the semester(s) this module is offered.
     *
     * @param semester The semester(s) offered (e.g., "1/2").
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * Checks if this module is available in the given semester.
     *
     * @param querySemester The semester to check (e.g., "sem1", "sem2").
     * @return A string indicating availability.
     */
    public String checkAvailability(String querySemester) {
        assert querySemester != null : "Query semester should not be null";
        assert !querySemester.trim().isEmpty() : "Query semester should not be empty";
        String query = querySemester.trim().toLowerCase();
        if (!query.equals("sem1") && !query.equals("sem2")) {
            return "Invalid semester. Please enter sem1 or sem2.";
        }

        String displaySemester = query.equals("sem1") ? "Semester 1" : "Semester 2";

        if (semester.equals("1/2")) {
            return moduleCode + " is available in both Semester 1 and Semester 2.";
        } else if (semester.equals("1") && query.equals("sem1")) {
            return "Yes, " + moduleCode + " is only available in Semester 1.";
        } else if (semester.equals("2") && query.equals("sem2")) {
            return "Yes, " + moduleCode + " is only available in Semester 2.";
        } else {
            return "No, " + moduleCode + " is not available in " + displaySemester + ".";
        }
    }

    /**
     * Returns a formatted string with full module information.
     *
     * @return A string with module code, name, units, semester, prerequisites and availability.
     */
    public String printInfo() {
        return printInfo(new java.util.ArrayList<>());
    }

    /**
     * Returns a formatted string with full module information, with canTake based on completed modules.
     *
     * @param completedModules The list of modules the user has completed.
     * @return A string with module code, name, units, semester, prerequisites and availability.
     */
    public String printInfo(java.util.ArrayList<String> completedModules) {
        String prereqStr = prerequisites.isEmpty() ? "NIL" : String.join(", ", prerequisites);
        boolean allPrereqsDone = completedModules.containsAll(prerequisites);
        String canTake = (prerequisites.isEmpty() || allPrereqsDone) ? "YES" : "NO";
        return " Code: " + moduleCode + "\n" +
               " Name: " + moduleName + "\n" +
               " Units: " + moduleUnit + "\n" +
               " Semester: " + semester + "\n" +
               " Prerequisites: " + prereqStr + "\n" +
               " Can take: " + canTake;
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
     * Returns the prerequisites as a string.
     *
     * @return A string listing prerequisites.
     */
    public String toStringPrerequisites() {
        if (prerequisites.isEmpty()) {
            return moduleCode + " has no prerequisites.";
        }
        return "Prerequisites for " + moduleCode +
                ": " + String.join(", ", prerequisites);
    }


    public String getModuleName() {
        return moduleName;
    }

    /**
     * Returns the module code.
     *
     * @return The module code.
     */
    public String getModuleCode() {
        return this.moduleCode;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    /**
     * Returns the list of prerequisite module codes.
     *
     * @return An {@code ArrayList} containing prerequisite module codes.
     */
    public ArrayList<String> getPrerequisites() {
        return prerequisites;
    }

    /**
     * Prints the prerequisite tree for this module.
     *
     * @param major The major object containing all modules.
     * @return A string representation of the prerequisite tree.
     */
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
        String newPrefix = isFirst ? "" : prefix + (isLast ? "    " : "│   ");
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

