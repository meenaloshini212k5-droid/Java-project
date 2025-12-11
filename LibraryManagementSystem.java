import java.util.*;

class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author +
               ", Issued: " + (isIssued ? "Yes" : "No");
    }
}

public class LibraryManagementSystem {
    static ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. View All Books");
            System.out.println("5. View Issued Books");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    addBook(sc);
                    break;
                case 2:
                    issueBook(sc);
                    break;
                case 3:
                    returnBook(sc);
                    break;
                case 4:
                    viewAllBooks();
                    break;
                case 5:
                    viewIssuedBooks();
                    break;
                case 6:
                    System.out.println("Exiting Library System...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // ---------------- ADD BOOK ----------------
    public static void addBook(Scanner sc) {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();

        books.add(new Book(id, title, author));
        System.out.println("Book Added Successfully!");
    }

    // ---------------- ISSUE BOOK ----------------
    public static void issueBook(Scanner sc) {
        System.out.print("Enter Book ID to Issue: ");
        int id = sc.nextInt();

        for (Book b : books) {
            if (b.id == id) {
                if (b.isIssued) {
                    System.out.println("Book is already issued.");
                } else {
                    b.isIssued = true;
                    System.out.println("Book Issued Successfully!");
                }
                return;
            }
        }
        System.out.println("Book Not Found.");
    }

    // ---------------- RETURN BOOK ----------------
    public static void returnBook(Scanner sc) {
        System.out.print("Enter Book ID to Return: ");
        int id = sc.nextInt();

        for (Book b : books) {
            if (b.id == id) {
                if (!b.isIssued) {
                    System.out.println("Book is not issued currently.");
                } else {
                    b.isIssued = false;
                    System.out.println("Book Returned Successfully!");
                }
                return;
            }
        }
        System.out.println("Book Not Found.");
    }

    // ---------------- VIEW ALL BOOKS ----------------
    public static void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n--- All Books ---");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    // ---------------- VIEW ISSUED BOOKS ----------------
    public static void viewIssuedBooks() {
        System.out.println("\n--- Issued Books ---");
        boolean found = false;

        for (Book b : books) {
            if (b.isIssued) {
                System.out.println(b);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books are issued.");
        }
    }
}
