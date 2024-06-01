import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public boolean withdraw(double amount) {
        return account.withdraw(amount);
    }

    public boolean deposit(double amount) {
        return account.deposit(amount);
    }

    public double checkBalance() {
        return account.getBalance();
    }

    public String miniStatement() {
        // For simplicity, we will just return the current balance.
        return "Mini Statement:\nBalance: $" + checkBalance();
    }
}

public class Transactions extends JFrame implements ActionListener {
    JButton depositButton, withdrawButton, fastCashButton, miniStatementButton, balanceEnquiryButton, exitButton;
    JLabel l;
    ATM atm;

    public Transactions() {
        atm = new ATM(new BankAccount(1000)); // Initial balance of $1000

        setTitle("TRANSACTION");
        setSize(600, 400);
        setLayout(new GridLayout(4, 2, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l = new JLabel("Please Select Your Transaction", SwingConstants.CENTER);
        l.setFont(new Font("Arial", Font.BOLD, 18));
        add(l);

        depositButton = new JButton("DEPOSIT");
        withdrawButton = new JButton("CASH WITHDRAWAL");
        fastCashButton = new JButton("FAST CASH");
        miniStatementButton = new JButton("MINI STATEMENT");
        balanceEnquiryButton = new JButton("BALANCE ENQUIRY");
        exitButton = new JButton("EXIT");

        // Set button colors
        depositButton.setBackground(new Color(0, 204, 102));
        withdrawButton.setBackground(new Color(204, 0, 0));
        fastCashButton.setBackground(new Color(0, 153, 153));
        miniStatementButton.setBackground(new Color(255, 153, 51));
        balanceEnquiryButton.setBackground(new Color(102, 178, 255));
        exitButton.setBackground(new Color(128, 128, 128));

        depositButton.setForeground(Color.WHITE);
        withdrawButton.setForeground(Color.WHITE);
        fastCashButton.setForeground(Color.WHITE);
        miniStatementButton.setForeground(Color.WHITE);
        balanceEnquiryButton.setForeground(Color.WHITE);
        exitButton.setForeground(Color.WHITE);

        add(depositButton);
        add(withdrawButton);
        add(fastCashButton);
        add(miniStatementButton);
        add(balanceEnquiryButton);
        add(exitButton);

        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        fastCashButton.addActionListener(this);
        miniStatementButton.addActionListener(this);
        balanceEnquiryButton.addActionListener(this);
        exitButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();

        switch (action) {
            case "DEPOSIT":
                String depositAmount = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
                if (depositAmount != null) {
                    try {
                        double amount = Double.parseDouble(depositAmount);
                        if (atm.deposit(amount)) {
                            JOptionPane.showMessageDialog(this, "Deposit successful! New balance: $" + atm.checkBalance());
                        } else {
                            JOptionPane.showMessageDialog(this, "Invalid deposit amount!");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Please enter a valid number.");
                    }
                }
                break;
            case "CASH WITHDRAWAL":
                String withdrawAmount = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
                if (withdrawAmount != null) {
                    try {
                        double amount = Double.parseDouble(withdrawAmount);
                        if (atm.withdraw(amount)) {
                            JOptionPane.showMessageDialog(this, "Withdrawal successful! New balance: $" + atm.checkBalance());
                        } else {
                            JOptionPane.showMessageDialog(this, "Insufficient funds or invalid amount!");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Please enter a valid number.");
                    }
                }
                break;
            case "FAST CASH":
                double fastCashAmount = 100.0;
                if (atm.withdraw(fastCashAmount)) {
                    JOptionPane.showMessageDialog(this, "Fast cash withdrawal of $100 successful! New balance: $" + atm.checkBalance());
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient funds for fast cash!");
                }
                break;
            case "MINI STATEMENT":
                JOptionPane.showMessageDialog(this, atm.miniStatement());
                break;
            case "BALANCE ENQUIRY":
                JOptionPane.showMessageDialog(this, "Current balance: $" + atm.checkBalance());
                break;
            case "EXIT":
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid action!");
        }
    }

    public static void main(String[] args) {
        new Transactions();
    }
}
