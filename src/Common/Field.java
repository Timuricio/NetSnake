package Common;

import java.io.Serializable;

/**
 * Created by Timur on 04.07.2016.
 */
public class Field implements Serializable
{
    private int[][] matrix;
    private int metka=666;
    private int headX,headY;


    public Field( int y,int x)
    {
        matrix = new int[y][x];
    }

    public int[][] getMatrix()
    {
        return matrix;
    }

    public void setMatrix(int[][] matrix)
    {
        this.matrix = matrix;
    }

    public int getMetka()
    {
        return metka;
    }

    public void setMetka(int metka)
    {
        this.metka = metka;
    }



    public int getHeadX()
    {
        return headX;
    }

    public void setHeadX(int headX)
    {
        this.headX = headX;
    }

    public int getHeadY()
    {
        return headY;
    }

    public void setHeadY(int headY)
    {
        this.headY = headY;
    }
}
