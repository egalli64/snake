package com.github.egalli64.snake.view;

import java.awt.Color;

/**
 * Cell representation
 */
public enum Id {
    EMPTY(Color.DARK_GRAY), FOOD(Color.GREEN), BODY(Color.WHITE), HEAD(Color.YELLOW);

    private final Color color;

    Id(Color color) {
        this.color = color;
    }

    public Color color() {
        return color;
    }
}
