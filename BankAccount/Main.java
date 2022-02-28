package Bank;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String custName = scan.nextLine();
        String custID = scan.nextLine();
        Account bankAccount = new Account(custName , custID);
        bankAccount.showMenu();
    }
}
