package seedu.classmate;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class ModulesLoader {
    private static final String CORE_MODULES_PATH = "data/core-modules.txt";
    private static final String SPECIALISATION_MODULES_PATH = "data/specialisation-modules.txt";
    private static final int NUMBER_OF_TOKENS_IN_CORE_MODULE_FILE_LINE = 4;
    private static final int NUMBER_OF_TOKENS_IN_SPECIALISATION_MODULE_FILE_LINE = 6;

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

            if (tokens.length != NUMBER_OF_TOKENS_IN_CORE_MODULE_FILE_LINE) {
                throw new ClassMateException(
                        "Invalid line in core-modules.txt: " + line);
            }

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
