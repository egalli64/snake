package com.github.egalli64.snake.view.swing;

import com.github.egalli64.snake.view.Id;
import org.tinylog.Logger;

import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {
    private static final int SIZE = 50;

    private Id id;

    public Tile() {
        this.id = Id.EMPTY;

        setPreferredSize(new Dimension(SIZE, SIZE));
        setFocusable(true);
    }

    public void setId(Id id) {
        this.id = id;
        Logger.trace(String.format("Reset [%d,%d,%d]", id.color().getRed(), id.color().getGreen(), id.color().getBlue()));
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(id.color());
        g.fillRect(0, 0, SIZE, SIZE);
    }

    @Override
    public String toString() {
        return "Tile{id=" + id + '}';
    }
}
