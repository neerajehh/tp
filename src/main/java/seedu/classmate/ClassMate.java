package seedu.classmate;
import seedu.classmate.commands.Command;

import java.util.Scanner;
import java.util.logging.Logger;

public class ClassMate {
    private static final Logger logger = Logger.getLogger(ClassMate.class.getName());
    private static final Ui ui = new Ui();

    /**
     * Main entry-point for the java.classmate.Classmate application.
     */
    public static void main(String[] args) {
        ui.showWelcome();
        logger.info("ClassMate application started.");

        Scanner in = new Scanner(System.in);
        Major major = new Major();
        SpecialisationOverview specOverview = new SpecialisationOverview();

        boolean isExit = false; // Flag to determine whether to exit Program

        while (!isExit) {
            /* TODO: to let input reading be handled by Parser class
             *    Alternatively, have a CommandManager to detect commandWord
             *    and return new XCommand subclasses
            */
            // Guard clause to check if there's a new line before reading it
            if (!in.hasNextLine()) {
                break;
            }
            String input = in.nextLine();
            assert input != null : "Input should not be null";
            logger.info("User input: " + input);

            try {
                Command command = Parser.parse(input);
                command.executeCommand(major, ui, specOverview);

                isExit = command.isExit();
            } catch (ClassMateException e) {
                ui.showError(e.getMessage());
            }
        }
        logger.info("ClassMate application exited.");
    }

}
