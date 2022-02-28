package Bank;
import java.util.Scanner;
public class Account
{
    private int balance;
    private int previousTransaction;
    private String customerName;
    private String customerID;

    public Account()
    {
        customerName = "Unknown";
        customerID = "Unknown";
    }

    public Account(String customerName , String customerID)
    {
        this.customerName = customerName;
        this.customerID = customerID;
    }

    public void balance()
    {
        System.out.println("==========");
        System.out.printf("Balance : %d%n" , balance);
        System.out.println("==========");
    }

    public void deposit(int amount)
    {
        balance += amount;
        previousTransaction = amount;
    }

    public void withdrawal(int amount)
    {
        if(amount > 0)
        {
            balance -= amount;
            previousTransaction = -amount;
        }
        else if(amount == 0)
        {
            return;
        }
        else
        {
            System.out.println("invalid input");
        }
    }

    public void prevTransaction()
    {
        if(previousTransaction > 0)
        {
            System.out.printf("You deposite - %d%n" , previousTransaction);
        }
        else if(previousTransaction < 0)
        {
            System.out.printf("You withdrawal - %d%n" , (+previousTransaction));
        }
        else
        {
            System.out.println("No previous transactions");
        }
    }

    public void showMenu()
    {
        char option1 = '\0', option = '\0';
        int amount = 0;
        Scanner scan = new Scanner(System.in);
        System.out.printf("Welcome : %s%n" , customerName);
        System.out.println();
        System.out.printf("Yuor ID : %s%n" , customerID);
        System.out.println();
        System.out.println("What would you like to do: ");
        System.out.println();
        System.out.println("A - Check your balance");
        System.out.println("B - Make a deposite");
        System.out.println("C - Make a withdrawal");
        System.out.println("D - Check previous transaction");
        System.out.println("E - Exit");
        System.out.println();
        do {
            System.out.println("enter option: ");
            option1 = scan.next().charAt(0);
            option = Character.toUpperCase(option1);
            switch (option)
            {
                case 'A':
                    balance();
                    break;
                case 'B':
                    System.out.print("Enter amount you want to deposite: ");
                    amount = scan.nextInt();
                    deposit(amount);
                    System.out.println();
                    break;
                case 'C':
                    System.out.print("Enter amount you want to withdrawal: ");
                    amount = scan.nextInt();
                    withdrawal(amount);
                    System.out.println();
                    break;
                case 'D':
                    prevTransaction();
                    System.out.println();
                    break;
                case 'E':
                    System.out.println("Goodbye :)");
                    break;
                default:
                    System.out.println("invalid option");
            }


        }while(option != 'E');


    }

}
