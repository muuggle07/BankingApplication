import java.util.ArrayList;
import java.util.Scanner;

public class BankingApplication {

    private static final Scanner sc = new Scanner(System.in);
    private static double balance = 0;
    private static ArrayList<String> transactionHistory = new ArrayList<String>();

    public static void main(String[] args) {
        login();
        displayMenu();
    }

    private static void login() {
        System.out.println("Welcome to the Banking Application");

        // Ask user to create an account or login
        System.out.println("Do you have an account? Enter Y or N");
        String answer = sc.nextLine().toUpperCase();

        if (answer.equals("Y")) {
            authenticateUser();
        } else if (answer.equals("N")) {
            createNewUser();
        } else {
            System.out.println("Invalid input. Please try again.");
            login();
        }
    }

    private static void authenticateUser() {
        // Ask for username and password to authenticate user
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        // Check if username and password are valid
        if (isValidUser(username, password)) {
            System.out.println("Welcome back, " + username + "!");
        } else {
            System.out.println("Incorrect username or password. Please try again.");
            authenticateUser();
        }
    }

    private static boolean isValidUser(String username, String password) {
        // Check if username and password match with existing user credentials
        // In this example, we are hard-coding a single user with username "user" and password "password"
        return username.equals("user") && password.equals("password");
    }

    private static void createNewUser() {
        // Ask user to create a new account by providing username and password
        System.out.print("Enter a username: ");
        String username = sc.nextLine();
        System.out.print("Enter a password: ");
        String password = sc.nextLine();
        System.out.println("Account created successfully. Welcome, " + username + "!");
    }

    private static void displayMenu() {
        int choice = 0;

        while (choice != 5) {
            System.out.println("\n1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    deposit();
                    break;

                case 2:
                    withdraw();
                    break;

                case 3:
                    checkBalance();
                    break;

                case 4:
                    displayTransactionHistory();
                    break;

                case 5:
                    System.out.println("Thank you for using the Banking Application!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    private static final String NEW_BALANCE_MESSAGE = "New balance: ₹%.2f\n";
    private static void deposit() {
        System.out.print("Enter amount to deposit: ");
        double depositAmount = sc.nextDouble();

        // Validate deposit amount
        if (depositAmount < 0) {
            System.out.println("Invalid amount. Please try again.");
            deposit();
            return;
        }


        balance += depositAmount;
        transactionHistory.add("Deposit: +" + depositAmount);
        System.out.printf("Deposit successful. New balance: ₹%.2f%n", balance);

    }



    private static void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double withdrawAmount = sc.nextDouble();

        // Validate withdraw amount
        if (withdrawAmount < 0 || withdrawAmount > balance) {
            System.out.println("Invalid amount. Please try again.");
            withdraw();
            return;
        }

        balance -= withdrawAmount;
        transactionHistory.add("Withdraw: -" + withdrawAmount);
        System.out.printf("Withdraw successful. New balance: ₹%.2f%n", balance);

    }

    private static void checkBalance() {
        System.out.printf("Your current balance is: ₹%.2f\n", balance);
    }
    private static void displayTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transaction history found.");
            return;
        }

        System.out.println("\nTransaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }


}
