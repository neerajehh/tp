package seedu.classmate;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class ModulesLoader {
    private static final String CORE_MODULES_PATH = "./data/core-modules.txt";
    private static final String SPECIALISATION_MODULES_PATH = "./data/specialisation-modules.txt";
    private static final int NUMBER_OF_TOKENS_IN_CORE_MODULE_FILE_LINE = 4;
    private static final int NUMBER_OF_TOKENS_IN_SPECIALISATION_MODULE_FILE_LINE = 6;

    public void ensureDataFilesExist() throws ClassMateException {
        File coreModulesFile = new File(CORE_MODULES_PATH);
        File coreModulesDirectory = coreModulesFile.getParentFile();
        File specialisationModulesFile = new File(SPECIALISATION_MODULES_PATH);
        File specialisationModulesDirectory = specialisationModulesFile.getParentFile();

        if ((coreModulesDirectory != null && !coreModulesDirectory.exists()) ||
                (specialisationModulesDirectory != null && !specialisationModulesDirectory.exists())) {
            throw new ClassMateException("The core modules and/or specialisation modules files do not exist");
        }
    }

    public ArrayList<Module> loadCoreModules() throws ClassMateException {
        ArrayList<Module> coreModulesArrayList = new ArrayList<>();
        File file = new File(CORE_MODULES_PATH);

        try {
            Scanner scanner = new Scanner(file);
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
        } catch (FileNotFoundException e) {
            throw new ClassMateException("The core modules file does not exist");
        }

        return coreModulesArrayList;
    }

    public HashMap<String, ArrayList<Module>> loadSpecialisationModules() throws ClassMateException {
        HashMap<String, ArrayList<Module>> specialisationMap = new HashMap<>();
        File file = new File(SPECIALISATION_MODULES_PATH);

        try {
            Scanner scanner = new Scanner(file);
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
        } catch (FileNotFoundException e) {
            throw new ClassMateException("The specialisation modules file does not exist");
        }

        return specialisationMap;
    }
}
