package com.example.snake.model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The game main character
 */
public class Snake {
    private final Deque<Position> body;
    private Direction direction;
    private final int size;

    public Snake(Position head, int size) {
        this.body = new ArrayDeque<>(0);
        this.body.add(head);

        // random direction
        Direction[] directions = Direction.values();
        this.direction = directions[ThreadLocalRandom.current().nextInt(directions.length)];
        this.size = size;
    }

    /**
     * Let access to the snake head
     *
     * @return the snake head
     */
    public Position getHead() {
        return body.getFirst();
    }

    /**
     * When the snake eats, then has to grow
     *
     * @return true if the snake is underdeveloped
     */
    public boolean hasToGrow() {
        return body.size() < size;
    }

    /**
     * Let the snake grow
     *
     * @param head its new head
     */
    public void grow(Position head) {
        body.addFirst(head);
    }

    /**
     * The current snake direction
     *
     * @return the current snake direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Set a new direction, when required, than return it
     *
     * @param direction the possibly new direction
     * @return the actual direction
     */
    public Direction towards(Direction direction) {
        if (direction != this.direction && !direction.isOpposite(this.direction)) {
            this.direction = direction;
        }

        return this.direction;
    }

    public Position move(Position head) {
        grow(head);
        return body.removeLast();
    }

    @Override
    public String toString() {
        return "Snake{ body=" + body + ", direction=" + direction + ", size=" + size + '}';
    }
}
