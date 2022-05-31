package com.github.egalli64.snake;

import com.github.egalli64.snake.ctrl.Controller;
import com.github.egalli64.snake.view.BareView;
import com.github.egalli64.snake.view.SwingView;
import com.github.egalli64.snake.view.View;
import org.tinylog.Logger;

/**
 * Snake entry point
 */
public class Main {
    private static final int DEFAULT_SIZE = 10;

    public static void main(String[] args) {
        Logger.trace("Enter");
        View view = new BareView();
//        View view = new SwingView(DEFAULT_SIZE);
        Controller controller = new Controller(DEFAULT_SIZE, view);
        controller.go();
        Logger.trace("Done");
    }
}
