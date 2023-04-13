package ru.liga.karmatskiyrg.distributed.app.lib.parsers.exceptions;

public class NotValidCommand extends RuntimeException {
    public NotValidCommand() {
    }

    public NotValidCommand(String message) {
        super(message);
    }

    public NotValidCommand(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidCommand(Throwable cause) {
        super(cause);
    }

    public NotValidCommand(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
