package Bank;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Main
{

    public static void enterUser()
    {
        System.out.println("==========");
        System.out.println("Bank");
        System.out.println("==========");
        System.out.println("log in");
        System.out.println("sign up");
        System.out.println("");
        UserAccount uAccount = new UserAccount();
        String username , userID;
        Scanner scan = new Scanner(System.in);
        String choice = "\0";
        do {
            System.out.print("enter choice: ");
            choice = scan.nextLine();
        }while(!choice.equals("log in") && !choice.equals("sign up"));
        if(choice.equals("log in"))
        {
            System.out.print("enter username: ");
            username = scan.nextLine();
            System.out.println();
            System.out.print("enter userID: ");
            userID = scan.nextLine();
            System.out.println();
            try
            {
                // user found
                if(uAccount.checkUser(username , userID))
                {
                    int balance = uAccount.getBalance(username , userID);
                    int prevTransaction = uAccount.getPreviousTransaction(username , userID);
                    Account user = new Account(username , userID , balance , prevTransaction);
                    user.showMenu();
                }
            }
            catch (IOException e)
            {
                System.out.println("something went wrong");
            }
            finally {
                scan.close();
            }


        }
        else
        {
            System.out.print("enter username: ");
            username = scan.nextLine();
            System.out.println();
            System.out.print("enter userID: ");
            userID = scan.nextLine();
            System.out.println();
            Account bankAccount = new Account(username , userID);
            bankAccount.showMenu();
            // write new user account in file
            try
            {
                uAccount.addUser(bankAccount);
            }
            catch (IOException e)
            {
                System.out.println("error in file");
            }


        }


    }

    public static void main(String[] args)
    {

        enterUser();


    }
}
