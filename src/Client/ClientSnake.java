package Client;

import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Timur on 04.07.2016.
 */
public class ClientSnake
{
    public static void main(String[] args) throws InterruptedException
    {
        ClientFrame frame = new ClientFrame();
        ClientConnection connection;
        String address = "";

        try(Socket client = new Socket();
        )
        {

            do{
                address = JOptionPane.showInputDialog(frame, "Enter the server address", "Options", JOptionPane.QUESTION_MESSAGE);
                client.connect(new InetSocketAddress(address, 41000));
            }while (client.isConnected());

            connection = new ClientConnection(client);

        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
