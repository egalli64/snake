package com.github.egalli64.snake.ctrl;

import java.awt.event.KeyEvent;

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

    /**
     * Convert a key code to the associated command, defaulted to "keep the current direction"
     *
     * @param key the command shortcut
     * @return the associated command
     */
    public static Command byKey(int key) {
        return switch (key) {
            case KeyEvent.VK_UP -> UP;
            case KeyEvent.VK_LEFT -> LEFT;
            case KeyEvent.VK_DOWN -> DOWN;
            case KeyEvent.VK_RIGHT -> RIGHT;
            case KeyEvent.VK_X -> EXIT;
            default -> SAME;
        };
    }
}
