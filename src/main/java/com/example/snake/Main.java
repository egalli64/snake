package com.example.snake;

import com.example.snake.ctrl.Controller;
import com.example.snake.view.BareView;
import com.example.snake.view.View;
import org.tinylog.Logger;

/**
 * Snake entry point
 */
public class Main {
    private static final int DEFAULT_SIZE = 10;

    public static void main(String[] args) {
        Logger.trace("Enter");
        View view = new BareView();
        Controller controller = new Controller(DEFAULT_SIZE, view);
        controller.go();
        Logger.trace("Done");
    }
}
