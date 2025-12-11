import java.util.*;

class BankAccount {
    String accountNumber;
    String holderName;
    double balance;
    ArrayList<String> transactions = new ArrayList<>();

    BankAccount(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    // Add a transaction (keep only last 5)
    void addTransaction(String txn) {
        if (transactions.size() == 5) {
            transactions.remove(0); // remove oldest
        }
        transactions.add(txn);
    }
}

public class ATMSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create one sample account
        BankAccount account = new BankAccount("12345678", "John Doe", 5000);

        while (true) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Mini Statement");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    checkBalance(account);
                    break;

                case 2:
                    depositMoney(account, sc);
                    break;

                case 3:
                    withdrawMoney(account, sc);
                    break;

                case 4:
                    miniStatement(account);
                    break;

                case 5:
                    System.out.println("Thank you for using ATM. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // ---------------- CHECK BALANCE ----------------
    public static void checkBalance(BankAccount acc) {
        System.out.println("Current Balance: ₹" + acc.balance);
        acc.addTransaction("Checked Balance: ₹" + acc.balance);
    }

    // ---------------- DEPOSIT MONEY ----------------
    public static void depositMoney(BankAccount acc, Scanner sc) {
        System.out.print("Enter amount to deposit: ");
        double amt = sc.nextDouble();

        if (amt <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        acc.balance += amt;
        System.out.println("Successfully Deposited ₹" + amt);
        acc.addTransaction("Deposited: ₹" + amt);
    }

    // ---------------- WITHDRAW MONEY ----------------
    public static void withdrawMoney(BankAccount acc, Scanner sc) {
        System.out.print("Enter amount to withdraw: ");
        double amt = sc.nextDouble();

        if (amt <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (amt > acc.balance) {
            System.out.println("Insufficient Balance.");
            acc.addTransaction("Failed Withdraw Attempt: ₹" + amt);
            return;
        }

        acc.balance -= amt;
        System.out.println("Successfully Withdrawn ₹" + amt);
        acc.addTransaction("Withdrawn: ₹" + amt);
    }

    // ---------------- MINI STATEMENT ----------------
    public static void miniStatement(BankAccount acc) {
        System.out.println("\n--- Last 5 Transactions ---");

        if (acc.transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        for (String txn : acc.transactions) {
            System.out.println(txn);
        }
    }
}
