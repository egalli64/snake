package com.example.snake.model;

import java.awt.Color;

public enum Id {
    EMPTY(Color.DARK_GRAY), FOOD(Color.GREEN), BODY(Color.WHITE), HEAD(Color.YELLOW);

    private final Color color;

    private Id(Color color) {
        this.color = color;
    }

    public Color color() {
        return color;
    }
}
