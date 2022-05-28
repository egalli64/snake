package com.example.snake.ctrl;

/**
 * The game commands
 */
public enum Command {
    UP, LEFT, DOWN, RIGHT, SAME, EXIT;

    /**
     * Convert a shortcut to the associated command, defaulted to "keep the current direction"
     *
     * @param c the command shortcut
     * @return the associated command
     */
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
