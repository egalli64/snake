package com.github.egalli64.snake.ctrl;

import com.github.egalli64.snake.model.Board;
import com.github.egalli64.snake.model.Direction;
import com.github.egalli64.snake.model.Position;
import com.github.egalli64.snake.model.Snake;
import com.github.egalli64.snake.view.View;
import org.tinylog.Logger;

import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * The application controller
 */
public class Controller implements Runnable {
    View view;
    Board board;
    Snake snake;
    BlockingQueue<Command> commands;

    /**
     * The (squared) board dimension is up to the caller
     * The snake size is half the board size
     *
     * @param size number of rows / columns
     * @param view the associated view
     */
    public Controller(int size, View view) {
        this.board = new Board(size);
        this.commands = new ArrayBlockingQueue<>(10);
        this.view = view;

        Position head = board.randomPop();
        this.snake = new Snake(head);
        Direction direction = snake.getDirection();
        view.show(new Response(head, null, board.getFood(), direction));

        while (snake.size() < size / 2) {
            Optional<Position> next = board.pop(head, direction);
            if (next.isPresent()) {
                head = next.get();
                snake.grow(head);
                view.show(new Response(head, null, null, direction));
            } else {
                Logger.error("Can't grow " + snake);
            }
        }

        Logger.trace("Snake: head " + snake.getHead() + ", direction " + snake.getDirection());
        Logger.trace("Food: " + board.getFood());
    }

    /**
     * Put the command in queue
     *
     * @param command a request to move the snake
     */
    public void put(Command command) {
        try {
            commands.put(command);
        } catch (InterruptedException e) {
            Logger.warn(e, command + " discarded");
        }
    }

    /**
     * Take a command from the queue and execute it, until the snake is alive
     */
    @Override
    public void run() {
        try {
            Command command;
            do {
                command = commands.take();
            } while (execute(command));
        } catch (InterruptedException e) {
            Logger.warn(e, "Can't take command from queue");
        }

        // termination
        view.show(new Response());
    }

    /**
     * Run the command selected by the user
     *
     * @param command the required command
     * @return false if it leads to the game termination
     */
    public boolean execute(Command command) {
        if (command == Command.EXIT) {
            return false;
        }

        Direction direction = command == Command.SAME ? snake.getDirection() : snake.towards(Direction.from(command));
        Logger.trace("Command: " + command + ", direction: " + direction);

        Optional<Position> next = board.pop(snake.getHead(), direction);
        if (next.isEmpty()) {
            return false;
        }

        Position head = next.get();
        Position tail = null;
        Position food = null;
        if (head.equals(board.getFood())) {
            Logger.debug("Food eaten @" + head);
            board.resetFood();
            snake.grow(head);
            food = board.getFood();
        } else {
            tail = snake.getTail();
            board.push(snake.move(head));
        }

        // signal the move to the view
        view.show(new Response(head, tail, food, direction));
        return true;
    }
}
