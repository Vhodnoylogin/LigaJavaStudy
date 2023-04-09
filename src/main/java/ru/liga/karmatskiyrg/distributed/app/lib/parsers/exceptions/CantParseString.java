package ru.liga.karmatskiyrg.distributed.app.lib.parsers.exceptions;

public class CantParseString extends RuntimeException {
    public CantParseString(String commandLine) {
        super(commandLine);
    }
}
