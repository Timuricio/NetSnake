package Client;

import Common.Connection;
import Common.Field;

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

        final int time = 100;

        Connection connection;
        String address = "";
        int metka = 0;

        try (Socket client = new Socket())
        {
            //address = JOptionPane.showInputDialog(frame, "Enter the server address", "Options", JOptionPane.QUESTION_MESSAGE);
            address = "localhost";
            client.connect(new InetSocketAddress(address, 22480));

            connection = new Connection(client);
            metka = connection.resiveNumber();
            clientField = connection.resive();

            snake = new Snake(metka, clientField);
            frame.addKeyListener(new ClientKeyListener(snake));

            while (true)
            {
                wait(time);

                clientField = snake.move();

                connection.send(clientField);

                Field f = connection.resive();

                frame.repaintField(f);
            }

        } catch (IOException e)
        {
            e.printStackTrace();
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
