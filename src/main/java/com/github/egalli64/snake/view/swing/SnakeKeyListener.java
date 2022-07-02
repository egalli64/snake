package com.github.egalli64.snake.view.swing;

import java.awt.event.KeyEvent;

@FunctionalInterface
public interface SnakeKeyListener extends java.awt.event.KeyListener {
    @Override
    default void keyReleased(KeyEvent e) {
    }

    @Override
    default void keyTyped(KeyEvent e) {
    }
}
