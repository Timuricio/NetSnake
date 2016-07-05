package Server;

import Common.Field;
import Common.Connection;

import java.io.IOException;

/**
 * Created by Timur on 04.07.2016.
 */
public class ConnectionHandler implements Runnable
{

    private Connection connection;
    private ClientFieldCombiner combiner;
    private Field clientField;
    private Field serverField;

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
                clientField = connection.resive();
                ServerSnake.getFields().add(clientField);

                while (!ServerSnake.isResiveReady())
                {
                    wait();
                }



            } catch (IOException e)
            {
                e.printStackTrace();
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            } catch (InterruptedException e)
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

    public Field getClientField()
    {
        return clientField;
    }

    public void setClientField(Field clientField)
    {
        this.clientField = clientField;
    }

    public Field getServerField()
    {
        return serverField;
    }

    public void setServerField(Field serverField)
    {
        this.serverField = serverField;
    }
}
