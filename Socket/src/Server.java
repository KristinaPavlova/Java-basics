import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private DataInputStream in = null;

    Server(int portNum) throws IOException {

        this.serverSocket = new ServerSocket(portNum);

        socket = serverSocket.accept();
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

        System.out.println("connected");

        String line = null;
        do{
            try
            {
                line = in.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(line);
        }while(!line.equals("over") );

        try {
            in.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            in.close();
            socket.close();
            serverSocket.close();
        }

    }
    public static void main(String[] args) throws IOException {

        Server server = new Server(6666);

    }
}

