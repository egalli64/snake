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
        System.out.print("Enter when ready > ");
        try(Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            Logger.trace(input);
        }

        return true;
    }
}
