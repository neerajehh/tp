package seedu.classmate;

import seedu.classmate.commands.ByeCommand;
import seedu.classmate.commands.Command;
import seedu.classmate.commands.HelpCommand;
import seedu.classmate.commands.MarkDoneCommand;
import seedu.classmate.commands.PrereqCommand;
import seedu.classmate.commands.PrintModuleInfoCommand;
import seedu.classmate.commands.QueryModuleAvailabilityCommand;
import seedu.classmate.commands.SpecialisationInfoCommand;
import seedu.classmate.commands.ViewDoneCommand;
import seedu.classmate.commands.ViewGradReqsCommand;
import seedu.classmate.commands.ViewSpecialisationsCommand;
import seedu.classmate.commands.CheckPrereqStatusCommand;
import seedu.classmate.commands.CheckProfileCommand;
import seedu.classmate.commands.SetSpecializationCommand;

import java.util.ArrayList;

public class CommandManager {

    public static Command createCommand(String commandWord, String arguments,
            ArrayList<String> completedModules, Storage storage, UserProfile userProfile) {
        switch (commandWord) {
        case "help":
            return new HelpCommand();

        case "bye":
            return new ByeCommand();

        case "viewgradreqs":
            return new ViewGradReqsCommand();

        case "viewprereqs":
            return new PrereqCommand(arguments);

        case "viewmoduleinfo":
            return new PrintModuleInfoCommand(arguments);

        case "querymoduleavailability":
            return new QueryModuleAvailabilityCommand(arguments);

        case "viewspecialisations":
            return new ViewSpecialisationsCommand();

        case "viewspecialisationinfo":
            return new SpecialisationInfoCommand(arguments);

        case "markdone":
            return new MarkDoneCommand(arguments, completedModules, storage);

        case "viewdone":
            return new ViewDoneCommand(completedModules);

        case "checkprereqstatus":
            return new CheckPrereqStatusCommand(arguments, completedModules);

        case "setspecialization":
            return new SetSpecializationCommand(arguments, userProfile);

        case "checkprofile":
            return new CheckProfileCommand(userProfile);

        default:
            throw new ClassMateException("Unknown command. Enter 'help' for available commands.");
        }
    }
}
