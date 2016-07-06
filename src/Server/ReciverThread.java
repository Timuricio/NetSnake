package Server;

import Common.Connection;

import java.io.IOException;

/**
 * Created by Timur on 06.07.2016.
 */
public class ReciverThread extends Thread
{
    private Connection connection;
    private boolean work;

    public ReciverThread(Connection connection)
    {
        this.connection = connection;
        this.setDaemon(true);
        work= true;
    }

    @Override
    public void run()
    {
        while (work)
        {
            try
            {
                ServerSnake.getFields().add(connection.resive());
                synchronized (ServerSnake.monitor)
                {
                    ServerSnake.monitor.notifyAll();
                }
            } catch (IOException e)
            {
                work = false;

            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }

        try
        {
            System.out.println(String.format("Connection %s closing...",connection.getSocket().getRemoteSocketAddress()));
            connection.close();
            System.out.println("Connection closed.");

            for (int i = 0; i < ServerSnake.getConnections().size(); i++)
            {
                if (ServerSnake.getConnections().get(i).equals(connection));
                {
                    ServerSnake.getConnections().remove(i);
                    break;
                }
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
