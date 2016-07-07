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
    public Field matrix;
    public Field field;
    public Apple apple;


    public SnakeDirection getDirection() {
        return direction;
    }

    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }

    public Snake(int metka, Field field) {
        this.metka = metka;
        this.field = field;
        size = 0;
        Ar = new int[100][2];
        searchMetka();
        direction = SnakeDirection.UP;

    }

    public Field move(Field field) {
        if (isAlive) {

            this.field = field;
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
            eatApple();
            fillField();
            return matrix;
        }
        return new Field(ServerSnake.HEIGHT, ServerSnake.WIDTH);
    }

    public void searchMetka() { // Поиск меток в матрице
        for (int y = 0; y < ServerSnake.HEIGHT; y++) {
            for (int x = 0; x < ServerSnake.WIDTH; x++) {
                if (field.getMatrix()[y][x] == metka) {
                    size++;
                    Ar[size - 1][0] = y;
                    Ar[size - 1][1] = x;
                    if (!isAlive) {
                        headY = y;
                        headX = x;
                        isAlive = true;
                    }
                } else if (field.getMatrix()[y][x] == Apple.metka) {
                    apple = new Apple(y, x);

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

    public void checkApple() {


        for (int y = 0; y < ServerSnake.HEIGHT; y++) {
            for (int x = 0; x < ServerSnake.WIDTH; x++) {
                if (field.getMatrix()[y][x] == Apple.metka) {

                    apple = new Apple(y, x);


                }

            }
        }

        if ((field.getMatrix()[headY][headX] != 1) && (field.getMatrix()[headY][headX] != 0) && (field.getMatrix()[headY][headX] != metka)) {
            size = 0;
            isAlive = false;
        }

    }

    public void eatApple() {
        checkApple();
        if ((apple.y == headY) && (apple.x == headX)) {
            size++;
            score += apple.point;
            apple.endApple(matrix);
        } else {
            apple.endApple(matrix);
        }
    }

}
