package ru.liga.karmatskiyrg.distributed.app.lib.router;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import ru.liga.karmatskiyrg.distributed.app.client.controller.telergam.AnotherRateCommandController;
import ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.algorithm.OldAlgorithmController;
import ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.period.TomorrowController;
import ru.liga.karmatskiyrg.distributed.app.client.service.lowlevel.period.WeekController;
import ru.liga.karmatskiyrg.distributed.app.lib.adapters.Adapter;
import ru.liga.karmatskiyrg.distributed.app.lib.annotations.ControllerMethod;
import ru.liga.karmatskiyrg.distributed.app.lib.annotations.aruments.ArgName;
import ru.liga.karmatskiyrg.distributed.app.lib.annotations.aruments.ArgNameController;
import ru.liga.karmatskiyrg.distributed.app.lib.parsers.interfaces.CommandParser;
import ru.liga.karmatskiyrg.distributed.app.lib.router.interfaces.Router;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;

import static ru.liga.karmatskiyrg.distributed.app.lib.parsers.CommandWithArgsParser.COMMAND_WITH_ARGS_PARSER;
import static ru.liga.karmatskiyrg.distributed.app.lib.parsers.CommandWithArgsParser.SUPER_COMMAND;

@Slf4j
public class Egg implements Router {
    public static final Egg EGG = new Egg();

    private static final Map<String, Class<? extends CommandParser>> parsers = new HashMap<>();
    private static final Map<String, Class<?>> controllers = new HashMap<>();
    private static final Map<String, List<Method>> controllerMethods = new HashMap<>();
    private static final Map<String, Adapter> adapters = new HashMap<>();

    static {
        controllers.put("tomorrow", TomorrowController.class);
        controllers.put("week", WeekController.class);
        controllers.put("old", OldAlgorithmController.class);

        controllerMethods.put(
                "rate",
                Arrays.stream(AnotherRateCommandController.class.getDeclaredMethods())
                        .filter(method -> method.isAnnotationPresent(ControllerMethod.class))
                        .filter(method -> Objects.equals(method.getAnnotation(ControllerMethod.class).value(), "rate"))
                        .toList()
        );

//        for (Method method : AnotherRateCommandController.class.getDeclaredMethods()) {
//            if (!method.isAnnotationPresent(ControllerMethod.class)) {
//                continue;
//            }
//            list.add(method);
//        }

        controllerMethods.put(
                "test",
                Arrays.stream(AnotherRateCommandController.class.getDeclaredMethods())
                        .filter(method -> method.isAnnotationPresent(ControllerMethod.class))
                        .filter(method -> Objects.equals(method.getAnnotation(ControllerMethod.class).value(), "test"))
                        .toList()
        );

    }

    private static List<Object> alignmentParams(Map<String, String> mapInputParams, Parameter[] listFuncParams) {
        var resArgsList = new ArrayList<>();
        for (Parameter param : listFuncParams) {
            var argName = "";
            if (param.isAnnotationPresent(ArgNameController.class)) {
                argName = param.getAnnotation(ArgNameController.class).value();
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

            if (param.isAnnotationPresent(ArgNameController.class)) {
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
        return resArgsList;
    }

    private static List<Class<?>> varTypes(List<Object> vars) {
        return vars.stream()
                .map(Object::getClass)
                .collect(Collectors.toList());
    }

    private static Pair<Method, Object[]> proccessed(Map<String, String> map) {
        var command = map.get(SUPER_COMMAND);

        var listOfMethods = controllerMethods.get(command);
        log.debug("listOfMethods = {}", listOfMethods);

        for (Method method : listOfMethods) {
            log.debug("method = {}", method);
            log.debug("Parameters = {}", method.getParameters());
            var args = alignmentParams(map, method.getParameters());
            log.debug("args = {}", args);
            var argTypes = varTypes(args);
            log.debug("argTypes = {}, and method types = {}, and they compare = {}",
                    argTypes,
                    method.getParameterTypes(),
                    paramTypesCompare(argTypes, method.getParameterTypes())
            );

            if (paramTypesCompare(argTypes, method.getParameterTypes())) {
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

        log.debug("method = {}", method);
        log.debug("args = {}", args);
        try {
            var object = method.getDeclaringClass().getConstructor().newInstance();
            log.debug("object = {}", object);
            return method.invoke(object, args);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }


//        var listObj =new ArrayList<>();
//                var fields = obClass.getDeclaredFields();
//        for (Field field : fields) {
//            if (field.isAnnotationPresent(ResolveVariable.class)) {
//                var anno = field.getDeclaredAnnotation(ResolveVariable.class);
//                var keyName = anno.value() == null ? field.getName() : anno.value();
//                var fObClass = controllers.get(keyName);
//
//
//                if(fObClass.isEnum()){
//                    var obj = Arrays.stream(fObClass.getEnumConstants())
//                            .filter(x -> x.toString().equals(keyName))
//                            .findFirst()
//                            .get();
//                    listObj.add(obj);
//
//                }else {
//                    try {
//                        var obj = fObClass. getConstructor().newInstance();
//                        listObj.add(obj);
//                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
//                             NoSuchMethodException e) {
////                        throw new RuntimeException(e);
//                        listObj.add(null);
//                    }
//                }
//            }
//        }
//
//        try {
//            var listObjType = listObj.stream().map(Object::getClass).toList().toArray(new Class<?>[listObj.size()]);
//            var res = obClass. getConstructor(listObjType).newInstance(listObj);
//            return res;
//        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
//                 NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public Adapter execute(String commandString) {
        var map = COMMAND_WITH_ARGS_PARSER.parseCommand(commandString);

        log.info("{}", map);


        var res = doAllIt(map);

        System.out.println(res);

        return null;
    }

    private void scanClasses() {

    }
}
