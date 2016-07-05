package Client;

import javax.swing.*;
import java.io.IOException;
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
        int metka = 0;

        try (Socket client = new Socket())
        {

            address = JOptionPane.showInputDialog(frame, "Enter the server address", "Options", JOptionPane.QUESTION_MESSAGE);
            client.connect(new InetSocketAddress(address, 22480));


            connection = new ClientConnection(client);

            metka = (Integer) connection.resiveNumber();

            System.out.println(metka);

        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
