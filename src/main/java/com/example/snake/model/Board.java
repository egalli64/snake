package com.example.snake.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The snake lives on this board
 */
public class Board {
    private final int size;

    private Set<Position> positions;

    public Board(int size) {
        this.size = size;

        positions = new HashSet<>(size * size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                positions.add(new Position(i, j));
            }
        }
    }

    @Override
    public String toString() {
        return "Board{positions=" + positions + '}';
    }

    /**
     * Remove a random position among the available ones and return it
     *
     * @return a not yet used random position in the board
     */
    public Position pop() {
        Iterator<Position> it = positions.iterator();
        for (int i = 0, j = ThreadLocalRandom.current().nextInt(positions.size() - 1); i < j; i++) {
            it.next();
        }

        Position result = it.next();
        positions.remove(result);
        return result;
    }

    /**
     * Remove the specified position from the available ones and return it
     *
     * @param position a position
     * @param direction where to get the result from the passed position
     * @return a good position or empty
     */
    public Optional<Position> pop(Position position, Direction direction) {
        int i = position.i();
        int j = position.j();
        switch (direction) {
            case LEFT -> j = j == 0 ? size - 1 : j - 1;
            case RIGHT -> j = j == size - 1 ? 0 : j + 1;
            case UP -> i = i == 0 ? size - 1 : i - 1;
            case DOWN -> i = i == size - 1 ? 0 : i + 1;
        }

        Position result = new Position(i, j);
        return positions.remove(result) ? Optional.of(result) : Optional.empty();
    }
}
