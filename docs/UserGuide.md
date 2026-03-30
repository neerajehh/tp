# User Guide

ClassMate is a CLI-based chatbot that assists NUS UG CEG students with timetable and course schedule planning, so as to tailor to their academic pursuits and scheduling needs, while tracking progress toward graduation, specialisation, minor, and other degree requirements.

* [Quick Start](#quick-start)
* [Features](#features)
  * [Viewing help: `help`](#Help on application usage: `help`)
  * [Viewing CEG graduation requirements: `viewGradReqs`](#viewing-major-requirements-viewgradreqs)
  * [Viewing module info: `viewModuleInfo`](#viewing-module-info-viewmoduleinfo)
  * [Checking module availability: `queryModuleAvailability`](#checking-module-availability-querymoduleavailability)
  * [Viewing prerequisites: `viewPrereqs`](#viewing-prerequisites-viewprereqs)
  * [Marking a module as done: `markDone`](#marking-a-module-as-done-markdone)
  * [Viewing completed modules: `viewDone`](#viewing-completed-modules-viewdone)
  * [Viewing list of specialisations: `viewSpecialisations`](#viewing-list-of-specialisations-viewspecialisations)
  * [Viewing specialisation info: `viewSpecialisationInfo`](#viewing-specialisation-info-viewspecialisationinfo)
* [Command Summary](#command-summary)

## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `ClassMate.jar` from [here](https://github.com/AY2526S2-CS2113-W11-4/tp/releases).
3. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar ClassMate.jar` command to run the application.

## Features

<div markdown="block" class="alert alert-info">

### Command Format

* Words in `UPPER_CASE` are the parameters to be supplied by the user.
* Parameters can be in any order.

</div>

### Help on application usage: `help`

Shows a list of valid commands and their purpose in point form

Format: `help`

Examples:
* `help`

Expected output:
``` 
Available commands:
- help: Viewing help
- viewGradReqs: Print CEG graduation requirements
... Other commands
```

### Viewing major requirements: `viewGradReqs`

Shows a list of modules the student has to clear in order to graduate.

Format: `viewGradReqs`

Examples:
* `viewGradReqs`

### Viewing module info: `viewModuleInfo`

Shows details for a specific module, such as title, academic units, prerequisites, and whether the module can be taken.

Format: `viewModuleInfo MODULE_CODE`

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

### Marking a module as done: `markDone`

Marks a module as completed. The module will be saved and remembered the next time you start ClassMate.

Format: `markDone MODULE_CODE`

Examples:
* `markDone CS1010`
* `markDone CS2040C`

Expected output:
```
Successfully marked CS1010 as done!
```

### Viewing completed modules: `viewDone`

Shows the list of modules you have marked as done.

Format: `viewDone`

Expected output:
```
Completed modules:
- CS1010
- CS2040C
```

### Viewing list of specialisations: `viewSpecialisations`

Provides a list of available CEG specialisations.

Format: `viewSpecialisations`

Expected output:
```
List of all CEG Specialisations:
1. Advanced Electronics
2. Space Technolgy
3. Industry 4.0
4. Internet of Things
5. Robotics
Enter <viewSpecialisationInfo [index]> to know more about a specialisation.
```

### Viewing specialisation info: `viewSpecialisationInfo`

Provides a description of the specialisation, the elective modules fulfillment criteria and all the core and elective modules that can be taken to fulfill the specialisation. Use `viewSpecialisations` to get the SPECIALISATION_INDEX of the specialisation of interest.

Format: `viewSpecialisationInfo SPECIALISATION_INDEX`

Examples:
* `viewSpecialisationInfo 2`
* `viewSpecialisationInfo 5`

---

## Command Summary

| Action | Format | Examples |
| :--- | :--- | :--- |
| **Major Reqs** | `viewGradReqs` | `viewGradReqs` |
| **Module Info** | `viewModuleInfo MODULE_CODE` | `viewModuleInfo CS2113` |
| **Availability** | `queryModuleAvailability MODULE_CODE sem1/sem2` | `queryModuleAvailability CG2023 sem2` |
| **Prerequisites** | `viewPrereqs MODULE_CODE` | `viewPrereqs CG2028` |
| **Mark Done** | `markDone MODULE_CODE` | `markDone CS1010` |
| **View Done** | `viewDone` | `viewDone` |
| **Specialisation List** | `viewSpecialisations` | `viewSpecialisations` |
| **Specialisation Info** | `viewSpecialisationInfo SPECIALISATION_INDEX` | `viewSpecialisationInfo 2` |