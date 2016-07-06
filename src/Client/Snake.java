package Client;

import Common.Apple;

import Common.Field;
import Server.ServerSnake;

import java.util.Arrays;

/**
 * Created by Иван on 05.07.2016.
 */
public class Snake {
    int score = 0;
    int[][] Ar;
    int size;  // Длинна змеи
    boolean isAlive = false; // Статус змеи
    int headY; // Координаты головы змеи
    int headX; //
    int metka; // Цвет змеи
    private SnakeDirection direction;
    Field matrix;
    public Apple apple;
    boolean b = true;


    public SnakeDirection getDirection() {
        return direction;
    }

    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }

    public Snake(int metka, Field field) {
        this.metka = metka;
        matrix = field;
        size = 0;
        Ar = new int[100][2];
        searchMetka();
        checkApple(field);
        direction = SnakeDirection.UP;

    }

    public Field move(Field field) {

        matrix = new Field(ServerSnake.HEIGHT, ServerSnake.WIDTH);
        matrix.setMetka(metka);
        if (direction.equals(direction.LEFT)) {


            left();


        } else if (direction.equals(direction.RIGHT)) {


            right();


        } else if (direction.equals(direction.UP)) {


            up();


        } else if (direction.equals(direction.DOWN)) {


            down();


        }
        eatApple(field);
        fillField();
        return matrix;

    }

    public void searchMetka() { // Поиск меток в матрице
        for (int y = 0; y < ServerSnake.HEIGHT; y++) {
            for (int x = 0; x < ServerSnake.WIDTH; x++) {
                if (matrix.getMatrix()[y][x] == metka) {
                    size++;
                    Ar[size - 1][0] = y;
                    Ar[size - 1][1] = x;
                    if (!isAlive) {
                        headY = y;
                        headX = x;
                        isAlive = true;
                    }
                }
            }
        }
    }

    public void moveBody() { // Передвижение тела змеи

        for (int t, i = size - 1; i > 0; i--) {

            t = Ar[i - 1][0];
            Ar[i][0] = t;
            t = Ar[i - 1][1];
            Ar[i][1] = t;

        }
    }

    public void fillField() { // Заполнение матрицы для сервера
        for (int i = 0; i < size; i++) {
            matrix.getMatrix()[Ar[i][0]][Ar[i][1]] = metka;
        }
    }

    public void up() {
        moveBody();
        if (--headY < 0) isAlive = false;
        Ar[0][0] = headY;
        Ar[0][1] = headX;
        matrix.getMatrix()[headY][headX] = metka;

    }

    public void down() {
        moveBody();
        if (++headY >= ServerSnake.HEIGHT) isAlive = false;
        Ar[0][0] = headY;
        Ar[0][1] = headX;
        matrix.getMatrix()[headY][headX] = metka;

    }

    public void left() {
        moveBody();
        if (--headX < 0) isAlive = false;
        Ar[0][0] = headY;
        Ar[0][1] = headX;
        matrix.getMatrix()[headY][headX] = metka;

    }

    public void right() {
        moveBody();
        if (++headX >= ServerSnake.WIDTH) isAlive = false;
        Ar[0][0] = headY;
        Ar[0][1] = headX;
        matrix.getMatrix()[headY][headX] = metka;

    }

    public void checkApple(Field field) {

        for (int y = 0; y < ServerSnake.HEIGHT; y++) {
            for (int x = 0; x < ServerSnake.WIDTH; x++) {
                if (field.getMatrix()[y][x] == Apple.metka) {
                    if(!b) {

                        if (apple.y == y && apple.x == x) {

                        } else {
                            apple = new Apple(y, x);
                            return;
                        }
                    } else {
                        apple = new Apple(y, x);
                        b = false;
                        return;
                    }
                }
            }
        }

    }

    public void eatApple(Field field) {
        checkApple(field);
        if ((apple.y == headY) && (apple.x == headX)) {
            size++;
            score += apple.point;
            apple = new Apple(matrix);
        } else {
            matrix.getMatrix()[apple.y][apple.x] = Apple.metka;
        }
    }

}
