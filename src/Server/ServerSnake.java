package Server;

import Client.ClientField;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Timur on 04.07.2016.
 */
public class ServerSnake
{
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;
    private static ServerSocket server;
    private static List<ConnectionHandler> handlers;
    private static List<ServerConnection> connections;
    private static ExecutorService executorService;

    private static volatile List<ClientField> fields = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException
    {
        server = new ServerSocket(22480);
        ServerConnection serverConnection;

        connections = new ArrayList<>();
        handlers = new ArrayList<>();

        ServerFrame serverFrame = new ServerFrame();
        ServerField serverField = new ServerField(HEIGHT,WIDTH);

        String temp = "";
        int playersQuantity = 0;
        int playersConnected = 0;
        while (temp == null || temp.isEmpty() || !temp.matches("[0-9]*"))
        {
            temp = JOptionPane.showInputDialog(serverFrame, "Enter the number of players:", "Options", JOptionPane.PLAIN_MESSAGE);
        }

        playersQuantity = Integer.valueOf(temp);
        serverFrame.setQuantity(playersQuantity);

        executorService = Executors.newFixedThreadPool(5);

        while (playersConnected<playersQuantity)
        {
            serverConnection = new ServerConnection(server.accept());
            System.out.println("new connection! " + serverConnection.getSocket().getRemoteSocketAddress());
            connections.add(serverConnection);
            handlers.add(new ConnectionHandler(serverConnection));
            playersConnected++;
            serverFrame.setQuantityConnected(playersConnected);
            Thread.sleep(10);
            serverConnection.sendNumber(playersConnected+4);
        }



        //while (true)
        {
            System.out.println("All users connected!");
        }
    }

    public static void serverClose() throws Exception
    {
        for(ServerConnection connection : connections)
            connection.close();

        server.close();
        System.exit(0);
    }
}
