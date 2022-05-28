package com.example.snake.view;

import java.util.Scanner;

import com.example.snake.ctrl.TextualController;
import org.tinylog.Logger;

public class TextualView {
    private TextualController controller;

    public TextualView(TextualController controller) {
        this.controller = controller;
    }

    public boolean go() {
        try(Scanner scanner = new Scanner(System.in)) {
            Command command;
            do {
                System.out.print("Your direction [L,R,U,D] (or, exit [X] / no change): ");
                String input = scanner.nextLine().toLowerCase();
                command = Command.byShortcut(input.isEmpty() ? ' ' : input.charAt(0));
                Logger.trace(command);
            } while(command != Command.EXIT);
        }

        return true;
    }
}
