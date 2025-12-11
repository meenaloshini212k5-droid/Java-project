import java.io.*;
import java.util.*;

class Student {
    int rollNo;
    String name;
    int age;

    Student(int rollNo, String name, int age) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Roll No: " + rollNo + ", Name: " + name + ", Age: " + age;
    }
}

public class StudentManagementSystem {

    static ArrayList<Student> students = new ArrayList<>();
    static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {
        loadDataFromFile();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    searchStudent(sc);
                    break;
                case 4:
                    deleteStudent(sc);
                    break;
                case 5:
                    saveDataToFile();
                    System.out.println("Data saved. Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // ------------------- Add Student -------------------
    public static void addStudent(Scanner sc) {
        System.out.print("Enter Roll No: ");
        int roll = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        students.add(new Student(roll, name, age));
        System.out.println("Student Added Successfully!");
    }

    // ------------------- View All -------------------
    public static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n--- Student List ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // ------------------- Search -------------------
    public static void searchStudent(Scanner sc) {
        System.out.print("Enter Roll No to Search: ");
        int roll = sc.nextInt();

        for (Student s : students) {
            if (s.rollNo == roll) {
                System.out.println("Student Found: " + s);
                return;
            }
        }
        System.out.println("Student Not Found.");
    }

    // ------------------- Delete -------------------
    public static void deleteStudent(Scanner sc) {
        System.out.print("Enter Roll No to Delete: ");
        int roll = sc.nextInt();

        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            if (it.next().rollNo == roll) {
                it.remove();
                System.out.println("Student Deleted Successfully.");
                return;
            }
        }
        System.out.println("Student Not Found.");
    }

    // ------------------- Save Data to File -------------------
    public static void saveDataToFile() {
        try {
            FileWriter fw = new FileWriter(FILE_NAME);
            for (Student s : students) {
                fw.write(s.rollNo + "," + s.name + "," + s.age + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }

    // ------------------- Load Data from File -------------------
    public static void loadDataFromFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int roll = Integer.parseInt(data[0]);
                String name = data[1];
                int age = Integer.parseInt(data[2]);

                students.add(new Student(roll, name, age));
            }
            br.close();

        } catch (FileNotFoundException e) {
            // Ignore - file will be created later
        } catch (Exception e) {
            System.out.println("Error reading file.");
        }
    }
}
