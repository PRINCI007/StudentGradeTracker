import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GradeTrackerGUI {
    private ArrayList<Student> studentList = new ArrayList<>();
    private JFrame frame;
    private JTextField nameField, gradeField;
    private JTextArea displayArea;

    public GradeTrackerGUI() {
        // 1. Setup the Window
        frame = new JFrame("Student Grade Tracker");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // 2. Input Panel (Top)
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.add(new JLabel(" Student Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        
        inputPanel.add(new JLabel(" Student Grade:"));
        gradeField = new JTextField();
        inputPanel.add(gradeField);

        JButton addButton = new JButton("Add Student");
        inputPanel.add(addButton);
        
        JButton reportButton = new JButton("Show Report");
        inputPanel.add(reportButton);

        // 3. Display Area (Center)
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // 4. Button Logic
        addButton.addActionListener(e -> addStudent());
        reportButton.addActionListener(e -> displayReport());

        // Add components to frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void addStudent() {
        try {
            String name = nameField.getText();
            double grade = Double.parseDouble(gradeField.getText());
            
            if (name.isEmpty()) throw new Exception("Name cannot be empty");
            
            studentList.add(new Student(name, grade));
            nameField.setText("");
            gradeField.setText("");
            JOptionPane.showMessageDialog(frame, "Student Added!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Invalid Input! Enter a valid name and number.");
        }
    }

    private void displayReport() {
        if (studentList.isEmpty()) {
            displayArea.setText("No students added yet.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        double total = 0, highest = -1, lowest = 101;

        sb.append(String.format("%-15s | %-10s\n", "NAME", "GRADE"));
        sb.append("-----------------------------\n");

        for (Student s : studentList) {
            sb.append(String.format("%-15s | %-10.2f\n", s.name, s.grade));
            total += s.grade;
            if (s.grade > highest) highest = s.grade;
            if (s.grade < lowest) lowest = s.grade;
        }

        sb.append("\n--- Summary ---\n");
        sb.append("Average: ").append(String.format("%.2f", total / studentList.size())).append("\n");
        sb.append("Highest: ").append(highest).append("\n");
        sb.append("Lowest: ").append(lowest);

        displayArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        new GradeTrackerGUI();
    }
}