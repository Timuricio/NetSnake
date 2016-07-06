package Server;

import Common.Connection;

import java.io.IOException;

/**
 * Created by Timur on 06.07.2016.
 */
public class ReciverThread extends Thread
{
    Connection connection;

    public ReciverThread(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public void run()
    {
        while (true)
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
                e.printStackTrace();
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }
}
