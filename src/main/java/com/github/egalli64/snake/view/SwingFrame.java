package com.github.egalli64.snake.view;

import javax.swing.*;

public class SwingFrame extends JFrame {
    public SwingFrame(SwingView panel) {
        super("Snake");

        add(panel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
