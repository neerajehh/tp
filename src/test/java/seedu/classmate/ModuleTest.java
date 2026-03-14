package seedu.classmate;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void addPrerequisites_multipleInputs_allAdded() {
        Module module = new Module("CS2113", "Software Engineering & OOP");
        module.addPrerequisites("CS2040C", "CS1010");
        assertEquals(2, module.getPrerequisites().size());
    }
    @Test
    void addPrerequisite_validInput_added() {
        Module module = new Module("CS2113", "Software Engineering & OOP");

        module.addPrerequisite("CS2040C");

        assertEquals(1, module.getPrerequisites().size());
    }

    @Test
    void addPrerequisites_duplicateInputs_onlyOneAdded() {
        Module module = new Module("CS2113", "Software Engineering & OOP");

        module.addPrerequisites("CS2040C", "CS2040C");

        assertEquals(1, module.getPrerequisites().size());
    }

    @Test
    void getModuleCode_validModule_returnsCode() {
        Module module = new Module("CS2113", "Software Engineering & OOP");

        assertEquals("CS2113", module.getModuleCode());
    }
}
