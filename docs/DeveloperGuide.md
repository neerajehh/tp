## **Acknowledgements**
*list of sources of all reused/adapted ideas, code, documentation and third party libraries with links to original source

## **Setup and Getting started**
Refer to the guide [_Getting started_](link).


## **Design**
### Architecture
The architecture of ClassMate is kept simple as all components reside in a single package. The main components are described in the Components section below.

### Components:
* `ClassMate`: Initialises other components and houses the `main` execution loop.
* `Parser`: Decompose raw user input strings into a command word and arguments, then delegating the creation of a `Command` object.
* `Command`: An abstract base class for all instructions (e.g., `viewGradReqsCommand`, `ByeCommand`). Each subclass encapsulates the specific behavior of a user action and signals back to `ClassMate` if the app should terminate via the `isExit` flag.
* `Ui`: Handles all interactions with the user. It is the only class that contains `System.out.println` calls, ensuring a consistent look and feel across the application.
* `UserProfile`: Holds the state of the user’s academic progress, including their specialisation and modules taken.
* `ModulesLoader`: Loads the module information for major and specialisation modules to be used.
* `Storage`: Responsible for reading from and writing data to the hard disk, ensuring user data is saved between sessions. 

### Interaction between architecture components:
The ***Sequence Diagram*** shows how the components interact with each other for the scenario when the user issues the command `viewPrereqs`

```
User      ClassMate    Parser   CommandManager  PrereqCommand  Major  SpecOvw
 |            |           |            |              |           |      |
 |--viewPrereqs CS2113---->           |              |           |      |
 |            |--parse(input)-------->|              |           |      |
 |            |           |--createCommand()-------->|           |      |
 |            |           |            |<--PrereqCmd-------------|      |
 |            |--executeCommand(...)---------------->|           |      |
 |            |           |            |             |--findModule(code)>|
 |            |           |            |             |<--module----------|  [if null: also checks SpecOvw]
 |            |           |            |             |--printPrereqTree(major)
 |            |           |            |             |<--tree string-----|
 |<--prints prereq tree---|            |             |           |      |
```


## **Implementation of Features**

### **Viewing Module Information**
<Uses `Parser`, `ViewModuleInfoCommand`, `Module`, `Major`, `SpecialisationOverview`, `Ui`>

The `viewModuleInfo` feature displays a module's code, name, units, semester, prerequisites and whether the user 
can take it.


### **Viewing Graduation Requirements** 
<Hardcoded, used classes Module, Major, Ui>

### **View Module Prerequisite Trees**
<Uses `CommandManager`, `PrereqCommand`, `Module`, `Major`, `Ui`>

The `viewPrereqs` feature displays a module’s prerequisite structure as a tree.

**Execution Flow:**
- User inputs `viewPrereqs`.
- `Parser` and `CommandManager` create a `PrereqCommand`.
- `PrereqCommand` calls `module.printPrereqTree(major)`.
- Output is formatted and displayed via `Ui`.

**Core Logic:**
- `printPrereqTree` initialises the process and optionally lists parent modules.
- `printPrereqTreeHelper` recursively builds the tree using prefixes and symbols (`├──`, `└──`) to represent hierarchy.
- If a prerequisite module exists, recursion continues; otherwise, it is printed as a leaf.

### Specialisation



## **Appendix: Requirements**
### **Product scope**
Target user profile:
* Needs to know and track the modules are required to graduate
* Needs to know what prerequisites need to be taken first
* Wants the list of modules easily tracked and accessed from their local desktop
* Prefers typing over mouse interaction 

Value proposition: A CLI-based chatbot to assist with timetable and course schedule planning for NUS CEG UGs.

### **User Stories**
Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

### Appendix: User Stories

| Priority | As a ...                                  | I want to ...                                 | So that I can ...                                                                 |
| -------- | ----------------------------------------- |-----------------------------------------------|-----------------------------------------------------------------------------------|
| `* * *`  | new user                                  | view usage instructions                       | refer to instructions when I forget how to use the App                            |
| `* * *`  | student                                   | view an overview of graduation requirements   | understand the milestones I need to reach to graduate                             |
| `* * *`  | student                                   | view the prerequisite tree for a module       | identify the sequence of modules required for my target course                    |
| `* * *`  | student                                   | view if a module is offered in a semester     | plan my timetable based on module availability                                    |
| `* * *`  | student looking to specialise             | view modules tied to specific specialisations | explore potential academic tracks and take necessary prerequisites                |
| `* * *`  | student looking to specialise             | view overview of a specialisation             | understand what the specialisation is about to see if it algins with my interests |
| `* *`    | recurring user                            | save my profile and academic history          | avoid the repetitive task of re-entering completed modules                        |
| `* *`    | user who wants a visual overview          | view a progress tracker for my degree         | stay motivated and ensure I am on track for graduation                            |
| `* *`    | student                                   | view modules using keywords                   | find relevant courses even if I do not know the exact module code                 |
| `*`      | user wanting non-core modules             | view non-core modules using keywords          | find interesting electives outside of my primary major                            |
| `*`      | user easily overwhelmed by info           | view modules filtered by level or subject     | narrow down my choices to suit my current year of study                           |





### **Checking Prerequisite Completion Status**
<Uses `Parser`, `CommandManager`, `CheckPrereqStatusCommand`, `Major`, `Module`, `Ui`>

The `checkPrereqStatus` feature shows which prerequisites for a module the user has completed and which are outstanding.

**Execution Flow:**
1. User inputs `checkPrereqStatus MODULE_CODE`.
2. `Parser` extracts the command word and module code argument.
3. `CommandManager` creates a `CheckPrereqStatusCommand` with the module code and completed modules list.
4. `executeCommand` calls `major.findModule(moduleCode)` to retrieve the module.
5. The command iterates over the module's prerequisites and checks each against `completedModules`.
6. Results are printed showing `[DONE]` or `[TODO]` for each prerequisite.

The sequence diagram below illustrates how the components interact when `checkPrereqStatus CS2113` is executed:
```
User        ClassMate      Parser        CommandManager     CheckPrereqStatusCommand    Major       Module
 |               |             |                |                      |                  |            |
 |--checkPrereqStatus CS2113-->|             |                      |                  |            |
 |               |--parse(...)-|>            |                      |                  |            |
 |               |             |--createCommand(...)--------------->|                  |            |
 |               |             |             |<-----CheckPrereqStatusCommand------------|            |
 |               |--executeCommand(...)------------------------------->|                |            |
 |               |             |             |                      |--findModule(...)>|            |
 |               |             |             |                      |<-----module-------|            |
 |               |             |             |                      |--getPrerequisites()---------->|
 |               |             |             |                      |<-----prerequisites------------|
 |               |             |             |              [loop for each prerequisite]             |
 |               |             |             |                      |--check completedModules        |
 |<--print [DONE]/[TODO] status--------------|                      |                  |            |
```

**Design Considerations:**
- `completedModules` is stored as an `ArrayList` for simplicity. An alternative `HashSet` would give O(1) lookup but `ArrayList` is sufficient given the small number of modules a student completes.

### **Marking Modules as Done**
<Uses `MarkDoneCommand`, `Storage`, `Major`>

The `markDone` feature allows users to mark a module as completed. The completed module is added to the `completedModules` list and saved to disk via `Storage` so it persists across sessions.

### **Querying Module Availability**
<Uses `QueryModuleAvailabilityCommand`, `Module`>

The `queryModuleAvailability` feature checks if a module is offered in a given semester (sem1 or sem2) by calling `module.checkAvailability(semester)`. The result is printed directly to the user.

## **Appendix C: Non-Functional Requirements**

1. Should work on any mainstream OS (Windows, macOS, Linux) with Java 17 installed.
2. Should respond to any command within 2 seconds on a typical desktop.
3. Should be usable by a student with no prior CLI experience after reading the User Guide.
4. The save file should be human-readable and editable with a plain text editor.
5. The application should work fully without an internet connection.

## **Appendix D: Glossary**

* **CEG** - Computer Engineering, a degree programme at NUS.
* **Module** - A course unit at NUS identified by a module code (e.g., CS2113).
* **Prerequisite** - A module that must be completed before taking another module.
* **Specialisation** - An optional academic track within CEG (e.g., Robotics, Internet of Things).
* **CLI** - Command Line Interface. A text-based interface where users interact by typing commands.
* **Semester** - A study term at NUS, either Semester 1 (August-December) or Semester 2 (January-May).

## **Appendix E: Instructions for Manual Testing**

### Launching the application
1. Download the JAR file and place it in an empty folder.
2. Open a terminal and run `java -jar ClassMate.jar`.
3. Expected: Welcome message with ClassMate logo is shown.

### Viewing help
- Input: `help`
- Expected: List of all available commands is displayed.

### Viewing graduation requirements
- Input: `viewGradReqs`
- Expected: List of CEG core modules is displayed.

### Viewing module information
- Input: `viewModuleInfo CS2113`
- Expected: Module code, name, units, semester, prerequisites and whether you can take it are shown.

### Querying module availability
- Input: `queryModuleAvailability CG2023 sem1`
- Expected: Message indicating CG2023 is not available in Semester 1.
- Input: `queryModuleAvailability CS2113 sem2`
- Expected: Message indicating CS2113 is available in both semesters.

### Marking a module as done
- Input: `markDone CS2040C`
- Expected: Success message confirming CS2040C is marked as done.
- Input: `markDone CS2040C` (again)
- Expected: Message saying CS2040C is already marked as done.

### Checking prerequisite status
- Prerequisites: First run `markDone CS2040C`
- Input: `checkPrereqStatus CS2113`
- Expected: CS2040C shows as `[DONE]`, any other prerequisites show as `[TODO]`.

### Viewing completed modules
- Input: `viewDone`
- Expected: List of all modules marked as done.

### Persistence across sessions
1. Mark some modules as done with `markDone`.
2. Exit with `bye`.
3. Relaunch the application.
4. Input: `viewDone`
5. Expected: Previously marked modules are still listed.

### Exiting the application
- Input: `bye`
- Expected: Goodbye message is shown and application exits.
