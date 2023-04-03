package ru.liga.karmatskiyrg.controller.observers;

import ru.liga.karmatskiyrg.model.dicts.commands.interfaces.DCommand;
import ru.liga.karmatskiyrg.utils.observers.base.SuperSwitchAsObserver;

public final class CommandLeadAction extends SuperSwitchAsObserver<DCommand, Runnable> {
    private static final CommandLeadAction singleton = new CommandLeadAction();

    private CommandLeadAction() {
    }

    public static CommandLeadAction getSingleton() {
        return singleton;
    }
}
