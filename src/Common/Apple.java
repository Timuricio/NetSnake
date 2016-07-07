package Common;

import Server.ServerSnake;

import java.util.Random;

/**
 * Created by Timur on 06.07.2016.
 */
public class Apple
{

    public static final int point = 200;
    public int y, x;
    public Random random = new Random();
    public static int metka = 1;


    public Apple(Field field) {



        do {
            y = random.nextInt(ServerSnake.HEIGHT - 1);
            x = random.nextInt(ServerSnake.WIDTH - 1);
        } while (field.getMatrix()[y][x] != 0);

        field.getMatrix()[y][x] = metka;

    }
    public Apple(int y, int x) {
        this.y = y;
        this.x = x;

    }

    public Apple(Field field, int y, int x) {
        this.y = y;
        this.x = x;
        field.getMatrix()[y][x] = metka;
    }

    public void endApple(Field field) {
        field.getMatrix()[y][x] = 0;
    }

}
