package com.github.egalli64.snake.ctrl;

import com.github.egalli64.snake.model.Board;
import com.github.egalli64.snake.model.Direction;
import com.github.egalli64.snake.model.Position;
import com.github.egalli64.snake.model.Snake;
import com.github.egalli64.snake.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * The application controller
 */
public class Controller implements Runnable {
    static final int PERIOD_MS = 500;
    private static final Logger log = LoggerFactory.getLogger(Controller.class);
    View view;
    Board board;
    Snake snake;
    Timer timer;
    BlockingQueue<Command> commands;

    /**
     * The (squared) board dimension is up to the caller
     * The snake initial size is half the board size
     *
     * @param size number of rows / columns
     * @param view the associated view
     */
    public Controller(int size, View view) {
        this.board = new Board(size);
        this.commands = new ArrayBlockingQueue<>(10);
        this.view = view;
        view.show(new Response(ResponseType.FOOD, board.getFood()));

        Position head = board.randomPop();
        this.snake = new Snake(head);
        view.show(new Response(ResponseType.HEAD, head));

        Direction direction = snake.getDirection();
        while (snake.size() < size / 2) {
            Optional<Position> next = board.pop(head, direction);
            if (next.isPresent()) {
                head = next.get();
                snake.grow(head);
                view.show(new Response(ResponseType.MOVE_HEAD, head));
            } else {
                log.error("Can't grow {}", snake);
            }
        }
        log.trace("The starting snake: {}", snake);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                put(Command.SAME);
            }
        }, 0, PERIOD_MS);
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
            log.warn(command + " discarded", e);
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
            log.warn("Can't take command from queue", e);
        }

        // termination
        timer.cancel();
        commands.clear();
        view.show(new Response());
    }

    /**
     * Run the command selected by the user (or the default move)
     *
     * @param command the required command
     * @return false if the execution leads to termination
     */
    public boolean execute(Command command) {
        if (command == Command.EXIT) {
            return false;
        }

        Direction direction = command == Command.SAME ? snake.getDirection() : snake.towards(Direction.from(command));
        log.trace("Command: {}, direction: {}", command, direction);

        Optional<Position> next = board.pop(snake.getHead(), direction);
        if (next.isEmpty()) {
            return false;
        }

        Position head = next.get();
        if (head.equals(board.getFood())) {
            log.debug("Food eaten @{}", head);
            board.resetFood();
            view.show(new Response(ResponseType.FOOD, board.getFood()));

            snake.grow(head);
        } else {
            Position movedTail = snake.move(head);
            board.push(movedTail);
            view.show(new Response(ResponseType.MOVE_TAIL, movedTail));
        }

        view.show(new Response(ResponseType.MOVE_HEAD, head));
        return true;
    }
}
