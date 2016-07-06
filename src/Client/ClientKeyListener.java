package Client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Timur on 06.07.2016.
 */
public class ClientKeyListener implements KeyListener
{
    Snake snake;

    public ClientKeyListener(Snake snake)
    {
        this.snake = snake;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && !snake.getDirection().equals(SnakeDirection.RIGHT))
            snake.setDirection(SnakeDirection.LEFT);
            //Если "стрелка вправо" - сдвинуть фигурку вправо
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && !snake.getDirection().equals(SnakeDirection.LEFT))
            snake.setDirection(SnakeDirection.RIGHT);
            //Если "стрелка вверх" - сдвинуть фигурку вверх
        else if (e.getKeyCode() == KeyEvent.VK_UP && !snake.getDirection().equals(SnakeDirection.DOWN))
            snake.setDirection(SnakeDirection.UP);
            //Если "стрелка вниз" - сдвинуть фигурку вниз
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && !snake.getDirection().equals(SnakeDirection.UP))
            snake.setDirection(SnakeDirection.DOWN);

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
