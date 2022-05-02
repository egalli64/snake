package com.example.snake.model;

import java.util.ArrayDeque;
import java.util.Deque;

public class Snake {
    private Deque<Position> body;
    private Direction direction;
    private int size;

    public Snake(Position head, Direction direction, int size) {
        this.body = new ArrayDeque<Position>(0);
        this.body.add(head);
        this.direction = direction;
        this.size = size;

        move();
    }

    public boolean hasToGrow() {
        return body.size() < size;
    }

    public void move() {
    }

    @Override
    public String toString() {
        return "Snake{ body=" + body + ", direction=" + direction + ", size=" + size + '}';
    }

    public void grow(Position head) {
        body.addFirst(head);
    }
}
