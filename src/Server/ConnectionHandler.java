package Server;

import Client.ClientField;

import java.io.IOException;

/**
 * Created by Timur on 04.07.2016.
 */
public class ConnectionHandler implements Runnable
{
    private boolean isSendReady = false;
    private ServerConnection connection;
    private ClientField clientField;
    private ServerField serverField;

    public ConnectionHandler(ServerConnection connection)
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

    public ServerConnection getConnection()
    {
        return connection;
    }

    public void setConnection(ServerConnection connection)
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

    public ClientField getClientField()
    {
        return clientField;
    }

    public void setClientField(ClientField clientField)
    {
        this.clientField = clientField;
    }

    public ServerField getServerField()
    {
        return serverField;
    }

    public void setServerField(ServerField serverField)
    {
        this.serverField = serverField;
    }
}
