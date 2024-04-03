
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GPACalculator extends JFrame {
    private JPanel mainPanel,headingpanel,buttonPanel;
    private JTextField nameField, semesterField;
    private JComboBox<Integer> totalCoursesComboBox;
    private JButton calculateButton;

    private JLabel headinglbl;
    private ArrayList<JTextField>courseNameFields;
    private ArrayList<JComboBox<String>> gradeComboBoxes;
    private ArrayList<JComboBox<Integer>> creditHoursComboBoxes;
    private JPanel coursesPanel;
    private static final String[] GRADES =       {"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D", "F"};
    private static final Double[] GRADE_POINTS = {4.0, 3.66, 3.33, 3.0, 2.66, 2.33, 2.0, 1.66, 1.0, 0.0};

    public GPACalculator() {
        setTitle("GPA Calculator");
        setSize(500, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(Color.LIGHT_GRAY);
        getContentPane().setBackground(Color.GREEN);
        nameField = new JTextField(10);
        semesterField = new JTextField(10);
        topPanel.add(new JLabel("Name:"));
        topPanel.add(nameField);
        topPanel.add(new JLabel("Semester:"));
        topPanel.add(semesterField);

        JPanel courseSelectionPanel = new JPanel(new FlowLayout());
        courseSelectionPanel.setBackground(Color.LIGHT_GRAY);
        totalCoursesComboBox = new JComboBox<>();
        for (int i = 1; i <= 10; i++) {
            totalCoursesComboBox.addItem(i);
        }
        totalCoursesComboBox.setSelectedIndex(4); // Default to 5 courses
        totalCoursesComboBox.addActionListener(e -> updateCoursesPanel((int) totalCoursesComboBox.getSelectedItem()));
        courseSelectionPanel.add(new JLabel("Total Courses:"));
        courseSelectionPanel.add(totalCoursesComboBox);

        coursesPanel = new JPanel();
        coursesPanel.setLayout(new BoxLayout(coursesPanel, BoxLayout.Y_AXIS));
        coursesPanel.setBackground(Color.darkGray);
        updateCoursesPanel(5); // Initialize with 5 courses

        headinglbl = new JLabel("Welcome to GPA Calculator");
        headinglbl.setBackground(Color.GRAY);
        headinglbl.setForeground(Color.green);
        headinglbl.setFont(new Font("consolas",Font.BOLD,20));
        calculateButton = new JButton("Calculate GPA");
        calculateButton.setBackground(Color.GREEN);
        calculateButton.setHorizontalAlignment(SwingConstants.CENTER);
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateGPA();
            }
        });

        headingpanel = new JPanel();
        headingpanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));
        headingpanel.add(headinglbl);
        mainPanel.add(headingpanel);
        buttonPanel= new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));
        Dimension buttonSize = new Dimension(150,40);
        calculateButton.setPreferredSize(buttonSize);
        calculateButton.setMaximumSize(buttonSize);
        calculateButton.setMinimumSize(buttonSize);
        buttonPanel.add(calculateButton);
        mainPanel.add(topPanel);
        mainPanel.add(courseSelectionPanel);
        mainPanel.add(new JScrollPane(coursesPanel));
        mainPanel.add(buttonPanel);
//        mainPanel.add(calculateButton);
        add(mainPanel);
    }

    private void updateCoursesPanel(int totalCourses) {
        coursesPanel.removeAll();
        gradeComboBoxes = new ArrayList<>();
        creditHoursComboBoxes = new ArrayList<>();
        courseNameFields = new ArrayList<>(); 

        for (int i = 0; i < totalCourses; i++) {
            JPanel coursePanel = new JPanel(new FlowLayout());
            coursePanel.setBackground(Color.gray);
            JTextField courseNameField = new JTextField(10);
            JComboBox<Integer> creditHoursComboBox = new JComboBox<>();
            for (int j = 1; j <= 5; j++) {
                creditHoursComboBox.addItem(j);
            }
            JComboBox<String> gradeComboBox = new JComboBox<>(GRADES);

            creditHoursComboBoxes.add(creditHoursComboBox);
            gradeComboBoxes.add(gradeComboBox);
            courseNameFields.add(courseNameField); // Add to the list

            coursePanel.add(new JLabel("Course " + (i + 1) + ":"));
            coursePanel.add(courseNameField);
            coursePanel.add(new JLabel("Credits:"));
            coursePanel.add(creditHoursComboBox);
            coursePanel.add(new JLabel("Grade:"));
            coursePanel.add(gradeComboBox);

            coursesPanel.add(coursePanel);
        }

        coursesPanel.revalidate();
        coursesPanel.repaint();
    }


    private void calculateGPA() {
        double totalPoints = 0;
        int totalCredits = 0;
        StringBuilder details = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i < gradeComboBoxes.size(); i++) {
            String name = (String) courseNameFields.get(i).getText();
            int credits = (int) creditHoursComboBoxes.get(i).getSelectedItem();
            String grade = (String) gradeComboBoxes.get(i).getSelectedItem();
            double gradePoint = GRADE_POINTS[findGradeIndex(grade)];
            totalPoints += gradePoint * credits;
            totalCredits += credits;
            details.append(String.format("Course %s: Grade=%s, Credits=%d%n",name , grade, credits));
        }

        double gpa = totalPoints / totalCredits;


        JOptionPane.showMessageDialog(this, "GPA: " + String.format("%.2f", gpa));


        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save GPA Calculation");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            // Ensure the file has a .txt extension
            if (!fileToSave.getAbsolutePath().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
            }

            try (FileWriter writer = new FileWriter(fileToSave)) {
                writer.write("GPA Calculation Result\n");
                writer.write("Date: " + dateFormat.format(new Date()) + "\n");
                writer.write("Student Name: " + nameField.getText() + "\n");
                writer.write("Semester: " + semesterField.getText() + "\n\n");
                writer.write(details.toString());
                writer.write("\nFinal GPA: " + String.format("%.2f", gpa));
                JOptionPane.showMessageDialog(this, "Data saved to file: " + fileToSave.getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving the file: " + ex.getMessage());
            }
        }
    }
    private int findGradeIndex(String grade) {
        for (int i = 0; i < GRADES.length; i++) {
            if (GRADES[i].equals(grade)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GPACalculator().setVisible(true);
            }
        });
    }
}

