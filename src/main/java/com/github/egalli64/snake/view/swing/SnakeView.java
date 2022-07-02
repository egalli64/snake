package com.github.egalli64.snake.view.swing;

import com.github.egalli64.snake.ctrl.Command;
import com.github.egalli64.snake.ctrl.Controller;
import com.github.egalli64.snake.ctrl.Response;
import com.github.egalli64.snake.model.Position;
import com.github.egalli64.snake.model.Snake;
import com.github.egalli64.snake.view.Id;
import com.github.egalli64.snake.view.View;
import org.tinylog.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class SnakeView extends JPanel implements View {
    static final int TILE_SIZE = 50;

    private final SnakeKeyListener keyListener;
    private Controller controller = null;
    private Position food = null;
    private Snake snake = null;

    public SnakeView(int size) {
        setPreferredSize(new Dimension(TILE_SIZE * size, TILE_SIZE * size));
        setFocusable(true);

        this.keyListener = e -> {
            Logger.trace("Key code: " + e.getKeyCode());
            Command command = Command.byKey(e.getKeyCode());
            controller.put(command);
        };

        this.addKeyListener(keyListener);
    }

    @Override
    public void paintComponent(Graphics g) {
        Logger.trace("enter");
        super.paintComponent(g);

        if (food != null) {
            g.setColor(Id.FOOD.color());
            g.fillOval(food.j() * TILE_SIZE, food.i() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        if (snake != null) {
            Iterator<Position> it = snake.iterator();

            g.setColor(Id.HEAD.color());
            do {
                Position cur = it.next();
                g.fillRect(cur.j() * TILE_SIZE, cur.i() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                g.setColor(Id.BODY.color());
            } while (it.hasNext());
        }
    }

    @Override
    public void go(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void show(Response response) {
        Logger.trace(response);
        if (response.food() != null) {
            food = response.food();
        }
        if (response.snake() != null) {
            snake = response.snake();
        } else {
            removeKeyListener(keyListener);
        }
        repaint();
    }
}
