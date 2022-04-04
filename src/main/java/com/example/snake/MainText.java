package com.example.snake;

import org.tinylog.Logger;

import com.example.snake.view.Textual;

public class MainText {
    private static final int DEFAULT_N_ROWS = 10;
    private static final int DEFAULT_N_COLS = 10;

    public static void main(String[] args) {
        Logger.trace("Enter");
        new Textual(DEFAULT_N_ROWS, DEFAULT_N_COLS);
    }
}
