package com.github.egalli64.snake.view;

import java.awt.Color;

/**
 * Cell representation
 */
public enum Id {
    FOOD(Color.GREEN), BODY(Color.BLUE), HEAD(Color.YELLOW), EMPTY(Color.LIGHT_GRAY);

    private final Color color;

    Id(Color color) {
        this.color = color;
    }

    public Color color() {
        return color;
    }
}
