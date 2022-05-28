package com.example.snake.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private final int N_ROWS;
    private final int N_COLS;

    private Set<Position> positions;

    public Board(int n_rows, int n_cols) {
        N_ROWS = n_rows;
        N_COLS = n_cols;

        positions = new HashSet<>(N_ROWS * N_COLS);

        for (int i = 0; i < N_ROWS; i++) {
            for (int j = 0; j < N_COLS; j++) {
                positions.add(new Position(i, j));
            }
        }
    }

    @Override
    public String toString() {
        return "Board{positions=" + positions + '}';
    }

    public Position pop() {
        Iterator<Position> it = positions.iterator();
        for (int i = 0, j = ThreadLocalRandom.current().nextInt(positions.size() - 1); i < j; i++) {
            it.next();
        }

        Position result = it.next();
        positions.remove(result);
        return result;
    }

    public Optional<Position> pop(Position position, Direction direction) {
        int i = position.i();
        int j = position.j();
        switch (direction) {
            case LEFT -> j = j == 0 ? N_COLS - 1 : j - 1;
            case RIGHT -> j = j == N_COLS - 1 ? 0 : j + 1;
            case UP -> i = i == 0 ? N_ROWS - 1 : i - 1;
            case DOWN -> i = i == N_ROWS - 1 ? 0 : i + 1;
        }

        Position result = new Position(i, j);
        return positions.remove(result) ? Optional.of(result) : Optional.empty();
    }
}
