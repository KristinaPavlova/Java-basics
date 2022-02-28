package Bank;
import java.io.*;
import java.io.IOException;

public class UserAccount {

    private boolean correctFile(File fileObj)
    {
        if(!fileObj.exists() )
        {
            System.out.println("users file doesn't exist");
           return false;
        }
        else if(!fileObj.canRead())
        {
            System.out.println("file is write only");
            return false;
        }
        else if(!fileObj.canWrite())
        {
            return false;
        }
        return true;

    }

    public boolean checkUser(String username , String userId) throws IOException
    {
        File fileObj = new File("users.txt");
        if(!correctFile(fileObj) )
        {
            throw new IOException("error in file");
        }
        BufferedReader buff = new BufferedReader(new FileReader(fileObj));
        String line = "\0";
        while((line = buff.readLine()) != null)
        {
//            System.out.println(line);
            if(username.equals(line))
            {
                System.out.println("user found");
                line = buff.readLine();
                if(line == null)
                {
                    System.out.println("error in user information");
                }
                else if(userId.equals(line))
                {
                    return true;
                }
                else
                {
                    System.out.println("wrong ID");
                }

            }
        }
        return false;

    }

    //used only after user verification
    public int getBalance(String username , String userId)throws IOException
    {
        File fileObj = new File("users.txt");
        if(!correctFile(fileObj) )
        {
            throw new IOException("error in file");
        }
        BufferedReader buff = new BufferedReader(new FileReader(fileObj));
        String line = "\0";
        while((line = buff.readLine()) != null)
        {
//            System.out.println(line);
            if(username.equals(line))
            {
                line = buff.readLine();
                line = buff.readLine();
                if(line == null)
                {
                    System.out.println("error in user information");
                }
                int balance = Integer.parseInt(line);
                return balance;

            }
        }
        throw new IOException("error in file");

    }

    public int getPreviousTransaction(String username , String userId)throws IOException
    {
        File fileObj = new File("users.txt");
        if(!correctFile(fileObj) )
        {
            throw new IOException("error in file");
        }
        BufferedReader buff = new BufferedReader(new FileReader(fileObj));
        String line = "\0";
        while((line = buff.readLine()) != null)
        {
//            System.out.println(line);
            if(username.equals(line))
            {
                line = buff.readLine();
                line = buff.readLine();
                line = buff.readLine();
                if(line == null)
                {
                    System.out.println("error in user information");
                }
                int prevTransaction = Integer.parseInt(line);
                return prevTransaction;

            }
        }
        throw new IOException("error in file");
    }

    public void addUser(Account user)throws IOException
    {
        File fileObj = new File("users.txt");
        if(!fileObj.exists())
        {
            try
            {
                fileObj.createNewFile();
            }
            catch (IOException e)
            {
                System.out.println("cant create new file");
                e.printStackTrace();
            }
        }
//        if(!correctFile(fileObj))
//        {
//            throw new IOException("error in file");
//        }
        BufferedWriter out = new BufferedWriter(new FileWriter("users.txt" , true));
        String username , userID;
        int balance , prevTransaction;
        try
        {
            username = user.getCustomerName();
        }
        catch (Exception e)
        {
            out.close();
            System.out.println("error with user");
            return;
        }

        try
        {
            userID = user.getCustomerID();
        }
        catch (Exception e)
        {
            out.close();
            System.out.println("error with user");
            return;
        }

        balance = user.getBalance();
        prevTransaction = user.getPreviousTransaction();

        String balanceStr = Integer.toString(balance);
        String prevTransactionStr = Integer.toString(prevTransaction);

        out.write(username + '\n');
        out.write(userID + '\n');
        out.write(balanceStr + '\n');
        out.write(prevTransactionStr + '\n');
        out.close();

    }

}
