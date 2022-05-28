package com.example.snake.ctrl;

public enum Command {
    UP, LEFT, DOWN, RIGHT, SAME, EXIT;

    public static Command byShortcut(char c) {
        return switch (c) {
            case 'u' -> UP;
            case 'l' -> LEFT;
            case 'd' -> DOWN;
            case 'r' -> RIGHT;
            case 'x' -> EXIT;
            default -> SAME;
        };
    }
}
