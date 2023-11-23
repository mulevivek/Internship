import java.util.Scanner;

class User {
    private String userId;
    private String userPin;
    private double balance;

    public User(String userId, String userPin, double balance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public void transfer(User recipient, double amount) {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            System.out.println("Transfer successful");
        } else {
            System.out.println("Insufficient funds");
        }
    }
}

class ATM {
    private User currentUser;

    public void login(User user) {
        this.currentUser = user;
    }

    public void showMenu() {
        System.out.println("1. Transactions History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
    }

    public void performOperation(int choice) {
        Scanner scanner = new Scanner(System.in);

        switch (choice) {
            case 1:
                System.out.println("Transaction History: Not implemented in this example");
                break;
            case 2:
                System.out.print("Enter withdrawal amount: ");
                double withdrawalAmount = scanner.nextDouble();
                currentUser.withdraw(withdrawalAmount);
                break;
            case 3:
                System.out.print("Enter deposit amount: ");
                double depositAmount = scanner.nextDouble();
                currentUser.deposit(depositAmount);
                break;
            case 4:
                System.out.print("Enter recipient's user ID: ");
                String recipientId = scanner.next();
                System.out.print("Enter transfer amount: ");
                double transferAmount = scanner.nextDouble();
                User recipient = new User(recipientId, "", 0); // Assuming a simple recipient creation
                currentUser.transfer(recipient, transferAmount);
                break;
            case 5:
                System.out.println("Quitting ATM. Have a nice day!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please enter a number from 1 to 5.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Assume some initial user and ATM setup
        User user = new User("12345", "6789", 1000.0);
        ATM atm = new ATM();

        // ATM login
        System.out.print("Enter user ID: ");
        String userId = scanner.next();
        System.out.print("Enter user PIN: ");
        String userPin = scanner.next();

        if (userId.equals(user.getUserId()) && userPin.equals(user.getUserPin())) {
            atm.login(user);

            while (true) {
                atm.showMenu();
                System.out.print("Enter your choice (1-5): ");
                int choice = scanner.nextInt();

                atm.performOperation(choice);
            }
        } else {
            System.out.println("Invalid user ID or PIN. Exiting...");
        }
    }
}
