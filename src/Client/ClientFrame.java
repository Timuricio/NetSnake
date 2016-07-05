package Client;

import Server.ServerSnake;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Timur on 04.07.2016.
 */
public class ClientFrame extends JFrame
{
    private JLabel userName,score,time;
    private Pixel[][] pixels;

    public ClientFrame() throws HeadlessException
    {
        super("ClientNetSnake");
        initComponents();
        userName.setText(requestUserName());
    }

    private String requestUserName()
    {
        String name = JOptionPane.showInputDialog(this, "Enter the user name", "Options", JOptionPane.QUESTION_MESSAGE);

        if (name == null||name.isEmpty())
            name = "NoName";

        return name;
    }

    private void initComponents(){
        setPreferredSize(new Dimension(830,475));
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setDefaultLookAndFeelDecorated(true);

        setLayout(null);

        userName = new JLabel("Name - ########");
        userName.setBounds(5, 5, 200, 20);
        getContentPane().add(userName);

        score = new JLabel("Score - 0");
        score.setBounds(210,5,200,20);
        getContentPane().add(score);

        time = new JLabel("Time - 0");
        time.setBounds(415,5,200,20);
        getContentPane().add(time);

        paintField();

        pack();
        setVisible(true);
    }

    private void paintField()
    {
        pixels = new Pixel[ServerSnake.WIDTH][ServerSnake.HEIGHT];

        for (int x = 0; x < ServerSnake.WIDTH; x++)
        {
            for (int y = 0; y < ServerSnake.HEIGHT; y++)
            {
                pixels[x][y] = new Pixel(Color.decode("#D0D8F6"));

                pixels[x][y].setBounds(5 + x * Pixel.W, 30 + y * Pixel.H, Pixel.W, Pixel.H);
                getContentPane().add(pixels[x][y]);
            }
        }

    }

    public void repaintField(int[][] matrix)
    {
        for (int x = 0; x < ServerSnake.WIDTH; x++)
        {
            for (int y = 0; y < ServerSnake.HEIGHT; y++)
            {
                if (matrix[x][y] != 1)
                {
                    pixels[x][y].setBackground(Color.decode("#D0D8F6"));
                } else
                {
                    pixels[x][y].setBackground(Color.BLACK);
                }

                getContentPane().repaint();
            }
        }

    }

    public JLabel getUserName()
    {
        return userName;
    }

    public void setUserName(JLabel userName)
    {
        this.userName = userName;
    }

    public JLabel getScore()
    {
        return score;
    }

    public void setScore(JLabel score)
    {
        this.score = score;
    }

    public JLabel getTime()
    {
        return time;
    }

    public void setTime(JLabel time)
    {
        this.time = time;
    }

    public Pixel[][] getPixels()
    {
        return pixels;
    }

    public void setPixels(Pixel[][] pixels)
    {
        this.pixels = pixels;
    }
}
