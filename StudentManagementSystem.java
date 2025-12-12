package Student;

imp ort java.sql.*;
import java.util.Scanner;

public class StudentManagementSystem {

    // Database connection details
    static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    static final String USER = "root";      // Your MySQL username
    static final String PASS = "root";      // Your MySQL password

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Load MySQL Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            return;
        }

        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudent();
                case 4 -> deleteStudent();
                case 5 -> {
                    System.out.println("Exiting program...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // ---------------- ADD STUDENT ----------------
    public static void addStudent() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

            System.out.print("Enter Roll No: ");
            int roll = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = sc.nextInt();

            String sql = "INSERT INTO students(rollNo, name, age) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, roll);
            ps.setString(2, name);
            ps.setInt(3, age);

            ps.executeUpdate();
            System.out.println("Student added successfully!");

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error: Roll number already exists.");
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    // ---------------- VIEW STUDENTS ----------------
    public static void viewStudents() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

            String sql = "SELECT * FROM students";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n--- STUDENT LIST ---");
            boolean empty = true;

            while (rs.next()) {
                empty = false;
                System.out.println("Roll No: " + rs.getInt("rollNo") +
                                   ", Name: " + rs.getString("name") +
                                   ", Age: " + rs.getInt("age"));
            }

            if (empty) System.out.println("No students found.");

        } catch (SQLException e) {
            System.out.println("Error fetching students: " + e.getMessage());
        }
    }

    // ---------------- SEARCH STUDENT ----------------
    public static void searchStudent() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

            System.out.print("Enter Roll No to search: ");
            int roll = sc.nextInt();

            String sql = "SELECT * FROM students WHERE rollNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, roll);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\nStudent Found:");
                System.out.println("Roll No : " + rs.getInt("rollNo"));
                System.out.println("Name    : " + rs.getString("name"));
                System.out.println("Age     : " + rs.getInt("age"));
            } else {
                System.out.println("Student not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error searching student: " + e.getMessage());
        }
    }

    // ---------------- DELETE STUDENT ----------------
    public static void deleteStudent() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

            System.out.print("Enter Roll No to delete: ");
            int roll = sc.nextInt();

            String sql = "DELETE FROM students WHERE rollNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, roll);

            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }
}
