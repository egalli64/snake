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
    @Override
    public void go(Controller controller) {
        try (Scanner scanner = new Scanner(System.in)) {
            Response response;

            do {
                System.out.print("Your direction [l,r,u,d] (or, exit [x] / no change): ");
                String input = scanner.nextLine().toLowerCase();
                Logger.trace("User input: " + input);
                Command command = Command.byShortcut(input.isEmpty() ? ' ' : input.charAt(0));
                controller.put(command);
                response = controller.getResponse();
                Logger.trace("Controller response " + response);
            } while(response.good());
        }
    }
}
