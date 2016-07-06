package Common;

import Server.ServerSnake;

import java.awt.*;

/**
 * Created by Timur on 05.07.2016.
 */
public class Test
{
    public static void test(Field field)
    {
        int[][]matrix = field.getMatrix();

        for (int y = 0; y < ServerSnake.HEIGHT; y++)
        {
            for (int x = 0; x < ServerSnake.WIDTH; x++)
            {
                System.out.print(matrix[y][x]);
            }
            System.out.println();
        }
    }
}
