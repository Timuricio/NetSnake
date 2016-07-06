package Server;

import Common.Field;

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
        return serverField;
    }

    public Field combine(List<Field> fields)
    {
        Field serverField = new Field(ServerSnake.HEIGHT,ServerSnake.WIDTH);
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

        serverField.setMatrix(matrixCommon);
        serverField.setMetka(666);

        return serverField;
    }

    public Field getServerField()
    {
        return serverField;
    }
}
