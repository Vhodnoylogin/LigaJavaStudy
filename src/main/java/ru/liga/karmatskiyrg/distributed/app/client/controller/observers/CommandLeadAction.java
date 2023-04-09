package ru.liga.karmatskiyrg.distributed.app.client.controller.observers;

import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.commands.interfaces.DCommand;
import ru.liga.karmatskiyrg.distributed.app.client.utils.observers.base.SuperSwitchAsObserver;

public final class CommandLeadAction extends SuperSwitchAsObserver<DCommand, Runnable> {
    private static final CommandLeadAction singleton = new CommandLeadAction();

    private CommandLeadAction() {
    }

    public static CommandLeadAction getSingleton() {
        return singleton;
    }
}
