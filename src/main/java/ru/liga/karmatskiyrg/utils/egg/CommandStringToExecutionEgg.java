package ru.liga.karmatskiyrg.utils.egg;

public interface CommandStringToExecutionEgg<T> {
    void execute(String commandString, T context);
}
