package com.example.snake.model;

import com.example.snake.ctrl.Command;

public enum Direction {
    UP, LEFT, DOWN, RIGHT;

    public static Direction from(Command command) {
        return switch (command) {
            case UP -> UP;
            case LEFT -> LEFT;
            case DOWN -> DOWN;
            case RIGHT -> RIGHT;
            default -> throw new IllegalStateException("Unexpected value: " + command);
        };
    }

    public boolean isOpposite(Direction other) {
        return switch (other) {
            case UP -> this.equals(DOWN);
            case LEFT -> this.equals(RIGHT);
            case DOWN -> this.equals(UP);
            case RIGHT -> this.equals(LEFT);
        };
    }
}
