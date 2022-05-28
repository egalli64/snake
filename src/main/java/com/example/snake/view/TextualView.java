package com.example.snake.view;

import java.util.Scanner;

import com.example.snake.ctrl.Command;
import com.example.snake.ctrl.TextualController;
import org.tinylog.Logger;

public class TextualView {
    private TextualController controller;

    public TextualView(TextualController controller) {
        this.controller = controller;
    }

    public void go() {
        try(Scanner scanner = new Scanner(System.in)) {
            Command command;
            do {
                System.out.print("Your direction [l,r,u,d] (or, exit [x] / no change): ");
                String input = scanner.nextLine().toLowerCase();
                Logger.trace("User input: " + input);
                command = Command.byShortcut(input.isEmpty() ? ' ' : input.charAt(0));
            } while(controller.execute(command));
        }
    }
}
