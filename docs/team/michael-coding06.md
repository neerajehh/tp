# Tran Viet Khoa's Project Portfolio Page

## Overview
ClassMate is an academic chatbot that helps NUS CEG students plan their modules and academic pathway. It allows users to track completed modules, explore prerequisites, and manage their specialisation progress through a CLI interface.

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

### **Set Specialisation**
**What it does:**  
Allows users to select and set their desired CEG specialisation.

**Why it is useful:**  
Choosing a specialisation affects which modules a student should take. This feature personalizes the user’s academic planning experience.

**Implementation details:**
- Accepts a specialisation index as input  
- Maps the index to a predefined list of specialisations  
- Stores the selected specialisation in the user profile  
- Ensures persistence so the selection is retained across sessions  
- Validates input to prevent invalid indices  

**Example usage:**
setSpecialisation Robotics

* **Check User Profile**
Displays the user’s current academic profile, including selected specialisation and completed modules.

**Format:**  
`checkprofile`

**Expected Output:**  
```
Your Academic Profile:
______________________________
Specialisation: ROBOTICS
Completed Modules:
  [DONE] CS2040C
  [DONE] CS2113
______________________________
Keep up the great work!
```

### **Find Module by Code**
**What it does:**
Allows the system to quickly retrieve a specific module using its module code.

**Why it is useful**
Many features (such as prerequisite viewing and validation) require direct access to a module. This function provides a simple and efficient way to locate modules within the major requirements.

**Implementation details:**
- Iterates through the list of core modules
- Matches module codes using string comparison
- Returns the corresponding Module object if found
- Returns null if the module does not exist
