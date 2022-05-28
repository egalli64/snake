package com.example.snake.model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ThreadLocalRandom;

public class Snake {
    private Deque<Position> body;
    private Direction direction;
    private int size;

    public Snake(Position head, int size) {
        this.body = new ArrayDeque<Position>(0);
        this.body.add(head);

        // random direction
        Direction[] directions = Direction.values();
        this.direction = directions[ThreadLocalRandom.current().nextInt(directions.length)];
        this.size = size;
    }

    public boolean hasToGrow() {
        return body.size() < size;
    }

    public void grow(Position head) {
        body.addFirst(head);
    }

    public Direction getDirection() {
        return direction;
    }

    public Direction towards(Direction direction) {
        if (direction != this.direction && !direction.isOpposite(this.direction)) {
            this.direction = direction;
        }

        return this.direction;
    }

    @Override
    public String toString() {
        return "Snake{ body=" + body + ", direction=" + direction + ", size=" + size + '}';
    }
}
