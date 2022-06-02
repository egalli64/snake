package com.github.egalli64.snake.view;

import com.github.egalli64.snake.ctrl.Command;
import com.github.egalli64.snake.ctrl.Controller;
import com.github.egalli64.snake.ctrl.Response;
import org.tinylog.Logger;

import java.util.Scanner;

/**
 * A bare-bones view for Snake
 */
public class BareView implements View {
    private boolean alive;

    public BareView(int size) {
        this.alive = true;
        System.out.println("Snake lives in a square sized " + size);
    }

    synchronized boolean isAlive() {
        try {
            this.wait();
            return alive;
        } catch (InterruptedException e) {
            Logger.warn(e);
            return false;
        }
    }

    @Override
    public void go(Controller controller) {
        try (Scanner scanner = new Scanner(System.in)) {

            do {
                System.out.print("Your direction [l,r,u,d] (or, exit [x] / no change): ");
                String input = scanner.nextLine().toLowerCase();
                Logger.trace("User input: " + input);
                Command command = Command.byShortcut(input.isEmpty() ? ' ' : input.charAt(0));
                controller.put(command);
            } while (isAlive());
        }
    }

    public void show(Response response) {
        Logger.trace(response);
        if (response.good()) {
            System.out.println("[Head: " + response.head() + " direction " + response.direction() + "]");
        }

        synchronized (this) {
            alive = response.good();
            this.notifyAll();
        }
    }
}
