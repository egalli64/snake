package com.github.egalli64.snake.ctrl;

import com.github.egalli64.snake.model.Position;

/**
 * Info from the model
 *
 * @param type     kind of response
 * @param position where on board
 */
public record Response(ResponseType type, Position position) {
    /**
     * Generate a terminator
     */
    public Response() {
        this(ResponseType.TERMINATE, null);
    }

    /**
     * @return true if the game is still on
     */
    public boolean good() {
        return type != ResponseType.TERMINATE;
    }
}
