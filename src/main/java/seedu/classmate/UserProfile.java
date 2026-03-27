package seedu.classmate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserProfile {
    private static final String MODULES_PATH = "data/completedModules.txt";
    private static final String SPEC_PATH = "data/specialization.txt";

    private String specialization;
    private ArrayList<String> completedModules;

    public UserProfile() {
        this.specialization = "Not Set";
        this.completedModules = new ArrayList<>();
        loadData();
    }

    private void loadData() {
        File specFile = new File(SPEC_PATH);
        if (specFile.exists()) {
            try (Scanner scanner = new Scanner(specFile)) {
                if (scanner.hasNextLine()) {
                    this.specialization = scanner.nextLine().trim();
                }
            } catch (IOException e) {
                System.out.println("Could not load user's specialization.");
            }
        }

        File modulesFile = new File(MODULES_PATH);
        if (modulesFile.exists()) {
            try (Scanner scanner = new Scanner(modulesFile)) {
                while (scanner.hasNextLine()) {
                    String module = scanner.nextLine().trim();
                    if (!module.isEmpty()) {
                        this.completedModules.add(module);
                    }
                }
            } catch (IOException e) {
                System.out.println("Could not load user's completed modules.");
            }
        }
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String newSpecialization) {
        this.specialization = newSpecialization;

        try {
            FileWriter writer = new FileWriter(SPEC_PATH, false);
            writer.write(newSpecialization);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving specialization.");
        }
    }

    public ArrayList<String> getCompletedModules() {
        return completedModules;
    }
}
