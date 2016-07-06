package Server;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Timur on 04.07.2016.
 */
public class ServerFrame extends JFrame
{
    private JLabel quantity,quantityConnected;
    public ServerFrame() throws HeadlessException
    {
        super("Server NetSnake");
        init();
    }

    private void init()
    {
        setPreferredSize(new Dimension(150,300));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setDefaultLookAndFeelDecorated(true);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10,10,10,10);
        c.weighty = 1;
        c.weightx= 1;
        c.gridx = 0;

        quantity = new JLabel("Game for 0 players.");
        c.gridy = 0;
        getContentPane().add(quantity,c);

        quantityConnected = new JLabel("Connected 0 players.");
        c.gridy = 1;
        getContentPane().add(quantityConnected,c);

        addWindowListener(new ServerWindowListener());

        pack();
        setVisible(true);
    }



    public void setQuantity(int q)
    {
        quantity.setText(String.format("Game for %d players.",q));
    }

    public  void setQuantityConnected(int q)
    {
        quantityConnected.setText(String.format("Connected %d players.",q));
    }
}
