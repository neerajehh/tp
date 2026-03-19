package seedu.classmate;

import seedu.classmate.commands.ByeCommand;
import seedu.classmate.commands.Command;

import java.util.Scanner;
import java.util.logging.Logger;

public class ClassMate {
    private static final Logger logger = Logger.getLogger(ClassMate.class.getName());
    private static final Display display = new Display();

    /**
     * Main entry-point for the java.classmate.Classmate application.
     */
    public static void main(String[] args) {

        assert false : "dummy assertion set to fail";

        display.showWelcome();
        logger.info("ClassMate application started.");
        Scanner in = new Scanner(System.in);
        Major major = new Major();
        SpecialisationOverview specOverview = new SpecialisationOverview();
        assert major != null : "Major should not be null";
        while (true) {
            String input = in.nextLine();
            assert input != null : "Input should not be null";
            logger.info("User input: " + input);
            try {
                Command command = Parser.parse(input);
                command.executeCommand(major, display, specOverview);

                if (command instanceof ByeCommand) {
                    logger.info("Classmate application exited.");
                    return;
                }

            } catch (ClassMateException e) {
                logger.warning("ClassMateException: " + e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }

}
