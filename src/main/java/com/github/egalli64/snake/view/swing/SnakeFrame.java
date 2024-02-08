package com.github.egalli64.snake.view.swing;

import javax.swing.*;

@SuppressWarnings("serial")
public class SnakeFrame extends JFrame {
    public SnakeFrame(SnakeView panel) {
        super("Snake");

        add(panel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
