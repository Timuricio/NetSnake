package Client;

import java.io.Serializable;

/**
 * Created by Timur on 04.07.2016.
 */
public class ClientField implements Serializable
{
    private int[][] matrix;

    public ClientField(int x, int y)
    {
        matrix = new int[x][y];
    }

    public int[][] getMatrix()
    {
        return matrix;
    }

    public void setMatrix(int[][] matrix)
    {
        this.matrix = matrix;
    }
}
