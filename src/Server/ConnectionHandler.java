package Server;

import Common.Field;
import Common.Connection;

import java.io.IOException;

/**
 * Created by Timur on 04.07.2016.
 */
public class ConnectionHandler implements Runnable
{
    private boolean isSendReady = false;
    private Connection connection;
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

                while (!isSendReady)
                {
                    wait();
                }

                connection.send(serverField);
                isSendReady = false;


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

    public boolean isSendReady()
    {
        return isSendReady;
    }

    public void setIsSendReady(boolean isSendReady)
    {
        this.isSendReady = isSendReady;
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
