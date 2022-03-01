
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Main
{

    public static void enterUser() {
        boolean correctUser = false;
        UserAccount uAccount = new UserAccount();
        String username , userID;
        Scanner scan = new Scanner(System.in);
        Account user = new Account();
        String choice = "\0";
        do {
            System.out.println("==========");
            System.out.println("Bank");
            System.out.println("==========");
            System.out.println("log in");
            System.out.println("sign up");
            System.out.println("");

            do {
                System.out.print("enter choice: ");
                choice = scan.nextLine();
            }while(!choice.equals("log in") && !choice.equals("sign up"));

            System.out.println();
            System.out.print("enter username: ");
            username = scan.nextLine();
            System.out.print("enter userID: ");
            userID = scan.nextLine();
            System.out.println();
            user.setCustomerName(username);
            user.setCustomerID(userID);

            if(choice.equals("log in"))
            {
                try
                {

                    try
                    {
                        correctUser = uAccount.checkUser(user);
                    }
                    catch (IOException e)
                    {

                    }
                    if(!correctUser)
                    {
                        System.out.println("user not found");
                    }

                    // user found
                    if(correctUser)
                    {
                        int balance = uAccount.getBalance(username , userID);
                        int prevTransaction = uAccount.getPreviousTransaction(username , userID);
                        user.setBalance(balance);
                        user.setPreviousTransaction(prevTransaction);
                        user.showMenu();
                        // ad userUpdate -> to save changed data in file
                        try
                        {
                            uAccount.updateUserInfo(user);
                        }
                        catch (IOException e)
                        {
                            System.out.println("error in modifing file");
                        }

                    }
                }
                catch (IOException e)
                {
                    System.out.println("something went wrong");
                }


            }
            else
            {
                correctUser = true;
                user.showMenu();
                // write new user account in file
                try
                {
                    uAccount.addUser(user);
                }
                catch (IOException e)
                {
                    System.out.println("error in file");
                }


            }

        } while (!correctUser);


    }


    public static void main(String[] args)
    {
        enterUser();

    }
}
