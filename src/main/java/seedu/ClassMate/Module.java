package seedu.ClassMate;

import java.util.ArrayList;

public class Module {
    private String moduleCode;
    private String moduleName;
    private ArrayList<String> prerequisites;

    public Module(String moduleCode, String moduleName) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.prerequisites = new ArrayList<>();
    }

    public void addPrerequisite(String prereqCode) {
        this.prerequisites.add(prereqCode);
    }

    @Override
    public String toString() {
        return moduleCode + " " + moduleName;
    }

    public String toStringPrerequisites() {
        if (prerequisites.isEmpty()) {
            return moduleCode + " has no prerequisites.";
        }
        return "Prerequisites for " + moduleCode +
                ": " + String.join(", ", prerequisites);
    }
}
