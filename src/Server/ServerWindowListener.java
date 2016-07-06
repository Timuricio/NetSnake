package Server;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by Timur on 06.07.2016.
 */
public class ServerWindowListener implements WindowListener
{
    @Override
    public void windowOpened(WindowEvent e)
    {

    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        try
        {
            ServerSnake.serverClose();

        } catch (Exception e1)
        {
            e1.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e)
    {

    }

    @Override
    public void windowIconified(WindowEvent e)
    {

    }

    @Override
    public void windowDeiconified(WindowEvent e)
    {

    }

    @Override
    public void windowActivated(WindowEvent e)
    {

    }

    @Override
    public void windowDeactivated(WindowEvent e)
    {

    }
}
