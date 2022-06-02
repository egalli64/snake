package com.github.egalli64.snake.view;

import com.github.egalli64.snake.ctrl.Controller;
import com.github.egalli64.snake.ctrl.Response;
import com.github.egalli64.snake.model.Position;
import org.tinylog.Logger;

import javax.swing.*;
import java.awt.*;

public class SwingView extends JPanel implements View {
    static final int SIZE = 500;
    static final int TILE_SIZE = 50;

    private Position food = null;
    private Position body = null;

    public SwingView(int size) {
        setPreferredSize(new Dimension(SIZE, SIZE));
    }

    @Override
    public void paintComponent(Graphics g) {
        Logger.trace("enter");
        super.paintComponent(g);

        if (food != null) {
            g.setColor(Color.red);
            g.fillOval(food.j() * TILE_SIZE, food.i() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        if (body != null) {
            g.setColor(Color.blue);
            g.fillRect(body.j() * TILE_SIZE, body.i() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
    }

    @Override
    public void go(Controller controller) {
        new SwingFrame(this, controller);
    }

    @Override
    public void show(Response response) {
        Logger.trace(response);
        if (response.food() != null) {
            food = response.food();
        }
        if (response.head() != null) {
            body = response.head();
        }
        repaint();
    }
}
