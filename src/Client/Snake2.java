package Client;

import Common.Field;
import Server.ServerSnake;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Timur on 05.07.2016.
 */
public class Snake2
{
    private List<SnakeSection> snakeSections = new ArrayList<>();
    private int metka;
    private int[][] matrix;
    private SnakeDirection direction;


    private int headX, headY;

    public Snake2(int metka, Field field)
    {
        this.metka = metka;
        matrix = field.getMatrix();
        direction = SnakeDirection.UP;
        searchHead();
    }

    private void searchHead()
    {
        for (int y = 0; y < ServerSnake.HEIGHT; y++)
        {
            for (int x = 0; x < ServerSnake.WIDTH; x++)
            {
                if (matrix[y][x] == metka)
                {
                    snakeSections.add(new SnakeSection(x, y));
                }
            }
        }

        headX = snakeSections.get(0).x;
        headY = snakeSections.get(0).y;
    }

    public Field move()
    {
        matrix = new int[ServerSnake.HEIGHT][ServerSnake.WIDTH];
        Field fieldMove = new Field(ServerSnake.HEIGHT,ServerSnake.WIDTH);

        if (direction.equals(SnakeDirection.UP) )
            headY--;
        else if(direction.equals(SnakeDirection.DOWN) )
            headY++;
        else if(direction.equals(SnakeDirection.LEFT))
            headX--;
        else
            headX++;

        snakeSections.add(0, new SnakeSection(headX, headY));
        snakeSections.remove(snakeSections.size() - 1);

        for (SnakeSection snakeSection : snakeSections)
            matrix[snakeSection.y][snakeSection.x] = metka;

        fieldMove.setMatrix(matrix);
        fieldMove.setMetka(metka);
        return fieldMove;
    }

    private class SnakeSection
    {
        int x, y;

        public SnakeSection(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SnakeSection that = (SnakeSection) o;

            if (x != that.x) return false;
            return y == that.y;

        }

        @Override
        public int hashCode()
        {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    public SnakeDirection getDirection()
    {
        return direction;
    }

    public void setDirection(SnakeDirection direction)
    {
        this.direction = direction;
    }
}
