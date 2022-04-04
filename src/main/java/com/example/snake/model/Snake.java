package com.example.snake.model;

import java.util.ArrayDeque;
import java.util.Deque;

public class Snake {
    private Deque<Position> body;
    private Direction direction;

    public Snake(Position head, Direction direction) {
        this.body = new ArrayDeque<Position>(0);
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Snake [body=" + body + ", direction=" + direction + "]";
    }
}
