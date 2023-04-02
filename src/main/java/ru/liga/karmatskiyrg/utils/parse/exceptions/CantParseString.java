package ru.liga.karmatskiyrg.utils.parse.exceptions;

public class CantParseString extends RuntimeException {
    public CantParseString(String commandLine) {
        super(commandLine);
    }
}
