package com.example.snake.model;

import com.example.snake.ctrl.Command;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private final int N_ROWS;
    private final int N_COLS;

    private Set<Position> availables;
    private Snake snake;

    public Board(int n_rows, int n_cols) {
        N_ROWS = n_rows;
        N_COLS = n_cols;

        availables = new HashSet<Position>(N_ROWS * N_COLS);

        for (int i = 0; i < N_ROWS; i++) {
            for (int j = 0; j < N_COLS; j++) {
                availables.add(new Position(i, j));
            }
        }

        // random direction
        Direction[] directions = Direction.values();
        Direction direction = directions[ThreadLocalRandom.current().nextInt(directions.length)];

        // random head
        Iterator<Position> it = availables.iterator();
        for (int i = 0, j = ThreadLocalRandom.current().nextInt(N_ROWS * N_COLS - 1); i < j; i++) {
            it.next();
        }
        Position head = it.next();
        availables.remove(head);

        snake = new Snake(head, direction, Math.min(N_COLS, N_ROWS) / 2);
        while (snake.hasToGrow()) {
            int i = head.i();
            int j = head.j();
            switch (direction) {
                case LEFT -> j = j == 0 ? N_COLS - 1 : j - 1;
                case RIGHT -> j = j == N_COLS - 1 ? 0 : j + 1;
                case UP -> i = i == 0 ? N_ROWS - 1 : i - 1;
                case DOWN -> i = i == N_ROWS - 1 ? 0 : i + 1;
            }

            head = new Position(i, j);
            availables.remove(head);
            this.snake.grow(head);
        }
    }

    @Override
    public String toString() {
        return "Data [" + snake + "]";
    }

    public boolean nextStep(Command command) {
        return true;
    }
}
