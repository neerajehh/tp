package seedu.classmate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for the Module class.
 * Tests for empty module codes and whether all modules are unique.
 */
class ModuleTest {
    @Test
    void addPrerequisite_duplicateInput_noChange() {
        Module module = new Module("CG1111A", "Engineering Principles and Practice I");
        module.addPrerequisite("CS1010");
        module.addPrerequisite("CS1010");
        assertEquals(1, module.getPrerequisites().size(), "Prerequisite module should not be duplicated.");
    }

    @Test
    void module_nullName_throwsException() {
        ClassMateException e = assertThrows(ClassMateException.class, () -> {
            new Module("", "");
        });
        assertEquals("Module details cannot be empty.", e.getMessage());
    }

    @Test
    void toString_validModule_returnsCorrectString() {
        Module module = new Module("CS2113", "Software Engineering & OOP");
        assertEquals("CS2113 Software Engineering & OOP", module.toString());
    }

    @Test
    void toStringPrerequisites_noPrerequisites_returnsNoPrereqMessage() {
        Module module = new Module("CS1010", "Programming Methodology");
        assertEquals("CS1010 has no prerequisites.", module.toStringPrerequisites());
    }

    @Test
    void addPrerequisite_validInput_added() {
        Module module = new Module("CS2113", "Software Engineering & OOP");
        module.addPrerequisite("CS2040C");
        assertEquals(1, module.getPrerequisites().size());
    }


    @Test
    void getModuleCode_validModule_returnsCode() {
        Module module = new Module("CS2113", "Software Engineering & OOP");
        assertEquals("CS2113", module.getModuleCode());
    }

    @Test
    void printInfo_noPrerequisites_showsCanTakeYes() {
        Module module = new Module("CS1010", "Programming Methodology");
        String info = module.printInfo();
        assertTrue(info.contains("Can take: YES"));
    }

    @Test
    void printInfo_withPrerequisites_showsCanTakeNo() {
        Module module = new Module("CS2113", "Software Engineering & OOP");
        module.addPrerequisite("CS2040C");
        String info = module.printInfo();
        assertTrue(info.contains("Can take: NO"));
    }

    @Test
    void checkAvailability_bothSemesters_returnsCorrectMessage() {
        Module module = new Module("CS1010", "Programming Methodology");
        String result = module.checkAvailability("sem1");
        assertEquals("CS1010 is available in both Semester 1 and Semester 2.", result);
    }

    @Test
    void checkAvailability_sem2Only_returnsYes() {
        Module module = new Module("CG2023", "Signals & Systems");
        module.setSemester("2");
        String result = module.checkAvailability("sem2");
        assertEquals("Yes, CG2023 is only available in Semester 2.", result);
    }

    @Test
    void checkAvailability_wrongSem_returnsNo() {
        Module module = new Module("CG2027", "Transistor-level Digital Circuit");
        module.setSemester("1");
        String result = module.checkAvailability("sem2");
        assertEquals("No, CG2027 is not available in sem2.", result);
    }
}

