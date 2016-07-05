package Server;

import Common.Field;
import Common.Test;

import java.util.List;

/**
 * Created by Timur on 04.07.2016.
 */
public class ClientFieldCombiner
{
    private int[][] matrixCommon;
    private Field serverField;

    public ClientFieldCombiner(int quantity)
    {
        matrixCommon = new int[ServerSnake.HEIGHT][ServerSnake.WIDTH];
        serverField = new Field(ServerSnake.HEIGHT,ServerSnake.WIDTH);
        init(quantity);
    }

    private Field init(int quantity)
    {
        int[][] matrix = new int[ServerSnake.HEIGHT][ServerSnake.WIDTH];
        for (int i = 1; i < quantity + 1; i++)
        {
            matrix[ServerSnake.HEIGHT / 2][ServerSnake.WIDTH * i / (quantity + 1)] = i+4;
            matrix[1 + ServerSnake.HEIGHT / 2][ServerSnake.WIDTH * i / (quantity + 1)] = i+4;
            matrix[2 + ServerSnake.HEIGHT / 2][ServerSnake.WIDTH * i / (quantity + 1)] = i+4;
        }

        serverField.setMatrix(matrix);

        Test.test(serverField);

        return serverField;
    }

    public Field combine(List<Field> fields)
    {
        Field serverField2 = new Field(ServerSnake.HEIGHT,ServerSnake.WIDTH);
        matrixCommon = new int[ServerSnake.HEIGHT][ServerSnake.WIDTH];

        for (int y = 0; y < ServerSnake.HEIGHT; y++)
        {
            for (int x = 0; x < ServerSnake.WIDTH; x++)
            {
                for (Field field : fields)
                {
                    if (field.getMatrix()[y][x] == field.getMetka())
                    {
                        matrixCommon[y][x] = field.getMetka();
                    }
                }
            }
        }

        serverField2.setMatrix(matrixCommon);
        serverField2.setMetka(666);

        return serverField2;
    }

    public Field getServerField()
    {
        return serverField;
    }
}
