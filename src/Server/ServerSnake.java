package Server;

import Common.Connection;
import Common.Field;
import Common.Test;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Timur on 04.07.2016.
 */
public class ServerSnake
{
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;
    private static ServerSocket server;
    private static List<Connection> connections;
    private static List<Field> fields = new ArrayList<>();
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException
    {
        server = new ServerSocket(22480);
        Connection serverConnection;

        connections = new ArrayList<>();


        ServerFrame serverFrame = new ServerFrame();
        Field serverField;
        ClientFieldCombiner combiner;

        String temp = "";
        int playersQuantity = 0;
        int playersConnected = 0;
        while (temp == null || temp.isEmpty() || !temp.matches("[0-9]*"))
        {
            temp = JOptionPane.showInputDialog(serverFrame, "Enter the number of players:", "Options", JOptionPane.PLAIN_MESSAGE);
        }

        playersQuantity = Integer.valueOf(temp);
        serverFrame.setQuantity(playersQuantity);

        while (playersConnected < playersQuantity)
        {
            serverConnection = new Connection(server.accept());
            System.out.println("new connection! " + serverConnection.getSocket().getRemoteSocketAddress());
            connections.add(serverConnection);
            playersConnected++;
            serverFrame.setQuantityConnected(playersConnected);
            Thread.sleep(10);
            serverConnection.sendNumber(playersConnected + 4);
        }

        combiner = new ClientFieldCombiner(playersQuantity);
        sendToAllUsers(combiner.getServerField());

        while (true)
        {
            Thread.sleep(10);
            resiveFromAll();

            sendToAllUsers(combiner.combine(fields));
            fields.clear();
        }
    }

    private static void sendToAllUsers(Field field) throws IOException
    {
        for (Connection connection : connections)
            connection.send(field);
    }

    private static void resiveFromAll() throws IOException, ClassNotFoundException
    {
        for (Connection c : connections)
            fields.add(c.resive());
    }

    public static void serverClose() throws Exception
    {
        for (Connection connection : connections)
            connection.close();

        server.close();
        System.exit(0);
    }

    public static List<Field> getFields()
    {
        return fields;
    }
}
