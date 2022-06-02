package com.github.egalli64.snake.view;

import com.github.egalli64.snake.ctrl.Command;
import com.github.egalli64.snake.ctrl.Controller;
import com.github.egalli64.snake.ctrl.Response;
import org.tinylog.Logger;

import javax.swing.*;

public class SwingView extends JFrame implements View {
    private Controller controller;

    public SwingView(int size) {
        super("Snake");

        add(new SwingPanel(size));
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

    @Override
    public void go(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void show(Response response) {
        Logger.trace(response);
    }
}
