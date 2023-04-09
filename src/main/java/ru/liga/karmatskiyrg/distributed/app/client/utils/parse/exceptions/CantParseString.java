package ru.liga.karmatskiyrg.distributed.app.client.utils.parse.exceptions;

public class CantParseString extends RuntimeException {
    public CantParseString(String commandLine) {
        super(commandLine);
    }
}
