package com.github.egalli64.snake.view;

import com.github.egalli64.snake.ctrl.Command;
import com.github.egalli64.snake.ctrl.Controller;
import org.tinylog.Logger;

import javax.swing.*;

public class SwingFrame extends JFrame {
    public SwingFrame(SwingView panel, Controller controller) {
        super("Snake");
        add(panel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        this.addKeyListener((SwingKeyListener) e -> {
            Logger.trace("Key code: " + e.getKeyCode());
            Command command = Command.byKey(e.getKeyCode());
            controller.put(command);
        });
    }
}
