package Server;

import Common.Field;
import Common.Connection;
import Common.Test;

import java.io.IOException;

/**
 * Created by Timur on 04.07.2016.
 */
public class ConnectionHandler implements Runnable
{
    private Connection connection;

    public ConnectionHandler(Connection connection)
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
                //ServerSnake.getFields().add(connection.resive());

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection()
    {
        return connection;
    }

    public void setConnection(Connection connection)
    {
        this.connection = connection;
    }

}
