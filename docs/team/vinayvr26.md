# VinayVR26's Project Portfolio Page

## Project: ClassMate

ClassMate is an academic assistance application to help CEG students to make their university journey easier. 
The user interacts with it using a CLI. It is written in Java.

### These are my contributions to the project.

#### Features

* **New Feature: `ModulesLoader`**: Added logic to load information, that the application needs.
   * What it does: On application startup, two text files - `core-modules.txt` and `specialisation-modules.txt`,
     containing module information, is loaded into the application to be used to answer user's requests. 
   * Justification: This feature helps to abstract the module information to text files rather than many repetitive,
     hard-coded lines of information. Moreover, the data needs to be loaded only once, hence reducing the overall
     computational cost.

* **New Feature: `viewSpecialisations`**: Added logic to display all the specialisations available.
   * What it does: Displays all the specialisations, when the user inputs the command `viewSpecialisations`. 
   * Justification: Shows an overview of what specialisations are available, helping users to get a better picture of 
    what options they have.

* **New Feature: `viewSpecialisationInfo`**: Added logic to display details about each specialisation.
   * What it does: Displays the description, list of core and elective modules and the requirement to fulfill the
     specialisation.
   * Justification: Helps users understand what the specialisation is about and the requirements, making them have a 
     better understanding if it aligns with their interest and academic timeline.

* **Code contributed:** [RepoSense link](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=vinayvr26)

* **Documentation:**
   * **User Guide:** 
     * Added documentation for the feature `help` 
     * Added more important information for the feature `viewSpecialisations`, to make it clearer
   * **Developer Guide:**
     * Added documentation for the features `viewSpecialisations` and `viewSpecialisationInfo`
   * **UML Diagrams:**
     * Created Sequence Diagrams for `viewSpecialisations` and `viewSpecialisationInfo` commands

* **Contributions to team tasks:**
   * When the application transitioned to using the `ModulesLoader` class to load information, many test cases written 
     by my teammates did not work. I helped to fix them to ensure that all test cases continued to pass.
   * Added logic to `command` classes to ensure that a module is checked if it belongs to the `Specialisation` class, 
     and only if this check fails, does the application output that the module is not found. (This check was missing,
     causing legitimate modules that only belonged to the `Specialisation` class to be flagged as not found)
   * Made JavaDoc comments clearer for methods, that belonged to my teammates
   * Modified other classes that were not created by me - `ClassMate`, `Major`, `Module` to incorporate the changes when
     I created the `ModulesLoader` class to load data, to ensure the loading of data was fully incorporated with all
     classes.
   * Fixed checkstyle errors (such as removing * imports)