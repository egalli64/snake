package com.github.egalli64.snake.view;

import com.github.egalli64.snake.ctrl.Controller;

/**
 * User Interface to Snake
 */
public interface View {
    /**
     * Let the fun begin
     *
     * @param controller the associated controller
     */
    void go(Controller controller);
}