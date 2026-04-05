package seedu.classmate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UiTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void showWelcome_printsWelcomeMessage() {
        Ui ui = new Ui();
        ui.showWelcome();

        String output = outContent.toString();
        assertTrue(output.contains("Welcome to"));
        assertTrue(output.contains("CEG course planning assistant"));
        assertTrue(output.contains("Type 'help' to see available commands."));
    }

    @Test
    void showError_printsFormattedErrorMessage() {
        Ui ui = new Ui();
        ui.showError("Something went wrong");

        String output = outContent.toString();
        assertTrue(output.contains("ERROR: Something went wrong"));
    }

    @Test
    void showGoodbye_printsGoodbyeMessage() {
        Ui.showGoodbye();

        String output = outContent.toString();
        assertTrue(output.contains("Goodbye! Happy course planning!"));
    }

    @Test
    void showHelp_printsHelpMessage() {
        Ui.showHelp();

        String output = outContent.toString();
        assertTrue(output.contains("Available commands:"));
        assertTrue(output.contains("help"));
    }

    @Test
    void showAllSpecialisations_printsSpecialisationList() {
        ArrayList<Module> modules = new ArrayList<>();
        Specialisation spec = new Specialisation("Robotics", modules);

        ArrayList<Specialisation> specs = new ArrayList<>();
        specs.add(spec);

        Ui.showAllSpecialisations(specs);

        String output = outContent.toString();
        assertTrue(output.contains("List of all CEG Specialisations:"));
        assertTrue(output.contains("1. Robotics"));
    }

    @Test
    void showSpecialisationDetails_printsDetails() {
        ArrayList<Module> modules = new ArrayList<>();
        Module m = new Module("CS2113", "Software Engineering & OOP", 4);
        modules.add(m);

        Specialisation spec = new Specialisation("Robotics", modules);
        Ui ui = new Ui();

        ui.showSpecialisationDetails(spec);

        String output = outContent.toString();
        assertTrue(output.contains("Specialisation: Robotics"));
        assertTrue(output.contains("Description:"));
        assertTrue(output.contains("Core Modules:"));
        assertTrue(output.contains("CS2113 : Software Engineering & OOP"));
    }

}
