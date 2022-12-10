package com.github.egalli64.snake.view.swing;

import com.github.egalli64.snake.ctrl.Command;
import com.github.egalli64.snake.ctrl.Controller;
import com.github.egalli64.snake.ctrl.Response;
import com.github.egalli64.snake.model.Position;
import com.github.egalli64.snake.view.Id;
import com.github.egalli64.snake.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public class SnakeView extends JPanel implements View {
    private static final Logger log = LoggerFactory.getLogger(SnakeView.class);
    private final Tile[][] tiles;
    private final SnakeKeyListener keyListener;
    private Controller controller = null;
    private Position head = null;

    public SnakeView(int size) {
        this.tiles = new Tile[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tiles[i][j] = new Tile();
                add(tiles[i][j]);
            }
        }

        this.setLayout(new GridLayout(size, size, 1, 1));
        setFocusable(true);

        this.keyListener = e -> {
            log.trace("Key code: " + e.getKeyCode());
            Command command = Command.byKey(e.getKeyCode());
            controller.put(command);
        };

        this.addKeyListener(keyListener);
    }

    @Override
    public void go(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void show(Response response) {
        log.trace(response.toString());
        switch (response.type()) {
            case FOOD -> {
                Position pos = response.position();
                tiles[pos.i()][pos.j()].setId(Id.FOOD);
            }
            case HEAD -> {
                head = response.position();
                tiles[head.i()][head.j()].setId(Id.HEAD);
            }
            case MOVE_HEAD -> {
                tiles[head.i()][head.j()].setId(Id.BODY);
                head = response.position();
                tiles[head.i()][head.j()].setId(Id.HEAD);
            }
            case MOVE_TAIL -> {
                Position pos = response.position();
                tiles[pos.i()][pos.j()].setId(Id.EMPTY);
            }
            case TERMINATE -> removeKeyListener(keyListener);
        }
    }
}
