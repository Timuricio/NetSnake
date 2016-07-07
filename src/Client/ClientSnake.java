package Client;

import Common.Connection;
import Common.Field;
import Common.Test;

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
        Snake snake;
        ClientFrame frame = new ClientFrame();

        Field clientField;

        final int time = 50;

        Connection connection;
        String address = "";
        int metka = 0;

        try (Socket client = new Socket())
        {
            address = JOptionPane.showInputDialog(frame, "Enter the server address", "Options", JOptionPane.QUESTION_MESSAGE);
            //address = "127.0.0.1";
            client.connect(new InetSocketAddress(address, 22480));

            connection = new Connection(client);
            metka = connection.resiveNumber();
            clientField = connection.resive();

            snake = new Snake(metka, clientField);
            frame.addKeyListener(new ClientKeyListener(snake));

            while (true)
            {
                wait(time);

                clientField = snake.move(clientField);

                connection.send(clientField);

                clientField = connection.resive();

                frame.repaintField(clientField);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    private static void wait(int time) throws InterruptedException
    {
        Thread.sleep(time);
    }
}
