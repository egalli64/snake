package com.github.egalli64.snake.model;

import com.github.egalli64.snake.ctrl.Command;

/**
 * The selectable directions
 */
public enum Direction {
    UP, LEFT, DOWN, RIGHT;

    /**
     * Convert a command to a direction, if possible
     *
     * @param command the user command
     * @return the associated direction
     * @throws IllegalStateException if command is not a direction
     */
    public static Direction from(Command command) {
        return switch (command) {
            case UP -> UP;
            case LEFT -> LEFT;
            case DOWN -> DOWN;
            case RIGHT -> RIGHT;
            default -> throw new IllegalStateException("Unexpected value: " + command);
        };
    }

    /**
     * Compare the current direction with other
     *
     * @param other a direction
     * @return false is same orientation, opposite sense
     */
    public boolean isOpposite(Direction other) {
        return switch (other) {
            case UP -> this.equals(DOWN);
            case LEFT -> this.equals(RIGHT);
            case DOWN -> this.equals(UP);
            case RIGHT -> this.equals(LEFT);
        };
    }
}
