package ru.liga.karmatskiyrg.distributed.app.client.utils.egg;

public interface CommandStringToExecutionEgg<T> {
    void execute(String commandString, T context);
}
