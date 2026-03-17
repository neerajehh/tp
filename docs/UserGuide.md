# User Guide

ClassMate is a CLI-based chatbot that assists NUS UG CEG students with timetable and course schedule planning, so as to tailor to their academic pursuits and scheduling needs, while tracking progress toward graduation, specialisation, minor, and other degree requirements.

* [Quick Start](#quick-start)
* [Features](#features)
  * [Viewing CEG graduation requirements: `viewGradReqs`](#viewing-major-requirements-viewgradreqs)
  * [Viewing module info: `printModuleInfo`](#printing-module-info-printmoduleinfo)
  * [Viewing prerequisites: `viewPrereqs`](#viewing-prerequisites-viewprereqs)
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

### Viewing major requirements: `viewGradReqs`

Shows a list of modules the student has to clear in order to graduate.

Format: `viewGradReqs`

Examples:
* `viewGradReqs`

### Printing module info: `printModuleInfo`

Shows details for specific modules, such as title, academic units, prerequisites, and whether the module can be taken.

Format: `printModuleInfo MODULE_CODE [MORE_MODULE_CODES]...`

Examples:
* `printModuleInfo CS2113`
* `printModuleInfo CS2113 CG2028 CS2040C`

Expected output:
```
Module Info for CS2113
Code: CS2113 Name: Software Engineering & Object-Oriented Programming Units: 4 Semester: 1/2 Prerequisites: CS2040C Can take: NO
```

### Viewing prerequisites: `viewPrereqs`

Given a module code, view the prerequisite tree.

Format: `viewPrereqs MODULE_CODE`

Examples:
* `viewPrereqs CG2028`
* `viewPrereqs CS2113`

### Viewing list of specialisations: `viewSpecialisations`

Provides a list of available CEG specialisations.

Format: `viewSpecialisations`

### Viewing specialisation info: `viewSpecialisationInfo`

Provides an overview of the specialisation and the list of modules to take in order to fulfill said specialisation. Use `viewSpecialisations` to check the SPECIALISATION_INDEX.

Format: `viewSpecialisationInfo SPECIALISATION_INDEX`

Examples:
* `viewSpecialisationInfo 2`
* `viewSpecialisationInfo 5`

---

## Command Summary

| Action | Format | Examples |
| :--- | :--- | :--- |
| **Major Reqs** | `viewGradReqs`
| **Module Info** | `printModuleInfo MODULE_CODE...` | `printModuleInfo CS2113 CG2028` |
| **Prerequisites** | `viewPrereqs MODULE_CODE` | `viewPrereqs CG2028` |
| **Specialisation List** | `viewSpecialisations` 
| **Specialisation Info** | `viewSpecialisationInfo SPECIALISATION_INDEX` | `viewSpecialisationInfo 2` |
