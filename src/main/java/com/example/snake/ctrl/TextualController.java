package com.example.snake.ctrl;

import com.example.snake.model.Data;
import com.example.snake.view.TextualView;

public class TextualController {
    Data data;
    TextualView view;

    public TextualController(int nRows, int nCols) {
        this.data = new Data(nRows, nCols);
        this.view = new TextualView();

        System.out.println(data);
    }

    public void go() {
        view.go();
    }
}
