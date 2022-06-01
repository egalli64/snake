package com.github.egalli64.snake.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@FunctionalInterface
public interface SwingKeyListener extends KeyListener {
    @Override
    default void keyReleased(KeyEvent e) {
    }

    @Override
    default void keyTyped(KeyEvent e) {
    }
}
