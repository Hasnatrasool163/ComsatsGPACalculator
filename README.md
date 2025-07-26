# ComsatsGPACalculator v2.0

A JavaFX-based GPA Calculator designed for COMSATS students to compute semester and cumulative GPAs with a modern, user-friendly interface.

This GPA Calculator allows users to manage semester and course details, calculate GPAs in real-time, and save/load data in JSON format. Built with JavaFX, it offers dynamic course input, lab/theory weight adjustments, and collapsible semester views for efficient GPA tracking.

## Screenshot of Main Interface

[Main Interface]<img width="1006" height="789" alt="main-interface" src="https://github.com/user-attachments/assets/ef54a8c4-5168-49a9-9d12-0ca1a011805e" />

<img width="1920" height="1031" alt="add-course" src="https://github.com/user-attachments/assets/6cbf438b-a14b-43f9-bbb4-5a2399c3fc24" />

<img width="1876" height="615" alt="add-marks" src="https://github.com/user-attachments/assets/452bdd72-3f1a-485a-947e-c73d2ffc6943" />

<img width="1889" height="620" alt="percentage" src="https://github.com/user-attachments/assets/b10138e1-bb2d-4a9d-9754-fd9e076f878e" />


## Features

- **Dynamic Semester and Course Management**:
  - Add/remove semesters and courses interactively.
  - Input course details: name, credit hours, quizzes, assignments, midterm, final, and lab components.
- **Real-Time GPA Calculation**:
  - Live updates for course GPA, percentage, and cumulative GPA as grades are entered.
  - Supports theory/lab weighted scoring with adjustable sliders.
- **Lab Component Support**:
  - Optional lab inclusion with automatic detection based on lab grades.
  - Dynamic lab assignment fields and separate lab midterm/final inputs.
- **Data Persistence**:
  - Save semester data to `student_data.json` and load it seamlessly.
  - No student ID required; supports single-student usage.
- **Collapsible UI**:
  - Accordion-style semesters and titled panes for easy navigation.
- **Error Handling**:
  - Robust validation for grade inputs and file operations with user-friendly alerts.
- **JSON Compatibility**:
  - Supports importing/exporting complex course data, including quizzes, assignments, and lab scores.


## Installation

1. Download the GpaCalculator setup.exe and install it, add a desktop shortcut icon.
   

## Usage

1. Run `MainApp.java` to launch the application.
2. **Add Semesters**:
   - Click "Add Semester" to create a new semester.
3. **Add Courses**:
   - Expand a semester and click "Add Course" to add a course (defaults to "Unnamed Course").
   - Enter course name, credit hours, quiz/assignment grades, midterm, final scores, and lab details (if applicable).
   - Adjust the theory/lab weight using the slider if the course has lab components.
4. **Real-Time GPA Tracking**:
   - Watch course GPA and percentage update live as grades are entered.
   - Cumulative GPA updates automatically at the top-level.
5. **Save/Load Data**:
   - Click "Save Data" to save to `student_data.json`.
   - Click "Load Data" to reload saved semesters and courses.
6. **Calculate GPA**:
   - Click "Calculate GPA" to refresh the cumulative CGPA display (optional, as updates are automatic).
7. **Example JSON Data**:
   - Load a sample course like "Computer Networks" (grade ~73.44%, GPA 3.0) from `student_data.json` to see populated fields.


## Changelog
- **v2.0.4** (July 2025):
  - Switched to JavaFX from Swing/AWT for a modern UI.
  - Added real-time GPA calculation and live UI updates.
  - Implemented JSON-based data persistence with `StorageManager`.
  - Added support for lab components with dynamic fields and weight sliders.
  - Fixed `IndexOutOfBoundsException` in `CourseView` during data loading.
  - Fixed `NullPointerException` when adding new courses.
  - Enhanced error handling and input validation.
- **v1.0**:
  - Initial Swing/AWT version with basic GPA calculation and text file output.

Thank you for using ComsatsGPACalculator v2.0!
