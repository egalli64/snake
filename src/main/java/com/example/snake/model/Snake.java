package com.example.snake.model;

import org.tinylog.Logger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The game main character
 */
public class Snake {
    private final Deque<Position> body;
    private Direction direction;

    public Snake(Position head) {
        this.body = new ArrayDeque<>(0);
        this.body.add(head);

        // random direction
        Direction[] directions = Direction.values();
        this.direction = directions[ThreadLocalRandom.current().nextInt(directions.length)];
    }

    /**
     * @return The current snake size
     */
    public int size() {
        return body.size();
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
     * Let the snake grow
     *
     * @param head its new head
     */
    public void grow(Position head) {
        body.addFirst(head);
        Logger.trace("Now snake size is " + body.size());
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

    /**
     * Move the snake, adding a new head and removing the previous tail
     *
     * @param head the new snake head
     * @return the previous tail
     */
    public Position move(Position head) {
        grow(head);
        return body.removeLast();
    }

    @Override
    public String toString() {
        return "Snake{ body=" + body + ", direction=" + direction + '}';
    }
}
