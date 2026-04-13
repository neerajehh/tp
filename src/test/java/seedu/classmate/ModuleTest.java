package seedu.classmate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Contains unit tests for the Module class.
 * Tests for empty module codes and whether all modules are unique.
 */
class ModuleTest {
    @Test
    void addPrerequisite_duplicateInput_noChange() {
        Module module = new Module("CG1111A", "Engineering Principles and Practice I", 4);
        module.addPrerequisite("CS1010");
        module.addPrerequisite("CS1010");
        assertEquals(1, module.getPrerequisites().size(), "Prerequisite module should not be duplicated.");
    }

    @Test
    void module_nullName_throwsException() {
        ClassMateException e = assertThrows(ClassMateException.class, () -> {
            new Module("", "", 4);
        });
        assertEquals("Module details cannot be empty.", e.getMessage());
    }

    @Test
    void toString_validModule_returnsCorrectString() {
        Module module = new Module("CS2113", "Software Engineering & OOP", 4);
        assertEquals("CS2113 Software Engineering & OOP", module.toString());
    }

    @Test
    void toStringPrerequisites_noPrerequisites_returnsNoPrereqMessage() {
        Module module = new Module("CS1010", "Programming Methodology", 4);
        assertEquals("CS1010 has no prerequisites.", module.toStringPrerequisites());
    }

    @Test
    void addPrerequisite_validInput_added() {
        Module module = new Module("CS2113", "Software Engineering & OOP", 4);
        module.addPrerequisite("CS2040C");
        assertEquals(1, module.getPrerequisites().size());
    }


    @Test
    void getModuleCode_validModule_returnsCode() {
        Module module = new Module("CS2113", "Software Engineering & OOP", 4);
        assertEquals("CS2113", module.getModuleCode());
    }

    @Test
    void printInfo_noPrerequisites_showsCanTakeYes() {
        Module module = new Module("CS1010", "Programming Methodology", 4);
        String info = module.printInfo();
        assertTrue(info.contains("Can take: YES"));
    }

    @Test
    void printInfo_withPrerequisites_showsCanTakeNo() {
        Module module = new Module("CS2113", "Software Engineering & OOP", 4);
        module.addPrerequisite("CS2040C");
        String info = module.printInfo();
        assertTrue(info.contains("Can take: NO"));
    }

    @Test
    void checkAvailability_bothSemesters_returnsCorrectMessage() {
        Module module = new Module("CS1010", "Programming Methodology", 4);
        String result = module.checkAvailability("sem1");
        assertEquals("CS1010 is available in both Semester 1 and Semester 2.", result);
    }

    @Test
    void checkAvailability_sem2Only_returnsYes() {
        Module module = new Module("CG2023", "Signals & Systems",4);
        module.setSemester("2");
        String result = module.checkAvailability("sem2");
        assertEquals("Yes, CG2023 is only available in Semester 2.", result);
    }

    @Test
    void checkAvailability_wrongSem_returnsNo() {
        Module module = new Module("CG2027", "Transistor-level Digital Circuit", 4);
        module.setSemester("1");
        String result = module.checkAvailability("sem2");
        assertEquals("No, CG2027 is not available in Semester 2.", result);
    }

    @Test
    void addPrerequisites_multipleInputs_allAdded() {
        Module module = new Module("CS2113", "Software Engineering", 4);
        module.addPrerequisite("CS1010");
        module.addPrerequisite("CS2040C");
        module.addPrerequisite("EE2026");
        assertEquals(3, module.getPrerequisites().size(), "All three prerequisites should be added.");
        assertTrue(module.getPrerequisites().contains("CS2040C"));
    }

    @Test
    void checkAvailability_sem1Only_returnsYes() {
        Module module = new Module("EE2026", "Digital Design", 4);
        module.setSemester("1");
        String result = module.checkAvailability("sem1");
        assertEquals("Yes, EE2026 is only available in Semester 1.", result);
    }

    @Test
    void checkAvailability_invalidInput_returnsErrorMessage() {
        Module module = new Module("CS2113", "Software Engineering", 4);
        String result = module.checkAvailability("summer");
        assertEquals("Invalid semester. Please enter sem1 or sem2.", result);
    }

    @Test
    void printInfo_withCompletedPrerequisites_showsCanTakeYes() {
        Module module = new Module("CS2113", "Software Engineering", 4);
        module.addPrerequisite("CS2040C");

        java.util.ArrayList<String> completed = new java.util.ArrayList<>();
        completed.add("CS2040C");

        String info = module.printInfo(completed);
        assertTrue(info.contains("Can take: YES"), "Should be YES if prerequisites are in the completed list.");
    }

    @Test
    void printInfo_missingSomePrerequisites_showsCanTakeNo() {
        Module module = new Module("CS2113", "Software Engineering", 4);
        module.addPrerequisite("CS1010");
        module.addPrerequisite("CS2040C");

        java.util.ArrayList<String> completed = new java.util.ArrayList<>();
        completed.add("CS1010");
        // User still yet to fulfill all prerequisites, i.e. cs2040c

        String info = module.printInfo(completed);
        assertTrue(info.contains("Can take: NO"), "Should be NO if only some prerequisites are completed.");
    }

    @Test
    void printPrereqTree_noPrerequisites_returnsModuleOnly() {
        Module module = new Module("CS1010", "Programming Methodology", 4);
        Major major = new Major(new ArrayList<>());

        String tree = module.printPrereqTree(major);

        assertTrue(tree.contains("CS1010"));
    }

    @Test
    void printPrereqTree_withPrerequisites_printsTree() {
        Module cs1010 = new Module("CS1010", "Programming Methodology", 4);
        Module cs2040 = new Module("CS2040", "Data Structures", 4);
        Module cs2113 = new Module("CS2113", "Software Engineering & OOP", 4);

        cs2040.addPrerequisite("CS1010");
        cs2113.addPrerequisite("CS2040");

        ArrayList<Module> modules = new ArrayList<>();
        modules.add(cs1010);
        modules.add(cs2040);
        modules.add(cs2113);

        Major major = new Major(modules);

        String tree = cs2113.printPrereqTree(major);

        assertTrue(tree.contains("CS2113"));
        assertTrue(tree.contains("CS2040"));
        assertTrue(tree.contains("CS1010"));
    }

    @Test
    void toStringPrerequisites_withPrerequisites_returnsFormattedList() {
        Module module = new Module("CS2113", "Software Engineering & OOP", 4);
        module.addPrerequisite("CS2040C");
        module.addPrerequisite("CS2103T");

        assertEquals("Prerequisites for CS2113: CS2040C, CS2103T",
                module.toStringPrerequisites());
    }

    @Test
    void getModuleName_validModule_returnsName() {
        Module module = new Module("CS2113", "Software Engineering & OOP", 4);
        assertEquals("Software Engineering & OOP", module.getModuleName());
    }
}
