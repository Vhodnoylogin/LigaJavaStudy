package ru.liga.karmatskiyrg.distributed.app.lib.router;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import ru.liga.karmatskiyrg.distributed.app.client.adapters.TelegramImageAdapter;
import ru.liga.karmatskiyrg.distributed.app.client.adapters.TelegramTextAdapter;
import ru.liga.karmatskiyrg.distributed.app.client.controller.RateCommandController;
import ru.liga.karmatskiyrg.distributed.app.client.service.currency.PredictCurrencyRateOld;
import ru.liga.karmatskiyrg.distributed.app.client.utils.dates.TomorrowPeriod;
import ru.liga.karmatskiyrg.distributed.app.client.utils.dates.WeekPeriod;
import ru.liga.karmatskiyrg.distributed.app.lib.adapters.Adapter;
import ru.liga.karmatskiyrg.distributed.app.lib.annotations.AdapterAnno;
import ru.liga.karmatskiyrg.distributed.app.lib.annotations.ControllerMethod;
import ru.liga.karmatskiyrg.distributed.app.lib.annotations.aruments.ArgName;
import ru.liga.karmatskiyrg.distributed.app.lib.annotations.aruments.ArgNameService;
import ru.liga.karmatskiyrg.distributed.app.lib.parsers.interfaces.CommandParser;
import ru.liga.karmatskiyrg.distributed.app.lib.router.interfaces.Router;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.liga.karmatskiyrg.distributed.app.lib.parsers.CommandWithArgsParser.COMMAND_WITH_ARGS_PARSER;
import static ru.liga.karmatskiyrg.distributed.app.lib.parsers.CommandWithArgsParser.SUPER_COMMAND;

@Slf4j
public class Egg implements Router {
//    public static final Egg EGG = new Egg();

    private static final Map<String, Class<? extends CommandParser>> parsers = new HashMap<>();
    private static final Map<String, Class<?>> controllers = new HashMap<>();
    private static final Map<String, List<Method>> controllerMethods = new HashMap<>();
    private static final Map<String, List<? extends Class<? extends Adapter>>> adapters = new HashMap<>();

    static {
        controllers.put("tomorrow", TomorrowPeriod.class);
        controllers.put("week", WeekPeriod.class);
        controllers.put("old", PredictCurrencyRateOld.class);

        controllerMethods.put(
                "rate",
                Arrays.stream(RateCommandController.class.getDeclaredMethods())
                        .filter(method -> method.isAnnotationPresent(ControllerMethod.class))
                        .filter(method -> Objects.equals(method.getAnnotation(ControllerMethod.class).value(), "rate"))
                        .toList()
        );


        controllerMethods.put(
                "test",
                Arrays.stream(RateCommandController.class.getDeclaredMethods())
                        .filter(method -> method.isAnnotationPresent(ControllerMethod.class))
                        .filter(method -> Objects.equals(method.getAnnotation(ControllerMethod.class).value(), "test"))
                        .toList()
        );


        adapters.put(
                "telegram",
                Stream.of(TelegramImageAdapter.class, TelegramTextAdapter.class)
                        .filter(x -> x.isAnnotationPresent(AdapterAnno.class))
                        .toList()
        );

    }

    private static List<Object> alignmentParams(Map<String, String> mapInputParams, Parameter[] listFuncParams) {
        var resArgsList = new ArrayList<>();
        for (Parameter param : listFuncParams) {
            var argName = "";
            if (param.isAnnotationPresent(ArgNameService.class)) {
                argName = param.getAnnotation(ArgNameService.class).value();
            } else if (param.isAnnotationPresent(ArgName.class)) {
                argName = param.getAnnotation(ArgName.class).value();
            } else {
                continue;
            }

            if (!mapInputParams.containsKey(argName)) {
                continue;
            }
            var arg = mapInputParams.get(argName);
//            log.debug("arg name = {}, agr value = {}", argName, arg);

            if (param.isAnnotationPresent(ArgNameService.class)) {
                try {
                    var ctrl = controllers.get(arg);
                    var argOb = ctrl.getConstructor().newInstance();
//                    log.debug("{}", argOb);
                    resArgsList.add(argOb);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
//                    throw new RuntimeException(e);
                    log.error("", e);
                }
            } else {
                resArgsList.add(arg);
            }
        }
        if (resArgsList.size() == listFuncParams.length) {
            return resArgsList;
        }
        return List.of();
    }

    private static List<Class<?>> varTypes(List<Object> vars) {
        return vars.stream()
                .map(Object::getClass)
                .collect(Collectors.toList());
    }

    private static Pair<Method, Object[]> proccessed(Map<String, String> map) {
        var command = map.get(SUPER_COMMAND);
        var newMap = new HashMap<>(map);
        newMap.remove(SUPER_COMMAND);

        var listOfMethods = controllerMethods.get(command);
//        log.debug("listOfMethods = {}", listOfMethods);

        for (Method method : listOfMethods) {
//            log.debug("method = {}", method);
//            log.debug("Parameters = {}", method.getParameters());
            var args = alignmentParams(newMap, method.getParameters());
//            log.debug("args = {}", args);
            var argTypes = varTypes(args);
//            log.debug("argTypes = {}, and method types = {}, and they compare = {}",
//                    argTypes,
//                    method.getParameterTypes(),
//                    paramTypesCompare(argTypes, method.getParameterTypes())
//            );

//            if (paramTypesCompare(argTypes, method.getParameterTypes())) {
            if (!args.isEmpty()) {
                log.debug("method = {}, args = {}", method, args);
                return Pair.of(method, args.toArray());
            }
        }
        return null;
    }

    private static boolean paramTypesCompare(List<Class<?>> myParamTypes, Class<?>[] methodParamTypes) {
        if (myParamTypes.size() != methodParamTypes.length) {
            return false;
        }
        var listMethodParamTypes = Arrays.asList(methodParamTypes);

        for (int i = 0; i < listMethodParamTypes.size(); i++) {
            var myType = myParamTypes.get(i);
            var methodType = listMethodParamTypes.get(i);
            if (!methodType.isAssignableFrom(myType)) {
                return false;
            }
        }
        return true;
    }

    private static Object doAllIt(Map<String, String> map) {
        var q = proccessed(map);

        var method = q.getLeft();
        var args = q.getRight();

//        log.debug("method = {}", method);
//        log.debug("args = {}", args);
        try {
            var object = method.getDeclaringClass().getConstructor().newInstance();
            return method.invoke(object, args);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Adapter execute(String commandString) throws RuntimeException {
        var map = COMMAND_WITH_ARGS_PARSER.parseCommand(commandString);

        log.info("{}", map);


        var res = doAllIt(map);

        for (Class<? extends Adapter> clazz : adapters.get("telegram")) {
            for (Constructor<?> constructor : clazz.getConstructors()) {
                if (constructor.getParameterTypes().length != 1) {
                    continue;
                }
                if (constructor.getParameterTypes()[0] != res.getClass()) {
                    continue;
                }
                try {
                    log.info("{}", clazz);
                    var c = clazz.getConstructor(res.getClass());
                    log.info("{}", c);
                    return c.newInstance(res);
                } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                         IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return null;
    }

    private void scanClasses() {

    }
}
