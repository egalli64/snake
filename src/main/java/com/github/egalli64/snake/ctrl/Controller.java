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
    BlockingQueue<Response> responses;

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
        this.responses = new ArrayBlockingQueue<>(10);
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

        Logger.trace("Snake: head " + snake.getHead() + ", direction " + snake.getDirection());
        Logger.trace("Food: " + board.getFood());
    }

    public void put(Command command) {
        try {
            commands.put(command);
        } catch (InterruptedException e) {
            Logger.warn(e, command + " discarded");
        }
    }

    public Response getResponse() {
        try {
            return responses.take();
        } catch (InterruptedException e) {
            Logger.warn(e, "Can't get response");
            return new Response();
        }
    }

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

        try {
            responses.put(new Response());
        } catch (InterruptedException e) {
            Logger.error(e, "Can't put terminal response to queue");
            throw new RuntimeException(e);
        }
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
        Logger.trace("Command: " + command + ", direction: " + direction);
        Position head = snake.getHead();

        Optional<Position> next = board.pop(head, direction);
        if (next.isEmpty()) {
            return false;
        }

        Position pos = next.get();
        Position tail = null;
        Position food = null;
        if (pos.equals(board.getFood())) {
            Logger.debug("Food eaten @" + pos);
            board.resetFood();
            snake.grow(pos);
            food = board.getFood();
        } else {
            tail = snake.getTail();
            board.push(snake.move(pos));
        }

        try {
            responses.put(new Response(next, tail, food));
        } catch (InterruptedException e) {
            Logger.error(e, "Can't put response to queue");
            throw new RuntimeException(e);
        }

        Logger.trace("Snake: head " + snake.getHead() + ", direction " + snake.getDirection());
        Logger.trace("Food: " + board.getFood());
        return true;
    }
}
