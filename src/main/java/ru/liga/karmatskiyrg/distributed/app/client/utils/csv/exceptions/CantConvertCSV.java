package ru.liga.karmatskiyrg.distributed.app.client.utils.csv.exceptions;

public class CantConvertCSV extends RuntimeException {
    public CantConvertCSV() {
    }

    public CantConvertCSV(String message) {
        super(message);
    }

    public CantConvertCSV(String message, Throwable cause) {
        super(message, cause);
    }

    public CantConvertCSV(Throwable cause) {
        super(cause);
    }

    public CantConvertCSV(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
