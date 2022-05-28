package com.example.snake;

import com.example.snake.ctrl.Controller;
import com.example.snake.view.Skin;
import org.tinylog.Logger;

/**
 * Snake entry point
 */
public class Main {
    private static final int DEFAULT_SIZE = 10;

    public static void main(String[] args) {
        Logger.trace("Enter");
        Controller controller = new Controller(DEFAULT_SIZE, Skin.CLI);
        controller.go();
        Logger.trace("Done");
    }
}
