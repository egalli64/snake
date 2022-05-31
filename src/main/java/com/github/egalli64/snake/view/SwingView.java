package com.github.egalli64.snake.view;

import com.github.egalli64.snake.ctrl.Controller;

import javax.swing.*;
import java.awt.*;

public class SwingView extends JFrame implements View {
    private final int size;

    public SwingView(int size) {
        super("Snake");

        this.size = size;
        setSize(30 * size, 30 * size);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new GridLayout(size, size, 0, 0));
    }

    @Override
    public void go(Controller controller) {
    }
}
