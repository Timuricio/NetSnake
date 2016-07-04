package Server;

import Client.ClientField;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Timur on 04.07.2016.
 */
public class ServerConnection implements AutoCloseable
{
    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;


    public ServerConnection(Socket socket) throws IOException
    {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    public void send(ServerField field) throws IOException
    {
        out.writeObject(field);
    }

    public void sendNumber(Integer number) throws IOException
    {
        out.writeObject(number);
    }


    public ClientField resive() throws IOException, ClassNotFoundException
    {

        ClientField field = (ClientField) in.readObject();
        return field;

    }

    public Socket getSocket()
    {
        return socket;
    }



    @Override
    public void close() throws Exception
    {
        in.close();
        out.close();
        socket.close();
    }
}


