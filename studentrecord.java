import java.util.ArrayList;
import java.util.Scanner;

class Student {

    private int id;
    private String name;
    private int age;
    private int[] marks;

    Student(int id, String name, int age, int[] marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    public int calculateTotal() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    public double calculatePercentage() {
        return calculateTotal() / (double) marks.length;
    }

    public String calculateGrade() {
        double percentage = calculatePercentage();

        if (percentage >= 90) return "A+";
        else if (percentage >= 80) return "A";
        else if (percentage >= 70) return "B";
        else if (percentage >= 60) return "C";
        else if (percentage >= 40) return "D";
        else return "F";
    }

    public void display() {
        System.out.println("\n--------------------------");
        System.out.println("Student ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Total Marks: " + calculateTotal());
        System.out.println("Percentage: " + calculatePercentage() + "%");
        System.out.println("Grade: " + calculateGrade());
        System.out.println("--------------------------");
    }
}

public class StudentRecord {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    static int[] readMarks() {
        int[] marks = new int[5];

        for (int i = 0; i < 5; i++) {
            System.out.print("Enter marks for Subject " + (i + 1) + ": ");
            marks[i] = sc.nextInt();

            if (marks[i] < 0 || marks[i] > 100) {
                System.out.println("Invalid marks! Enter between 0 and 100.");
                i--;
            }
        }

        return marks;
    }

    static Student findStudent(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        if (findStudent(id) != null) {
            System.out.println("Student ID already exists!");
            return;
        }

        sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        if (age <= 0) {
            System.out.println("Invalid age!");
            return;
        }

        int[] marks = readMarks();

        students.add(new Student(id, name, age, marks));
        System.out.println("Student added successfully!");
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found!");
            return;
        }

        for (Student s : students) {
            s.display();
        }
    }

    static void searchStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        Student s = findStudent(id);

        if (s != null) {
            s.display();
        } else {
            System.out.println("Student not found!");
        }
    }

    static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();

        Student s = findStudent(id);

        if (s == null) {
            System.out.println("Student not found!");
            return;
        }

        sc.nextLine();

        System.out.print("Enter new name: ");
        String name = sc.nextLine();

        System.out.print("Enter new age: ");
        int age = sc.nextInt();

        if (age <= 0) {
            System.out.println("Invalid age!");
            return;
        }

        System.out.println("Enter new subject marks:");
        int[] marks = readMarks();

        s.setName(name);
        s.setAge(age);
        s.setMarks(marks);

        System.out.println("Student updated successfully!");
    }

    static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();

        Student s = findStudent(id);

        if (s != null) {
            students.remove(s);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    static void calculateGrade() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        Student s = findStudent(id);

        if (s != null) {
            System.out.println("Total: " + s.calculateTotal());
            System.out.println("Percentage: " + s.calculatePercentage() + "%");
            System.out.println("Grade: " + s.calculateGrade());
        } else {
            System.out.println("Student not found!");
        }
    }

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n================================");
            System.out.println(" STUDENT RECORD AND GRADING SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Calculate Grade");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    calculateGrade();
                    break;

                case 7:
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 7);

        sc.close();
    }
}
