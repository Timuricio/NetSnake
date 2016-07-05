package Client;

import Common.Connection;
import Common.Field;
import Server.ServerSnake;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

        Field field = new Field(ServerSnake.WIDTH, ServerSnake.HEIGHT);
        int[][] matrix = field.getMatrix();

        final int time = 200;

        Connection connection;
        String address = "";
        int metka = 0;

        try (Socket client = new Socket())
        {

            address = JOptionPane.showInputDialog(frame, "Enter the server address", "Options", JOptionPane.QUESTION_MESSAGE);
            client.connect(new InetSocketAddress(address, 22480));

            connection = new Connection(client);
            metka = connection.resiveNumber();
            field = connection.resive();
            snake = new Snake(metka, field);



            while (true)
            {
                wait(time);
                field = snake.move();
                connection.send(field);
                field = connection.resive();
                frame.repaintField(field);
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
