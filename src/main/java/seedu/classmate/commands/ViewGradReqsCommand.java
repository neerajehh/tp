package seedu.classmate.commands;

import seedu.classmate.Major;
import seedu.classmate.Module;
import seedu.classmate.SpecialisationOverview;
import seedu.classmate.Ui;
import seedu.classmate.UserProfile;

import java.util.ArrayList;

// @@author neerajehh
public class ViewGradReqsCommand extends Command {
    private final UserProfile userProfile;

    public ViewGradReqsCommand(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public void executeCommand(Major major, Ui ui, SpecialisationOverview specialisationOverview) {
        ArrayList<String> completed = userProfile.getUserCompletedModules();
        ArrayList<Module> remaining = new ArrayList<>();
        for (Module m : major.getCoreModules()) {
            if (!completed.contains(m.getModuleCode())) {
                remaining.add(m);
            }
        }
        ui.showGraduationRequirements(remaining);
    }
}
// @@author
