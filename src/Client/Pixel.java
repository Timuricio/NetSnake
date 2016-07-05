package Client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Timur on 01.07.2016.
 */
public class Pixel extends JPanel
{
    static final int W = 10, H = 10;

    public Pixel(Color color) throws HeadlessException
    {
        this.setSize(W, H);
        this.setBackground(color);
    }
}
