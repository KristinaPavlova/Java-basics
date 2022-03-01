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

    public boolean checkUser(Account user) throws IOException
    {
        File fileObj = null;
        try
        {
            fileObj = new File("users.txt");
        }
        catch(Exception e)
        {

        }

        if(!correctFile(fileObj) )
        {
            throw new IOException("error in file");
        }
        BufferedReader buff = null;
        try
        {
            buff= new BufferedReader(new FileReader(fileObj));
        }
        catch (Exception e)
        {

        }
        String line = "\0" , username = "\0" , userID = "\0";
        try
        {
            username = user.getCustomerName();
        }
        catch (Exception e)
        {

        }
        try
        {
            userID = user.getCustomerID();
        }
        catch (Exception e)
        {

        }
        while((line = buff.readLine()) != null)
        {

            if(username.equals(line))
            {
                System.out.println("user found");
                line = buff.readLine();
                System.out.println(line);
                if(line == null)
                {
                    System.out.println("error in user information");
                    throw new IOException("error");
                }
                else if(userID.equals(line))
                {
                    return true;
                }
                else
                {
                    System.out.println("wrong ID");
                    return false;

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

            if(username.equals(line))
            {
                line = buff.readLine();
                line = buff.readLine();
                if(line == null)
                {
                    System.out.println("error in user information");
                }
                int balance = Integer.parseInt(line);
                buff.close();
                return balance;

            }
        }
        buff.close();
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
                buff.close();
                return prevTransaction;

            }
        }
        buff.close();
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

        BufferedWriter out = new BufferedWriter(new FileWriter("users.txt" , true));
        String username , userID;
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

        out.write(username + '\n');
        out.write(userID + '\n');
        out.write(Integer.toString(user.getBalance()) + '\n');
        out.write(Integer.toString(user.getPreviousTransaction()) + '\n');
        out.close();

    }

    public void updateUserInfo(Account user)throws IOException
    {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try
        {
            br = new BufferedReader(new FileReader("users.txt"));
        }
        catch (IOException e)
        {
            System.out.println("error with file");
        }
        try
        {
            bw = new BufferedWriter(new FileWriter("temp.txt"));
        }
        catch (IOException e)
        {
            System.out.println("error with file");
        }
        String line;
        String customerName = "\0" ;
        try
        {
            customerName = user.getCustomerName();
        }
        catch (Exception e )
        {
            System.out.println("error");
        }
        // try
        // {
        //     customerID = user.getCustomerID();
        // }
        // catch (Exception e )
        // {
        //     System.out.println("error");
        // }

        while((line = br.readLine()) != null)
        {
            if(line.equals(customerName)) // write new info
            {
                bw.write(line + "\n");
                line = br.readLine();
                if(line == null)
                {
                    throw new IOException("error in reading");
                }
                bw.write(line + "\n");
                bw.write(Integer.toString(user.getBalance()) + "\n");
                bw.write(Integer.toString(user.getPreviousTransaction()) + "\n");
                try
                {
                    line = br.readLine();
                }
                catch(Exception e)
                {
                    System.out.println("error");
                }
                try
                {
                    line = br.readLine();
                }
                catch(Exception e)
                {
                    System.out.println("error");
                }
            }
            else
            {
                bw.write(line + "\n");
            }

        }
        br.close();
        bw.close();
        File oldFile = new File("users.txt");
        oldFile.delete();

        File newFile = new File("temp.txt");
        newFile.renameTo(oldFile);


    }

}
