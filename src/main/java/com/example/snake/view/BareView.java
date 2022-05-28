package com.example.snake.view;

import com.example.snake.ctrl.Command;
import com.example.snake.ctrl.Controller;
import org.tinylog.Logger;

import java.util.Scanner;

/**
 * A bare to the bone view for Snake
 */
public class BareView implements View {
    private Controller controller;

    public BareView(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void go() {
        try (Scanner scanner = new Scanner(System.in)) {
            Command command;
            do {
                System.out.print("Your direction [l,r,u,d] (or, exit [x] / no change): ");
                String input = scanner.nextLine().toLowerCase();
                Logger.trace("User input: " + input);
                command = Command.byShortcut(input.isEmpty() ? ' ' : input.charAt(0));
            } while (controller.execute(command));
        }
    }
}
