import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

    private Socket socket = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;

    Client() throws IOException {
        socket = new Socket("127.0.0.1" , 6666);

        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(System.in);

        String line = null;
        do{
            try
            {
                line = in.readLine();
                out.writeUTF(line);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }while(!line.equals("over"));

        try{
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            socket.close();
            in.close();
            out.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
    }

}
