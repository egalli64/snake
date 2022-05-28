package com.example.snake.ctrl;

import com.example.snake.model.Data;
import com.example.snake.view.TextualView;
import org.tinylog.Logger;

public class TextualController {
    Data data;
    TextualView view;

    public TextualController(int nRows, int nCols) {
        this.data = new Data(nRows, nCols);
        this.view = new TextualView(this);

        System.out.println(data);
    }

    public boolean execute(Command command) {
        Logger.trace(command);
        return command != Command.EXIT;
    }

    public void go() {
        view.go();
    }
}
