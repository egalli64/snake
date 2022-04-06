package com.example.snake.view;

import java.util.Scanner;

import org.tinylog.Logger;

public class TextualView {
    public boolean go() {
        System.out.print("Enter when ready > ");
        try(Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            Logger.trace(input);
        }

        return true;
    }
}