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
    private static final int MIN_SIZE = 10;
    private static final int MAX_SIZE = 30;

    /**
     * By default, swing MIN_SIZE board
     *
     * @param args mode (plain/swing), board size
     */
    public static void main(String[] args) {
        Mode mode = args.length > 0 && args[0].equalsIgnoreCase("PLAIN") ? Mode.PLAIN : Mode.SWING;
        int size = MIN_SIZE;
        if (args.length > 1) {
            try {
                int candidate = Integer.parseInt(args[1]);
                if(candidate < MIN_SIZE || candidate > MAX_SIZE) {
                    System.out.printf("Size should be in [%d..%d]", MIN_SIZE, MAX_SIZE);
                    return;
                }
                size = candidate;
            } catch (NumberFormatException e) {
                Logger.warn("Ignoring second program argument [" + args[1] + "]");
            }
        }

        Logger.trace("Enter in " + mode + " mode and board size " + size);
        View view = switch (mode) {
            case PLAIN -> new BareView(size);
            case SWING -> new SwingView(size);
        };

        Controller controller = new Controller(size, view);
        Thread t = new Thread(controller);
        t.start();

        view.go(controller);

        try {
            t.join();
            Logger.trace("Done");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
