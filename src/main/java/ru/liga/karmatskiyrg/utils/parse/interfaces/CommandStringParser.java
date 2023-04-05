package ru.liga.karmatskiyrg.utils.parse.interfaces;

import org.apache.commons.lang3.tuple.Pair;

public interface CommandStringParser<T> {
    Pair<T, String> getTokenFromCommandString(String commandLine);
}
