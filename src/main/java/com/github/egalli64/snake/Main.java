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
        Mode mode = Mode.PLAIN;
        if (args.length > 0 && args[0].equalsIgnoreCase("SWING")) {
            mode = Mode.SWING;
        }

        Logger.trace("Enter, mode " + mode);
        View view = switch (mode) {
            case PLAIN -> new BareView();
            case SWING -> new SwingView(DEFAULT_SIZE);
        };
        Controller controller = new Controller(DEFAULT_SIZE, view);

        view.go(controller);
        Logger.trace("Done");
    }
}
