# User Guide

ClassMate is a CLI-based chatbot that assists NUS UG CEG students with timetable and course schedule planning, so as to tailor to their academic pursuits and scheduling needs, while tracking progress toward graduation, specialisation, minor, and other degree requirements.

## Table of Contents
* [Quick Start](#quick-start)
* [Features](#features)
  * [General Commands](#general-commands)
  * [Major Requirements](#major-requirements)
  * [Specialisations](#specialisations)
  * [Profile & Progress Tracking](#profile--progress-tracking)
* [Command Summary](#command-summary)

## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `ClassMate.jar` from [here](https://github.com/AY2526S2-CS2113-W11-4/tp/releases).
3. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar ClassMate.jar` command to run the application.
---
## Features

> **Note on Command Format**
> * Words in `UPPER_CASE` are parameters to be supplied by the user (e.g., `markDone CS2113`).
> * Module codes and specialisation names are **case-insensitive**.
---
### General Commands
#### Viewing help: `help`
Displays a categorized list of all available commands and their usage formats.
* **Format:** `help`

#### Exiting the application: `bye`
Saves your current academic profile and exits ClassMate.
* **Format:** `bye`

---
### Major Requirements

#### Viewing major requirements: `viewGradReqs`

Shows a list of modules the student has to clear in order to graduate.

Format: `viewGradReqs`

Examples:
* `viewGradReqs`

### Viewing module info: `viewModuleInfo`

Shows details for a specific module, such as title, academic units, prerequisites, and whether the module can be taken.

Format: `viewModuleInfo MODULE_CODE`

* `MODULE_CODE` must be a valid module code. If the module code is not found, ClassMate will display an error message.
* `MODULE_CODE` must be non-empty, else ClassMate will prompt the user to provide one.
* `MODULE_CODE` is case-insensitive.
* The command works for both major modules and specialisation modules.

Examples:
* `viewModuleInfo CS2113`
* `viewModuleInfo CG2023`

Expected output:
```
 Code: CS2113
 Name: Software Engineering & Object-Oriented Programming
 Units: 4
 Semester: 1/2
 Prerequisites: CS2040C
 Can take: NO
```

### Checking module availability: `queryModuleAvailability`

Checks if a module is available in Semester 1 or Semester 2.

Format: `queryModuleAvailability MODULE_CODE sem1/sem2`

* `MODULE_CODE` must be a valid module code. If the module code is not found, ClassMate will display an error message.
* If the module code or semester is missing, ClassMate will display the correct format.
* `MODULE_CODE` is case-insensitive.
* Use `sem1` or `sem2` only.

Examples:
* `queryModuleAvailability CG2023 sem2`
* `queryModuleAvailability CS2113 sem1`

Expected output:
```
Yes, CG2023 is only available in Semester 2.
```

### Viewing prerequisites: `viewPrereqs`

Given a module code, view the prerequisite tree.

Format: `viewPrereqs MODULE_CODE`

* If the module code is missing, ClassMate will prompt the user to provide one.
* If the module is not found, ClassMate will display an error message.
* `MODULE_CODE` is case-insensitive.
* Shows the module’s prerequisite chain in tree form.
If the module has no prerequisites, the module will appear as the root of the prerequisite tree.

Examples:
* `viewPrereqs CG2028`
* `viewPrereqs CS2113`

Expected output: 
```
EE4204  
└── CS2113
    └── CS2040C
        └── CS1010
```

#### Checking prerequisite completion status: `checkPrereqStatus`

Checks which prerequisites for a module you have already completed and which are still outstanding.

Format: `checkPrereqStatus MODULE_CODE`

* `MODULE_CODE` is case-insensitive.
* The result is based on the user's saved academic profile.

Examples:
* `checkPrereqStatus CS2113`
* `checkPrereqStatus CG2271`

Expected output (if CS2040C is marked done):
```
Prerequisite Status for CS2113:
______________________________
Completed prerequisites:
  [DONE] CS2040C
______________________________
You have completed all prerequisites for CS2113!
```

Expected output (if no prerequisites completed):
```
Prerequisite Status for CS2113:
______________________________
Outstanding prerequisites:
  [TODO] CS2040C
______________________________
You still need to complete 1 prerequisite(s) before taking CS2113.
```

Expected output (if module has no prerequisites):
```
CS2113 has no prerequisites. You can take it anytime!
```

Error cases:
* If module code is empty: `Please provide a module code. Format: checkPrereqStatus <MODULE_CODE>`
* If module not found: `Module not found: INVALID`


---
### Specialisations

#### Viewing list of specialisations: `viewSpecialisations`

Provides a list of available CEG specialisations.

Format: `viewSpecialisations`

Expected output:
```
List of all CEG Specialisations:
1. Others
2. Advanced Electronics
3. Space Technolgy
4. Industry 4.0
5. Internet of Things
6. Robotics
Enter <viewSpecialisationInfo [index]> to know more about a specialisation.
```

#### Viewing specialisation info: `viewSpecialisationInfo`

Provides a description of the specialisation, the elective modules fulfillment criteria and all the core and elective modules that can be taken to fulfill the specialisation. Use `viewSpecialisations` to get the SPECIALISATION_INDEX of the specialisation of interest.

Format: `viewSpecialisationInfo SPECIALISATION_INDEX`

* User can use `viewSpecialisations` to find the available specialisations and their indexes.

Examples:
* `viewSpecialisationInfo 2`
* `viewSpecialisationInfo 5`

Error cases:
* If the index is missing, ClassMate will prompt the user to provide one.
* If the index is not an integer from 1 to 6, ClassMate will display an error message.

#### Setting a specialisation: `setSpecialisation`
Adds a specialisation track to your profile. You can select up to **two** specialisations.
Format:  `setSpecialisation SPECIALISATION_NAME`

Example:
* `setSpecialisation Robotics`

#### Removing a specialisation: `removeSpecialisation`
Removes a selected specialisation from your profile.

Format: `removeSpecialisation SPECIALISATION_NAME`

Example:
* `removeSpecialisation Robotics`

---
### Profile & Progress Tracking

#### Marking a module as done: `markDone`

Marks a module as completed. The module will be saved and remembered the next time you start ClassMate.

Format: `markDone MODULE_CODE`

Examples:
* `markDone CS1010`
* `markDone CS2040C`

### Viewing completed modules: `viewDone`

Shows the list of modules you have marked as done.

Format: `viewDone`

Expected output:
```
Completed modules:
- CS1010
- CS2040C
```

### Checking your academic profile: `checkprofile`

Provides a detailed summary of your progress. It displays completed modules, remaining major requirements, and progress within your selected specialisations.

Format: `checkProfile`

---

## Command Summary

| Category | Action | Format | Examples |
| :--- | :--- | :--- | :--- |
| **General** | **Get Help** | `help` | `help` |
| | **Exit Application** | `bye` | `bye` |
| **Major** | **View Core Requirements** | `viewGradReqs` | `viewGradReqs` |
| | **View Module Details** | `viewModuleInfo MODULE_CODE` | `viewModuleInfo CS2113` |
| | **Check Availability** | `queryModuleAvailability MODULE_CODE sem1/sem2` | `queryModuleAvailability CG2023 sem2` |
| | **View Prerequisite Tree** | `viewPrereqs MODULE_CODE` | `viewPrereqs EE4204` |
| **Spec** | **List All Specialisations** | `viewSpecialisations` | `viewSpecialisations` |
| | **View Specialisation Info** | `viewSpecialisationInfo INDEX` | `viewSpecialisationInfo 2` |
| | **Add Specialisation** | `setSpecialisation NAME` | `setSpecialisation Robotics` |
| | **Remove Specialisation** | `removeSpecialisation NAME` | `removeSpecialisation IoT` |
| **Profile** | **Mark Module as Done** | `markDone MODULE_CODE` | `markDone CS2040C` |
| | **View Completed Modules** | `viewDone` | `viewDone` |
| | **Check Academic Profile** | `checkProfile` | `checkProfile` |
| | **Check Prereq Checklist** | `checkPrereqStatus MODULE_CODE` | `checkPrereqStatus CS2113` |
