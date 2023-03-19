package ru.liga.karmatskiyrg.controller.observers;

import ru.liga.karmatskiyrg.model.dicts.interfaces.DLineCommand;
import ru.liga.karmatskiyrg.utils.observers.base.SuperSwitchAsObserver;

public final class CommandLeadAction extends SuperSwitchAsObserver<DLineCommand, Runnable> {
    private static final CommandLeadAction singleton = new CommandLeadAction();

    private CommandLeadAction() {
    }

    public static CommandLeadAction getSingleton() {
        return singleton;
    }
}
