package com.github.egalli64.snake.ctrl;

import com.github.egalli64.snake.model.Position;
import com.github.egalli64.snake.model.Snake;

/**
 * Info from the model
 *
 * @param snake its current layout
 * @param food  the new food
 */
public record Response(Snake snake, Position food) {
    /**
     * Generate an empty response as terminator
     */
    public Response() {
        this(null, null);
    }

    /**
     * No snake means game over
     *
     * @return true if the game is still on
     */
    public boolean good() {
        return snake != null;
    }
}
