import java.util.Scanner;
import java.util.ArrayList;

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Type: " + type + ", Amount: " + amount;
    }
}

class Account {
    private double balance;
    private ArrayList<Transaction> transactions;

    public Account() {
        balance = 0;
        transactions = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount));
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void transfer(double amount, Account receiver) {
        if (balance >= amount) {
            balance -= amount;
            receiver.deposit(amount);
            transactions.add(new Transaction("Transfer to " + receiver.toString(), amount));
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void printTransactions() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}

class ATM {
    private Account account;

    public ATM(Account account) {
        this.account = account;
    }

    public void displayMenu() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    account.printTransactions();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    System.out.print("Enter receiver's account: ");
                    Account receiver = new Account(); // Just for simulation
                    account.transfer(transferAmount, receiver);
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
}

public class Main {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user id: ");
        String userId = scanner.next();
        System.out.print("Enter user pin: ");
        String userPin = scanner.next();

        // Authentication (in reality, this would be more secure)
        if (userId.equals("user123") && userPin.equals("1234")) {
            Account account = new Account(); // Create user's account
            ATM atm = new ATM(account);
            atm.displayMenu();
        } else {
            System.out.println("Invalid user id or pin.");
        }
    }
}
