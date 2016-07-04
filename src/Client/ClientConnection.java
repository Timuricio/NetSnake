package Client;

import Server.ServerField;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Timur on 04.07.2016.
 */
public class ClientConnection implements AutoCloseable
{
    private Integer numberOnMatrix;
    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;


    public ClientConnection(Socket socket) throws IOException
    {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    public void send(ClientField field) throws IOException
    {
        out.writeObject(field);
    }

    public ServerField resive() throws IOException, ClassNotFoundException
    {
        return (ServerField) in.readObject();
    }

    public int resiveNumber() throws IOException, ClassNotFoundException
    {
        return (Integer) in.readObject();
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


