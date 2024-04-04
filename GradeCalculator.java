//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//
//public class GradeCalculator extends JFrame {
//    private JComboBox<String> courseTypeComboBox;
//    private JTextField[] quizFields = new JTextField[4];
//    private JTextField[] assignmentFields = new JTextField[4];
//    private JTextField[] labAssignmentFields = new JTextField[4];
//    private JTextField midField, finalField, labMidField, labFinalField;
//    private JTextField totalQuizMarksField, totalAssignmentMarksField, totalMidMarksField, totalFinalMarksField;
//    private JButton calculateButton;
//    private JLabel gradeLabel;
//
//    public GradeCalculator() {
//        setTitle("Advanced Grade Calculator");
//        setSize(600, 550);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        initializeUI();
//    }
//
//    private void initializeUI() {
//        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
//
//        panel.add(new JLabel("Course Type:"));
//        courseTypeComboBox = new JComboBox<>(new String[]{"Theory", "Theory+Lab"});
//        courseTypeComboBox.addActionListener(e -> updateFieldVisibility());
//        panel.add(courseTypeComboBox);
//
//        panel.add(new JLabel("Total Quiz Marks (Default 15):"));
//        totalQuizMarksField = new JTextField("15");
//        panel.add(totalQuizMarksField);
//
//        panel.add(new JLabel("Total Assignment Marks (Default 10):"));
//        totalAssignmentMarksField = new JTextField("10");
//        panel.add(totalAssignmentMarksField);
//
//        for (int i = 0; i < 4; i++) {
//            panel.add(new JLabel("Quiz " + (i + 1) + " Marks:"));
//            quizFields[i] = new JTextField();
//            panel.add(quizFields[i]);
//
//            panel.add(new JLabel("Assignment " + (i + 1) + " Marks:"));
//            assignmentFields[i] = new JTextField();
//            panel.add(assignmentFields[i]);
//
//            panel.add(new JLabel("Lab Assignment " + (i + 1) + " Marks:"));
//            labAssignmentFields[i] = new JTextField();
//            panel.add(labAssignmentFields[i]);
//        }
//
//        panel.add(new JLabel("Total Mid Marks (Default 25):"));
//        totalMidMarksField = new JTextField("25");
//        panel.add(totalMidMarksField);
//
//        panel.add(new JLabel("Mid Marks:"));
//        midField = new JTextField();
//        panel.add(midField);
//
//        panel.add(new JLabel("Total Final Marks (Default 50):"));
//        totalFinalMarksField = new JTextField("50");
//        panel.add(totalFinalMarksField);
//
//        panel.add(new JLabel("Final Marks:"));
//        finalField = new JTextField();
//        panel.add(finalField);
//
//        // Lab-specific fields
//        panel.add(new JLabel("Lab Mid Marks (Default 25):"));
//        labMidField = new JTextField("25");
//        panel.add(labMidField);
//
//        panel.add(new JLabel("Lab Final Marks (Default 50):"));
//        labFinalField = new JTextField("50");
//        panel.add(labFinalField);
//
//        calculateButton = new JButton("Calculate Grade");
//        calculateButton.addActionListener(this::calculateGrade);
//        panel.add(calculateButton);
//
//        gradeLabel = new JLabel("Grade: ");
//        panel.add(gradeLabel);
//
//        add(new JScrollPane(panel), BorderLayout.CENTER);
//
//        updateFieldVisibility(); // Initial visibility setup
//        setVisible(true);
//    }
//
//    private void updateFieldVisibility() {
//        boolean isLab = courseTypeComboBox.getSelectedItem().equals("Theory+Lab");
//        for (JTextField labAssignmentField : labAssignmentFields) {
//            labAssignmentField.setVisible(isLab);
//        }
//        labMidField.setVisible(isLab);
//        labFinalField.setVisible(isLab);
//        this.pack();
//    }
//
//    private void calculateGrade(ActionEvent e) {
//        try {
//            int totalQuizMarks = Integer.parseInt(totalQuizMarksField.getText());
//            int totalAssignmentMarks = Integer.parseInt(totalAssignmentMarksField.getText());
//            int totalMidMarks = Integer.parseInt(totalMidMarksField.getText());
//            int totalFinalMarks = Integer.parseInt(totalFinalMarksField.getText());
//
//            double quizSum = 0, assignmentSum = 0, labAssignmentSum = 0;
//            for (int i = 0; i < 4; i++) {
//                quizSum += Double.parseDouble(quizFields[i].getText()) / totalQuizMarks * 100;
//                assignmentSum += Double.parseDouble(assignmentFields[i].getText()) / totalAssignmentMarks * 100;
//                if (courseTypeComboBox.getSelectedItem().equals("Theory+Lab")) {
//                    labAssignmentSum += Double.parseDouble(labAssignmentFields[i].getText()); // Assuming 100 is total for each lab assignment
//                }
//            }
//            double mid = Double.parseDouble(midField.getText()) / totalMidMarks * 100;
//            double finalExam = Double.parseDouble(finalField.getText()) / totalFinalMarks * 100;
//            double labMid = Double.parseDouble(labMidField.getText());
//            double labFinal = Double.parseDouble(labFinalField.getText());
//
//            double totalMarks;
//            if (courseTypeComboBox.getSelectedItem().equals("Theory")) {
//                totalMarks = (quizSum / 4 * 0.15) + (assignmentSum / 4 * 0.10) + (mid * 0.25) + (finalExam * 0.50);
//            } else { // Theory+Lab
//                double theoryMarks = (quizSum / 4 * 0.15) + (assignmentSum / 4 * 0.10) + (mid * 0.25) + (finalExam * 0.50);
//                double labAssignmentAverage = (labAssignmentSum / 4) * 0.25; // This assumes 100 total for lab assignments and then takes 25% of the lab component.
//                double labMidNormalized = (labMid / 100) * 0.25; // Normalize lab mid to 25% of the lab component.
//                double labFinalNormalized = (labFinal / 100) * 0.50; // Normalize lab final to 50% of the lab component.
//                double labMarks = (labAssignmentAverage + labMidNormalized + labFinalNormalized) * 33; // Apply the 33% overall weighting to the lab component.
//                totalMarks = (theoryMarks * 0.67) + labMarks;
//            }
//
//            double gradePoint = calculateGradePoint(totalMarks);
//            gradeLabel.setText("Grade: " + gradePointToString(gradePoint));
//        } catch (NumberFormatException nfe) {
//            JOptionPane.showMessageDialog(this, "Please enter valid numbers");
//        }
//    }
//
//    private double calculateGradePoint(double marks) {
//        if (marks >= 85) return 4.0;
//        else if (marks >= 80) return 3.66;
//        else if (marks >= 75) return 3.33;
//        else if (marks >= 70) return 3.0;
//        else if (marks >= 65) return 2.66;
//        else if (marks >= 60) return 2.0;
//        else if (marks >= 55) return 1.5;
//        else return 0;
//    }
//
//    private String gradePointToString(double gradePoint) {
//        if (gradePoint == 4.0) return "A";
//        else if (gradePoint == 3.66) return "A-";
//        else if (gradePoint == 3.33) return "B+";
//        else if (gradePoint == 3.0) return "B";
//        else if (gradePoint == 2.66) return "B-";
//        else if (gradePoint == 2.0) return "C";
//        else if (gradePoint == 1.5) return "D";
//        else return "F";
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(GradeCalculator::new);
//    }
//}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GradeCalculator extends JFrame {
    private JComboBox<String> courseTypeComboBox;
    private JTextField[] quizFields = new JTextField[4];
    private JTextField[] assignmentFields = new JTextField[4];
    private JTextField[] labAssignmentFields = new JTextField[4];
    private JTextField midField, finalField, labMidField, labFinalField;
    private JTextField totalQuizMarksField, totalAssignmentMarksField, totalMidMarksField, totalFinalMarksField;
    private JButton calculateButton;
    private JLabel gradeLabel;

    public GradeCalculator() {
        setTitle("Advanced Grade Calculator");
        setSize(600, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initializeUI();
    }

    private void initializeUI() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        panel.add(new JLabel("Course Type:"));
        courseTypeComboBox = new JComboBox<>(new String[]{"Theory", "Theory+Lab"});
        courseTypeComboBox.addActionListener(e -> updateFieldVisibility());
        panel.add(courseTypeComboBox);

        panel.add(new JLabel("Total Quiz Marks (Default 15):"));
        totalQuizMarksField = new JTextField("15");
        panel.add(totalQuizMarksField);

        panel.add(new JLabel("Total Assignment Marks (Default 10):"));
        totalAssignmentMarksField = new JTextField("10");
        panel.add(totalAssignmentMarksField);

        for (int i = 0; i < 4; i++) {
            panel.add(new JLabel("Quiz " + (i + 1) + " Marks:"));
            quizFields[i] = new JTextField();
            panel.add(quizFields[i]);

            panel.add(new JLabel("Assignment " + (i + 1) + " Marks:"));
            assignmentFields[i] = new JTextField();
            panel.add(assignmentFields[i]);

            panel.add(new JLabel("Lab Assignment " + (i + 1) + " Marks:"));
            labAssignmentFields[i] = new JTextField();
            panel.add(labAssignmentFields[i]);
        }

        panel.add(new JLabel("Total Mid Marks (Default 25):"));
        totalMidMarksField = new JTextField("25");
        panel.add(totalMidMarksField);

        panel.add(new JLabel("Mid Marks:"));
        midField = new JTextField();
        panel.add(midField);

        panel.add(new JLabel("Total Final Marks (Default 50):"));
        totalFinalMarksField = new JTextField("50");
        panel.add(totalFinalMarksField);

        panel.add(new JLabel("Final Marks:"));
        finalField = new JTextField();
        panel.add(finalField);

        // Lab-specific fields
        panel.add(new JLabel("Lab Mid Marks:"));
        labMidField = new JTextField();
        panel.add(labMidField);

        panel.add(new JLabel("Lab Final Marks:"));
        labFinalField = new JTextField();
        panel.add(labFinalField);

        calculateButton = new JButton("Calculate Grade");
        calculateButton.addActionListener(this::calculateGrade);
        panel.add(calculateButton);

        gradeLabel = new JLabel("Grade: ");
        panel.add(gradeLabel);

        add(new JScrollPane(panel), BorderLayout.CENTER);

        updateFieldVisibility(); // Initial visibility setup
        setVisible(true);
    }

    private void updateFieldVisibility() {
        boolean isLab = courseTypeComboBox.getSelectedItem().equals("Theory+Lab");
        for (JTextField labAssignmentField : labAssignmentFields) {
            labAssignmentField.setVisible(isLab);
        }
        labMidField.setVisible(isLab);
        labFinalField.setVisible(isLab);
        this.pack();
    }


    private void calculateGrade(ActionEvent e) {
        try {
            int totalQuizMarks = Integer.parseInt(totalQuizMarksField.getText());
            int totalAssignmentMarks = Integer.parseInt(totalAssignmentMarksField.getText());
            int totalMidMarks = Integer.parseInt(totalMidMarksField.getText());
            int totalFinalMarks = Integer.parseInt(totalFinalMarksField.getText());

            double quizSum = 0, assignmentSum = 0, labAssignmentSum = 0;
            for (int i = 0; i < 4; i++) {
                quizSum += Double.parseDouble(quizFields[i].getText()) / totalQuizMarks * 100;
                assignmentSum += Double.parseDouble(assignmentFields[i].getText()) / totalAssignmentMarks * 100;
                if (courseTypeComboBox.getSelectedItem().equals("Theory+Lab")) {
                    labAssignmentSum += Double.parseDouble(labAssignmentFields[i].getText()) / 10 * 100; // Normalize each lab assignment mark out of 10 to a percentage
                }
            }
            double mid = Double.parseDouble(midField.getText()) / totalMidMarks * 100;
            double finalExam = Double.parseDouble(finalField.getText()) / totalFinalMarks * 100;
            double labMid = 0, labFinal = 0; // Initialize labMid and labFinal
            if (courseTypeComboBox.getSelectedItem().equals("Theory+Lab")) {
                labMid = Double.parseDouble(labMidField.getText()) / 25 * 100; // Normalize lab mid out of 25 to a percentage, if applicable
                labFinal = Double.parseDouble(labFinalField.getText()) / 50 * 100; // Normalize lab final out of 50 to a percentage, if applicable
            }

            double totalMarks;
            if (courseTypeComboBox.getSelectedItem().equals("Theory")) {
                totalMarks = (quizSum / 4 * 0.15) + (assignmentSum / 4 * 0.10) + (mid * 0.25) + (finalExam * 0.50);
            } else { // Theory+Lab
                double theoryMarks = (quizSum / 4 * 0.15) + (assignmentSum / 4 * 0.10) + (mid * 0.25) + (finalExam * 0.50);
                double labAssignmentAverage = (labAssignmentSum / 4); // Average of lab assignments already converted to percentages
                double labComponent = (labAssignmentAverage * 0.25) + (labMid * 0.25) + (labFinal * 0.50); // Calculate total lab component score
                totalMarks = theoryMarks * 0.67 + labComponent * 0.33; // Adjusted total incorporating theory and lab components
            }

            double gradePoint = calculateGradePoint(totalMarks);
            gradeLabel.setText("Grade: " + gradePointToString(gradePoint));
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for all required fields.");
        }
    }

    private double calculateGradePoint(double marks) {
        if (marks >= 85) return 4.0;
        else if (marks >= 80) return 3.66;
        else if (marks >= 75) return 3.33;
        else if (marks >= 70) return 3.0;
        else if (marks >= 65) return 2.66;
        else if (marks >= 60) return 2.0;
        else if (marks >= 55) return 1.5;
        else return 0;
    }

    private String gradePointToString(double gradePoint) {
        if (gradePoint == 4.0) return "A";
        else if (gradePoint == 3.66) return "A-";
        else if (gradePoint == 3.33) return "B+";
        else if (gradePoint == 3.0) return "B";
        else if (gradePoint == 2.66) return "B-";
        else if (gradePoint == 2.0) return "C";
        else if (gradePoint == 1.5) return "D";
        else return "F";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GradeCalculator::new);
    }
}


