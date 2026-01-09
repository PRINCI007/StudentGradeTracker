import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    double grade;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class GradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> studentList = new ArrayList<>();

        System.out.println("--- Student Grade Management System ---");

        while (true) {
            System.out.print("Enter Student Name (or 'done' to finish): ");
            String name = scanner.next();
            if (name.equalsIgnoreCase("done")) break;

            System.out.print("Enter " + name + "'s Grade: ");
            double grade = scanner.nextDouble();
            
            studentList.add(new Student(name, grade));
        }

        if (!studentList.isEmpty()) {
            generateReport(studentList);
        }
    }

    public static void generateReport(ArrayList<Student> list) {
        double total = 0;
        Student topStudent = list.get(0);
        Student bottomStudent = list.get(0);

        System.out.println("\n--- FINAL SUMMARY REPORT ---");
        System.out.println("-----------------------------");
        
        for (Student s : list) {
            total += s.grade;
            if (s.grade > topStudent.grade) topStudent = s;
            if (s.grade < bottomStudent.grade) bottomStudent = s;
            
            System.out.println("Student: " + s.name + " | Grade: " + s.grade);
        }

        double average = total / list.size();

        System.out.println("-----------------------------");
        System.out.printf("Average Score: %.2f\n", average);
        System.out.println("Highest: " + topStudent.name + " (" + topStudent.grade + ")");
        System.out.println("Lowest:  " + bottomStudent.name + " (" + bottomStudent.grade + ")");
    }
}