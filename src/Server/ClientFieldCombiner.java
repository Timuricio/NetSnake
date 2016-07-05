package Server;

import Common.Field;

import java.util.List;

/**
 * Created by Timur on 04.07.2016.
 */
public class ClientFieldCombiner
{
    private int[][] matrixCommon;
    private int quantity;
    private Field serverField;

    public ClientFieldCombiner(int quantity)
    {
        this.quantity = quantity;
        matrixCommon = new int[ServerSnake.HEIGHT][ServerSnake.WIDTH];
        serverField = new Field(ServerSnake.WIDTH,ServerSnake.HEIGHT);
    }

    private Field init(int quantity)
    {
        int[][] matrix = new int[ServerSnake.HEIGHT][ServerSnake.WIDTH];
        for (int i = 1; i < quantity + 1; i++)
        {
            matrix[ServerSnake.HEIGHT / 2][ServerSnake.WIDTH * i / (quantity + 2)] = i;
            matrix[1 + ServerSnake.HEIGHT / 2][ServerSnake.WIDTH * i / (quantity + 2)] = i;
            matrix[2 + ServerSnake.HEIGHT / 2][ServerSnake.WIDTH * i / (quantity + 2)] = i;
        }

        serverField.setMatrix(matrix);

        return serverField;
    }

    public Field combine(List<Field> fields)
    {
        for (int y = 0; y < ServerSnake.HEIGHT; y++)
        {
            for (int x = 0; x < ServerSnake.WIDTH; x++)
            {
                for (Field field : fields)
                {
                    if (field.getMatrix()[y][x] == 1)
                    {
                        matrixCommon[y][x] = 1;
                        continue;
                    }
                }
            }
        }

        serverField.setMatrix(matrixCommon);

        return serverField;
    }

    public Field getServerField()
    {
        return serverField;
    }
}
