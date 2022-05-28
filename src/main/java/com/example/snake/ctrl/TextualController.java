package com.example.snake.ctrl;

import com.example.snake.model.Board;
import com.example.snake.view.TextualView;
import org.tinylog.Logger;

public class TextualController {
    Board board;
    TextualView view;

    public TextualController(int nRows, int nCols) {
        this.board = new Board(nRows, nCols);
        this.view = new TextualView(this);

        System.out.println(board);
    }

    public boolean execute(Command command) {
        Logger.trace(command);
        return command == Command.EXIT ? false : board.nextStep(command);
    }

    public void go() {
        view.go();
    }
}
