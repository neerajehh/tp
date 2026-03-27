package seedu.classmate;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class LoadTest {

    @Test
    public void loadCoreModules_successful_load() throws ClassMateException {
        ModulesLoader coreModulesLoader = new ModulesLoader();
        ArrayList<Module> coreModules = coreModulesLoader.loadCoreModules();

        Module secondModule =  coreModules.get(1);
        assertEquals("MA1512", secondModule.getModuleCode());
    }

    @Test
    public void loadSpecialisationModules_successful_load() throws ClassMateException {
        ModulesLoader specialisationModulesLoader = new ModulesLoader();
        ArrayList<Module> coreModules = specialisationModulesLoader.loadCoreModules();

        Module secondModule =  coreModules.get(1);
        assertEquals("EE4211", secondModule.getModuleCode());
    }
}
