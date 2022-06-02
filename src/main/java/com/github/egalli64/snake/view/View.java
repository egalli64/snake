package com.github.egalli64.snake.view;

import com.github.egalli64.snake.ctrl.Controller;
import com.github.egalli64.snake.ctrl.Response;

/**
 * User Interface to Snake
 */
public interface View {
    /**
     * Inject the associated controller in the view
     *
     * @param controller Snake controller
     */
    void go(Controller controller);

    /**
     * The snake move
     *
     * @param response change in the model
     */
    void show(Response response);
}
