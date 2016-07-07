package Server;

import Common.Field;
import Common.Test;

import java.util.List;
import java.util.Random;

/**
 * Created by Timur on 04.07.2016.
 */
public class ClientFieldCombiner
{
    private boolean appleEaten = false;
    private Field serverField;
    private Random random = new Random();
    private int xApple,yApple;

    public ClientFieldCombiner(int quantity)
    {
        serverField = new Field(ServerSnake.HEIGHT, ServerSnake.WIDTH);
        init(quantity);
    }

    private Field init(int quantity)
    {
        int[][] matrix = new int[ServerSnake.HEIGHT][ServerSnake.WIDTH];
        for (int i = 1; i < quantity + 1; i++)
        {
            matrix[ServerSnake.HEIGHT / 2][ServerSnake.WIDTH * i / (quantity + 1)] = i + 4;
            matrix[1 + ServerSnake.HEIGHT / 2][ServerSnake.WIDTH * i / (quantity + 1)] = i + 4;
            matrix[2 + ServerSnake.HEIGHT / 2][ServerSnake.WIDTH * i / (quantity + 1)] = i + 4;
        }
        matrix = generateApple(matrix);
        serverField.setMatrix(matrix);
        return serverField;
    }

    private int[][] generateApple(int[][] m)
    {
        do
        {
            yApple = random.nextInt(ServerSnake.HEIGHT);
            xApple = random.nextInt(ServerSnake.WIDTH);
        }while (m[yApple][xApple]>4);

        m[yApple][xApple] = 1;

        return m;
    }

    public Field combine(List<Field> fields)
    {
        Field serverField = new Field(ServerSnake.HEIGHT, ServerSnake.WIDTH);
        int[][] matrixCommon = new int[ServerSnake.HEIGHT][ServerSnake.WIDTH];

        matrixCommon[yApple][xApple]=1;

        for (int y = 0; y < ServerSnake.HEIGHT; y++)
        {
            for (int x = 0; x < ServerSnake.WIDTH; x++)
            {
                for (Field field : fields)
                {
                    if (field.getMatrix()[y][x] == 1 && !appleEaten)
                    {/*
                        if (matrixCommon[y][x] > 4)
                            appleEaten = true;
                        else*/
                            matrixCommon[y][x] = 1;
                    } else if (field.getMatrix()[y][x] == field.getMetka())
                    {
                        if (matrixCommon[y][x]==1)
                            appleEaten = true;
                        matrixCommon[y][x] = field.getMetka();
                    }
                }
            }
        }

        if (appleEaten)
        {
            matrixCommon = generateApple(matrixCommon);
            appleEaten = false;
        }

        serverField.setMatrix(matrixCommon);

        return serverField;
    }

    public Field getServerField()
    {
        return serverField;
    }
}
