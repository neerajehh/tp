# Tran Viet Khoa's Project Portfolio Page

## Overview
ClassMate is an academic chatbot that helps NUS CEG students plan their modules and academic pathway. It allows users to track completed modules, explore prerequisites, and manage their specialization progress through a CLI interface.

---

## Summary of Contributions

### **View Module Prerequisites**

**What it does:**  
Allows users to view the full prerequisite tree of a given module in a structured, hierarchical format.

**Why it is useful:**  
Students often struggle to understand prerequisite chains across multiple modules. This feature provides a clear visualization of dependencies, helping users plan their academic journey more effectively.

**Implementation details:**
- Parses module prerequisite data into a tree structure  
- Recursively traverses prerequisites to build the hierarchy  
- Outputs the structure using indentation and tree symbols (`└──`) for readability  
- Handles edge cases such as:
  - Modules with no prerequisites  
  - Invalid module codes  

**Example usage:**
Expected output:
```
EE4204  
└── CS2113
    └── CS2040C
        └── CS1010
```

* **Set Specialization**
**What it does:**  
Allows users to select and set their desired CEG specialization.

**Why it is useful:**  
Choosing a specialization affects which modules a student should take. This feature personalizes the user’s academic planning experience.

**Implementation details:**
- Accepts a specialization index as input  
- Maps the index to a predefined list of specializations  
- Stores the selected specialization in the user profile  
- Ensures persistence so the selection is retained across sessions  
- Validates input to prevent invalid indices  

**Example usage:**
setSpecialization Robotics

* **Check User Profile**
Displays the user’s current academic profile, including selected specialisation and completed modules.

**Format:**  
`checkprofile`

**Expected Output:**  
```
Your Academic Profile:
______________________________
Specialization: ROBOTICS
Completed Modules:
  [DONE] CS2040C
  [DONE] CS2113
______________________________
Keep up the great work!
```