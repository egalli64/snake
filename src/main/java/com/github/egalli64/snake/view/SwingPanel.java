package com.github.egalli64.snake.view;

import org.tinylog.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingPanel extends JPanel implements ActionListener {
    static final int SIZE = 500;

    public SwingPanel(int size) {
        setPreferredSize(new Dimension(SIZE, SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Logger.trace(e);
        repaint();
    }
}
