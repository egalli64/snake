package com.github.egalli64.snake.ctrl;

import com.github.egalli64.snake.model.Direction;
import com.github.egalli64.snake.model.Position;

/**
 * Response from Controller
 * <p>
 * if the snake is alive, its head changes position
 * <p>
 * if it eats, its tail does not move and food changes position
 *
 * @param head the new head
 * @param tail the new tail
 * @param food the new food
 */
public record Response(Position head, Position tail, Position food, Direction direction) {
    /**
     * Generate an empty response as terminator
     */
    public Response() {
        this(null, null, null, null);
    }

    /**
     * Headless snake means game over
     *
     * @return true if the game is still on
     */
    public boolean good() {
        return head != null;
    }
}
