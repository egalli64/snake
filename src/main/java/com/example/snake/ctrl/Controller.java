package com.example.snake.ctrl;

import com.example.snake.model.Board;
import com.example.snake.model.Direction;
import com.example.snake.model.Position;
import com.example.snake.model.Snake;
import com.example.snake.view.View;
import org.tinylog.Logger;

import java.util.Optional;

/**
 * The application controller
 */
public class Controller {
    View view;
    Board board;
    Snake snake;

    /**
     * The (squared) board dimension is up to the caller
     * The snake size is based on the board size
     *
     * @param size number of rows / columns
     * @param view the associated view
     */
    public Controller(int size, View view) {
        this.board = new Board(size);
        this.view = view;

        Position head = board.randomPop();
        this.snake = new Snake(head);

        while (snake.size() < size / 2) {
            Optional<Position> next = board.pop(head, snake.getDirection());
            if (next.isPresent()) {
                head = next.get();
                snake.grow(head);
            } else {
                Logger.error("Can't grow " + snake);
            }
        }

        Logger.trace(snake);
        Logger.trace("Food: " + board.getFood());
    }

    /**
     * Run the command selected by the user
     *
     * @param command the required command
     * @return false if it leads to the game termination
     */
    public boolean execute(Command command) {
        Logger.trace("Command: " + command);
        if (command == Command.EXIT) {
            return false;
        }

        Direction direction = command == Command.SAME ? snake.getDirection() : snake.towards(Direction.from(command));
        Logger.trace("Direction: " + direction);
        Position head = snake.getHead();

        Optional<Position> next = board.pop(head, direction);
        if (next.isEmpty()) {
            return false;
        } else {
            Position pos = next.get();
            if(pos == board.getFood()) {
                Logger.debug("Food eaten @" + pos);
                board.resetFood();
                snake.grow(pos);
            } else {
                board.push(snake.move(pos));
            }
        }

        Logger.trace(snake);
        Logger.trace("Food: " + board.getFood());
        return true;
    }

    /**
     * Start the game
     */
    public void go() {
        view.go(this);
    }
}
