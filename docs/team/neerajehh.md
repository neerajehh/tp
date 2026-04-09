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

* **New Feature**: `markDone`
  * What it does: Allows users to mark modules as completed, which is saved persistently across sessions.
  * Justification: Students need to track their academic progress across multiple sessions.

* **New Feature**: `viewDone`
  * What it does: Displays the list of all modules the user has marked as completed.
  * Justification: Students need a quick overview of what they have already completed to plan their remaining workload.

* **New Feature**: `Storage`
  * What it does: Handles persistent saving and loading of completed modules and specialisations to and from text files, so progress is retained between sessions.
  * Justification: Without persistence, students would have to re-enter their completed modules every time they launch the app.

* **Bug fixes**:
  * Fixed `checkPrereqStatus` error message to match UG format — changed from `"Module X not found"` to `"Module not found: X"`.
  * Fixed `queryModuleAvailability` invalid semester input to use proper error formatting with `(!) ERROR:` prefix, consistent with all other error messages in the app.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=neerajehh)

* **Contributions to the UG**:
  * Added documentation for `viewModuleInfo`, `queryModuleAvailability`, `checkPrereqStatus`, `markDone`, and `viewDone` commands including format, examples, expected output, and error cases.

* **Contributions to the DG**:
  * Added UML sequence diagram for `checkPrereqStatus` feature illustrating how components interact when the command is executed.
  * Added UML sequence diagram for `markDone` feature (v2.1) illustrating the full flow from user input to storage persistence.
  * Wrote the Storage component design section including file management responsibilities, startup and update data flow sequences, and design considerations (exception propagation, encapsulation of file paths, overwrite-on-save strategy).
  * Added implementation walkthrough and design considerations for `checkPrereqStatus`, `markDone`, `viewDone`, and `Storage`.

* **Contributions to team-based tasks**:
  * Implemented `Storage` class for persistent data saving and loading.
  * Fixed checkstyle errors including duplicate imports.
  * Added `@@author` tags to ensure correct RepoSense attribution.
  * Reviewed and merged PRs from teammates.

* **Review/mentoring contributions**:
  * Reviewed PRs from teammates and provided feedback on code quality and correctness.
