package Client;

import Common.Field;

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

        matrix = new Field(matrix.getMatrix().length, matrix.getMatrix().length);
        fillField();
        // some code

        return matrix;

    }

    public void searchMetka(Field field) { // Поиск меток в матрице
        for (int y = 0; y < field.getMatrix().length; y++) {
            for (int x = 0; x < field.getMatrix().length; x++) {
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
        if(--headY < 0) isAlive = false;

        moveBody();
    }

    public void down() {
        if(++headY >= matrix.getMatrix().length) isAlive = false;

        moveBody();
    }

    public void left() {
        if(--headX < 0) isAlive = false;

        moveBody();
    }

    public void right() {
        if(++headX >= matrix.getMatrix().length) isAlive = false;

        moveBody();
    }

}
