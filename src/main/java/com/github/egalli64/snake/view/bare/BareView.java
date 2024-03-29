package com.github.egalli64.snake.view.bare;

import com.github.egalli64.snake.ctrl.Command;
import com.github.egalli64.snake.ctrl.Controller;
import com.github.egalli64.snake.ctrl.Response;
import com.github.egalli64.snake.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import java.util.Scanner;

/**
 * A bare-bones view for Snake
 */
public class BareView implements View {
    private static final Logger log = LoggerFactory.getLogger(BareView.class);

    /**
     * view is alive until the controller sends a terminator
     */
    private boolean alive;

    public BareView(int size) {
        this.alive = true;
        System.out.println("Snake lives in a square sized " + size);
    }

    /**
     * Wait a response from the controller
     *
     * @return when alive is refreshed concurrently
     */
    synchronized boolean isAlive() {
        try {
            this.wait();
            return alive;
        } catch (InterruptedException e) {
            log.warn("Can't wait", e);
            return false;
        }
    }

    /**
     * Let the user interact with the game from CLI
     *
     * @param controller Snake controller
     */
    @Override
    public void go(Controller controller) {
//         try (Scanner scanner = new Scanner(System.in)) {
        // this block should be a do-while loop
        if (isAlive()) {
            System.out.print("Your direction [l,r,u,d] (or, exit [x] / no change): ");
            String input = "x"; // scanner.nextLine().toLowerCase();
            log.trace("User input: {}", input);
//                Command command = Command.byShortcut(input.isEmpty() ? ' ' : input.charAt(0));
            Command command = Command.byShortcut(input.charAt(0));
            controller.put(command);
        }
//         }

        System.out.println("\nSorry CLI view disabled, maybe to be replaced with some kind of curses library?");
    }

    /**
     * This view acts in synchrony with the controller
     * <p>
     * When the controller invokes this method, the user sees the changes, and then should enter a new command
     *
     * @param response change in the model
     */
    @Override
    public void show(Response response) {
        log.trace(response.toString());
        if (response.good()) {
            System.out.println("Position: " + response.position());
        }

        synchronized (this) {
            alive = response.good();
            this.notifyAll();
        }
    }
}
