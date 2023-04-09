package ru.liga.karmatskiyrg.distributed.app.lib.router;

import ru.liga.karmatskiyrg.distributed.app.lib.adapters.Adapter;
import ru.liga.karmatskiyrg.distributed.app.lib.parsers.interfaces.CommandParser;
import ru.liga.karmatskiyrg.distributed.app.lib.router.interfaces.Router;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static ru.liga.karmatskiyrg.distributed.app.lib.parsers.CommandWithArgsParser.COMMAND_WITH_ARGS_PARSER;
import static ru.liga.karmatskiyrg.distributed.app.lib.parsers.CommandWithArgsParser.SUPER_COMMAND;

public class Egg implements Router {
    public static final Egg EGG = new Egg();

    private static final Map<String, Class<? extends CommandParser>> parsers = new HashMap<>();
    private static final Map<String, Class<?>> controllers = new HashMap<>();
    //    private static final Map<String, Method> controllerMethods = new HashMap<>();
    private static final Map<String, Adapter> adapters = new HashMap<>();

    @Override
    public void execute(String commandString) {
        var map = COMMAND_WITH_ARGS_PARSER.parseCommand(commandString);
        var command = map.get(SUPER_COMMAND);

        var controllerClass = controllers.get(command);
        var methodList = controllerClass.getDeclaredMethods();
        for (Method method : methodList) {
            if (!method.getName().equals(command)) {
                continue;
            }
            var paramList = method.getParameters();

            if (paramList.length != map.size() - 1) {
                continue;
            }

            for (Parameter parameter : paramList) {
                parameter.getName()

            }

        }

        var args = new ArrayList<String>();
        args.add(map.get("cur"));
        args.add(map.get("period"));


        try {
            met.invoke(controllers.get(command), )
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private void scanClasses() {

    }
}
