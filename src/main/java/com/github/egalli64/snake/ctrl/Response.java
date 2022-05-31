package com.github.egalli64.snake.ctrl;

import com.github.egalli64.snake.model.Position;

import java.util.Optional;

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
public record Response(Optional<Position> head, Optional<Position> tail, Optional<Position> food) {
    public Response(Optional<Position> head, Position tail, Position food) {
        this(head, Optional.ofNullable(tail), Optional.ofNullable(food));
    }

    public Response() {
        this(Optional.empty(), Optional.empty(), Optional.empty());
    }

    public boolean good() {
        return head().isPresent();
    }
}
