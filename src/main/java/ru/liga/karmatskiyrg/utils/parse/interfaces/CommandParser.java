package ru.liga.karmatskiyrg.utils.parse.interfaces;

import java.util.List;

public interface CommandParser<T> {
    List<T> parseCommand(String commandLine);
}
