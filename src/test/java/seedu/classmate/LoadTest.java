package seedu.classmate;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadTest {

    @Test
    public void loadCoreModules_successful_load() throws ClassMateException {
        ModulesLoader coreModulesLoader = new ModulesLoader();
        ArrayList<Module> coreModules = coreModulesLoader.loadCoreModules();

        Module secondModule = coreModules.get(1);
        assertEquals("MA1512", secondModule.getModuleCode());
    }

    @Test
    public void loadSpecialisationInternetOfThingsModules_successful_load()
            throws ClassMateException {
        ModulesLoader specialisationModulesLoader = new ModulesLoader();
        HashMap<String, ArrayList<Module>> specialisationModules = specialisationModulesLoader
                .loadSpecialisationModules();

        ArrayList<Module> iotModules = specialisationModules.get("Internet of Things");
        Module secondModule = iotModules.get(1);
        assertEquals("EE4211", secondModule.getModuleCode());
    }
}
