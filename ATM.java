package ATM_INTERFACE;

import java.util.*;

class TransactionHistory{
    private List<UserAccount> transactions;
    
    public TransactionHistory(){
        transactions = new ArrayList<>();
    }
    
    public void addTransaction(UserAccount transaction){ 
            transactions.add(transaction);    
    }
    
    public List<UserAccount> getTransactions(){
        return transactions;
    }
}

class UserAccount  {

    private double balance;
    private Date date;
    private String description;
    private double amount;
    private TransactionHistory transactionhistory;
    
    public UserAccount(double balance) {
        this.balance = balance;
         transactionhistory = new TransactionHistory();
    }
    
    public UserAccount(Date date, String description, double amount){
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Rs. " + amount + " withdrawn successfully");
            System.out.print("\n");
            UserAccount transaction = new UserAccount(new Date(), "Withdraw/debited via ATM", amount);
            transactionhistory.addTransaction(transaction);

        } else {
            System.out.println("Withdrawn Failure!! Unable to withdraw due to Insufficient Balance.");
            System.out.print("\n");
        }
    }

    public void deposit(double amount) {
        balance += amount; //balance = balance + amount
        System.out.println("Rs. " + amount + " deposited successfully.");
        System.out.print("\n");
        UserAccount transaction = new UserAccount(new Date(), "Deposit/credited via ATM", amount);
        transactionhistory.addTransaction(transaction);
    }

    public void checkBalance() {
        System.out.println("Current Balance: Rs. " + balance);
        System.out.print("\n");
    }
    
    public List<UserAccount> getTransactionHistory(){
        return transactionhistory.getTransactions();
    }
}

class ATM {

    public static void main(String[] args) {

        UserAccount obj = new UserAccount(10000);
        Scanner sc = new Scanner(System.in);

        int option;
        double userPin = 2486;
        double pin;
        double amount;

        System.out.println("Welcome to the ATM Machine");
        System.out.print("Enter the User Pin: ");
        pin = sc.nextDouble();
        System.out.print("\n");
        if (pin == userPin) {
            do {
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit the Money");
                System.out.println("3. Withdraw the Money");
                System.out.println("4. Transaction History");
                System.out.println("5. Quit");

                System.out.print("\n");
                System.out.print("Please Select an Option from the above : ");
                option = sc.nextInt();

                switch (option) {
                    case 1:
                        obj.checkBalance(); 
                        break;
                    case 2:
                        System.out.print("Enter the amount to deposit: ");
                        amount = sc.nextDouble();
                        obj.deposit(amount);
                        break;
                    case 3:
                        System.out.print("Enter the amount to withdraw: ");
                        amount = sc.nextDouble();
                        obj.withdraw(amount);
                        break;
                    case 4:
                        List<UserAccount> history = obj.getTransactionHistory();
                        
                        for(UserAccount transaction : history){
                            System.out.println(transaction.getDate()+" - "+transaction.getDescription()+" : "+transaction.getAmount());
                        }
                        System.out.print("\n");
                        break;
                    case 5:
                        System.out.print("\n");
                        System.out.println("Successfully Quit.");
                        System.out.println("Thank you for using the Services.");
                        System.out.println("Please Visit Again!!");
                        break;
                    default:
                        System.out.println("Invalid Option!! Please Try Again with the appropriate options.");
                        System.out.print("\n");
                }
            } while (option != 5);
        }
        else{
            System.out.println("Please!! Enter the correct User Pin.");
        }
    }

}
