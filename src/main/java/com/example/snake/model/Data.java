package com.example.snake.model;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Data {
    private final int N_ROWS;
    private final int N_COLS;

    private Set<Position> availables;
    private Snake snake;

    public Data(int n_rows, int n_cols) {
        this.N_ROWS = n_rows;
        this.N_COLS = n_cols;

        this.availables = new HashSet<Position>(N_ROWS * N_COLS);

        for (int i = 0; i < N_ROWS; i++) {
            for (int j = 0; j < N_ROWS; j++) {
                availables.add(new Position(i, j));
            }
        }

        Direction[] directions = Direction.values();
        Direction direction = directions[ThreadLocalRandom.current().nextInt(directions.length)];
        Position head = availables.iterator().next();
        availables.remove(head);

        this.snake = new Snake(head, direction);
    }

    @Override
    public String toString() {
        return "Data [" + snake + "]";
    }
}
