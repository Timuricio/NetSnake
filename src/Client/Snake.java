package Client;

import Common.Field;
import Server.ServerSnake;

import java.util.Arrays;

/**
 * Created by Иван on 05.07.2016.
 */
public class Snake {
    int[][] Ar;
    int size = 0;  // Длинна змеи
    boolean isAlive = false; // Статус змеи
    int headY; // Координаты головы змеи
    int headX; //
    int metka; // Цвет змеи
    private SnakeDirection direction;
    Field matrix;


    public SnakeDirection getDirection() {
        return direction;
    }

    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }

    public Snake(int metka, Field field) {
        this.metka = metka;
        matrix = field;
        Ar = new int[100][2];
        searchMetka(matrix);
        direction = SnakeDirection.UP;

    }

    public Field move() {

        matrix = new Field(ServerSnake.HEIGHT, ServerSnake.WIDTH);
        fillField();
        if (direction.equals(direction.LEFT)) {


            left();

        } else if (direction.equals(direction.RIGHT)) {


            right();


        } else if (direction.equals(direction.UP)) {


            up();


        } else if (direction.equals(direction.DOWN)) {

            down();


        }

        return matrix;

    }

    public void searchMetka(Field field) { // Поиск меток в матрице
        for (int y = 0; y < ServerSnake.HEIGHT; y++) {
            for (int x = 0; x < ServerSnake.WIDTH; x++) {
                if (field.getMatrix()[y][x] == metka) {
                    Ar[y][x] = metka;
                    size++;
                    if (!isAlive) {
                        headY = y;
                        headX = x;
                        isAlive = true;
                    }
                }
            }
        }
    }

    public void moveBody() {

        for (int t, i = size - 1; i > 0; i--) {

            t = Ar[i - 1][0];
            Ar[i][0] = t;
            t = Ar[i - 1][1];
            Ar[i][1] = t;

        }
    }

    public void fillField() {
        for (int i = 0; i < size; i++) {
            matrix.getMatrix()[Ar[i][0]][Ar[i][1]] = 1;
        }
    }

    public void up() {
        moveBody();
        if(--headY < 0) isAlive = false;

    }

    public void down() {
        moveBody();
        if(++headY >= matrix.getMatrix().length) isAlive = false;

    }

    public void left() {
        moveBody();
        if(--headX < 0) isAlive = false;

    }

    public void right() {
        moveBody();
        if(++headX >= matrix.getMatrix().length) isAlive = false;

    }

}
