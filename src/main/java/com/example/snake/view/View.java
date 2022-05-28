package com.example.snake.view;

import com.example.snake.ctrl.Controller;

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
