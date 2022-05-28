package com.example.snake.ctrl;

import com.example.snake.model.Board;
import com.example.snake.model.Direction;
import com.example.snake.model.Position;
import com.example.snake.model.Snake;
import com.example.snake.view.TextualView;
import org.tinylog.Logger;

import java.util.Optional;

public class TextualController {
    TextualView view;
    Board board;
    Snake snake;

    public TextualController(int nRows, int nCols) {
        this.view = new TextualView(this);
        this.board = new Board(nRows, nCols);

        Position head = board.pop();
        snake = new Snake(head, Math.min(nCols, nCols) / 2);

        while (snake.hasToGrow()) {
            Optional<Position> next = board.pop(head, snake.getDirection());
            if (next.isPresent()) {
                head = next.get();
                snake.grow(head);
            } else {
                Logger.error("Can't move " + snake);
            }
        }

        Logger.trace(snake);
    }

    public boolean execute(Command command) {
        Logger.trace("Command: " + command);
        if (command == Command.EXIT) {
            return false;
        }

        Direction direction = command == Command.SAME ? snake.getDirection() : snake.towards(Direction.from(command));
        Logger.trace("Direction: " + direction);
        return true;
    }

    public void go() {
        view.go();
    }
}
