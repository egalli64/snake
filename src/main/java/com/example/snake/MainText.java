package com.example.snake;

import com.example.snake.ctrl.TextualController;

import org.tinylog.Logger;

public class MainText {
    private static final int DEFAULT_N_ROWS = 10;
    private static final int DEFAULT_N_COLS = 10;

    public static void main(String[] args) {
        Logger.trace("Enter");
        TextualController controller = new TextualController(DEFAULT_N_ROWS, DEFAULT_N_COLS);
        controller.go();
        Logger.trace("Done");
    }
}
