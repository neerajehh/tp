## **Acknowledgements**
*list of sources of all reused/adapted ideas, code, documentation and third party libraries with links to original source

## **Setup and Getting started**
Refer to the guide [_Getting started_](link).


## **Design**
### Architecture
The ***Architecture Diagram*** explains the high-level design of the App.
[TODO: paste uml class diagram]

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

[TODO: paste uml sequence diagram]


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




