package com.github.egalli64.snake.view;

import com.github.egalli64.snake.ctrl.Command;
import com.github.egalli64.snake.ctrl.Controller;
import com.github.egalli64.snake.ctrl.Response;
import org.tinylog.Logger;

//import java.util.Scanner;

/**
 * A bare-bones view for Snake
 */
public class BareView implements View {
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
            Logger.warn(e);
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
            if(isAlive()) {
                System.out.print("Your direction [l,r,u,d] (or, exit [x] / no change): ");
                String input = "x"; // scanner.nextLine().toLowerCase();
                Logger.trace("User input: " + input);
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
        Logger.trace(response);
        if (response.good()) {
            System.out.println("Snake head: " + response.snake().getHead());
        }

        synchronized (this) {
            alive = response.good();
            this.notifyAll();
        }
    }
}
