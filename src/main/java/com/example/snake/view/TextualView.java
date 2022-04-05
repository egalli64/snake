package com.example.snake.view;

import java.util.Scanner;

import org.tinylog.Logger;

public class TextualView {
    public boolean go() {
        System.out.println("Enter when ready");
        try(Scanner scanner = new Scanner(System.in)) {
            Logger.trace(scanner.nextLine());
        }

        return true;
    }
}