package seedu.classmate;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;


/**
 * Handles loading and validation of module information from .txt file. There are two methods {@code loadCoreModules}
 * and {@code loadSpecialisationModules} which loads the module information from the core-modules.txt and
 * specialisation-modules.txt files respectively, performs a validation of the expected
 * number of token and splits the data into tokens. An ArrayList is returned by the {@code loadCoreModules} method
 * while a HasMap is returned by the {@code loadSpecialisationModules} method.
 */
public class ModulesLoader {
    private static final String CORE_MODULES_PATH = "data/core-modules.txt";
    private static final String SPECIALISATION_MODULES_PATH = "data/specialisation-modules.txt";
    private static final int NUMBER_OF_TOKENS_IN_CORE_MODULE_FILE_LINE = 4;
    private static final int NUMBER_OF_TOKENS_IN_SPECIALISATION_MODULE_FILE_LINE = 6;


    /**
     * Load the core-modules.txt file from storage and parse each line into 4 tokens and adds them to an ArrayList.
     *
     * @return An ArrayList of {@code Module} objects which represent the major modules a CEG student has to take.
     * @throws ClassMateException If there is an error in loading the file or breaking a line into tokens.
     */
    public ArrayList<Module> loadCoreModules() throws ClassMateException {
        ArrayList<Module> coreModulesArrayList = new ArrayList<>();
        InputStream inputStream = ModulesLoader.class
                .getClassLoader()
                .getResourceAsStream(CORE_MODULES_PATH);

        if (inputStream == null) {
            throw new ClassMateException("Core modules file does not exist");
        }

        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            String[] tokens = line.split(",");

            assert tokens.length == NUMBER_OF_TOKENS_IN_CORE_MODULE_FILE_LINE : "The line in " +
                    "core-modules.txt is invalid: " + line;

            String moduleName = tokens[0];
            String moduleCode = tokens[1];
            String semester = tokens[2];
            String prerequisites = tokens[3];

            Module module = new Module(moduleCode, moduleName);
            module.setSemester(semester);

            if (!prerequisites.equals("None")) {
                String[] prerequisiteCodes = prerequisites.split("\\|");

                for (String prerequisiteCode : prerequisiteCodes) {
                    module.addPrerequisite(prerequisiteCode);
                }
            }
            coreModulesArrayList.add(module);
        }

        scanner.close();
        return coreModulesArrayList;
    }

    /**
     * Load specialisation-modules.txt file from storage and parse each line into 6 tokens, adding them to a HashMap.
     *
     * @return A HashMap where the keys are the specialisation names and values are ArrayList of {@code Module} objects
     *         representing the modules that are within a specialisation.
     *
     * @throws ClassMateException If there is an error in loading the file or breaking a line into tokens.
     */
    public HashMap<String, ArrayList<Module>> loadSpecialisationModules() throws ClassMateException {
        HashMap<String, ArrayList<Module>> specialisationMap = new HashMap<>();
        InputStream inputStream = ModulesLoader.class
                .getClassLoader()
                .getResourceAsStream(SPECIALISATION_MODULES_PATH);

        if (inputStream == null) {
            throw new ClassMateException("Specialisation modules file does not exist");
        }

        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            String[] tokens = line.split(",");
            assert tokens.length == NUMBER_OF_TOKENS_IN_SPECIALISATION_MODULE_FILE_LINE : "The line in " +
                    "specialisation-modules.txt is invalid: " + line;

            String specialisationName = tokens[0];
            String moduleType = tokens[1];
            String moduleName = tokens[2];
            String moduleCode = tokens[3];
            String semester = tokens[4];
            String prerequisites = tokens[5];

            specialisationMap.putIfAbsent(specialisationName, new ArrayList<>());

            Module module = new Module(moduleCode, moduleName);
            module.setSemester(semester);
            module.setModuleType(moduleType);

            if (!prerequisites.equals("None")) {
                String[] prerequisiteCodes = prerequisites.split("\\|");
                for (String prerequisiteCode : prerequisiteCodes) {
                    module.addPrerequisite(prerequisiteCode);
                }
            }

            specialisationMap.get(specialisationName).add(module);
        }

        scanner.close();
        return specialisationMap;
    }
}
