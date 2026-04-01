---
layout: page
title: Neeraj's Project Portfolio Page
---

### Project: ClassMate

ClassMate is a desktop application for CEG students to plan their modules and track their academic progress. The user interacts with it using a CLI.

Given below are my contributions to the project.

* **New Feature**: `viewModuleInfo`
  * What it does: Displays detailed information about a specified CEG module including prerequisites, units, and semester availability.
  * Justification: Students need a quick way to look up module details without referring to external sources.

* **New Feature**: `queryModuleAvailability`
  * What it does: Checks if a module is available in a given semester (sem1 or sem2).
  * Justification: Helps students plan which semester to take a module.

* **New Feature**: `checkPrereqStatus`
  * What it does: Shows which prerequisites for a module the user has completed and which are still outstanding.
  * Justification: Students need to know if they are eligible to take a module based on their completed modules.

* **New Feature**: `markDone` and `viewDone`
  * What it does: Allows users to mark modules as completed and view their completed modules list, which is saved across sessions.
  * Justification: Students need to track their academic progress across multiple sessions.

* **New Feature**: `Storage`
  * What it does: Handles persistent saving and loading of completed modules to and from a text file, so progress is retained between sessions.
  * Justification: Without persistence, students would have to re-enter their completed modules every time they launch the app.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=neerajehh)

* **Contributions to the UG**:
  * Added documentation for `viewModuleInfo`, `queryModuleAvailability`, `checkPrereqStatus`, `markDone`, and `viewDone` commands including format, examples, and expected output.

* **Contributions to the DG**:
  * Added UML sequence diagram for `checkPrereqStatus` feature illustrating how components interact when the command is executed.
  * Added implementation walkthrough and design considerations for `checkPrereqStatus`, `markDone`, `viewDone`, and `Storage`.
  * Design considerations include: use of `ArrayList` vs `HashSet` for `completedModules`, file path handling in `Storage`, and separation of concerns between commands and storage.

* **Contributions to team-based tasks**:
  * Implemented `Storage` class for persistent data saving and loading.
  * Fixed checkstyle errors including duplicate imports.
  * Added `@@author` tags to ensure correct RepoSense attribution.
  * Reviewed and merged PRs from teammates.

* **Review/mentoring contributions**:
  * Reviewed PRs from teammates and provided feedback on code quality and correctness.
