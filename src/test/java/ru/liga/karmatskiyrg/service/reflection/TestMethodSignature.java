package ru.liga.karmatskiyrg.service.reflection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.distributed.app.client.adapters.TelegramImageAdapter;
import ru.liga.karmatskiyrg.distributed.app.client.adapters.TelegramTextAdapter;
import ru.liga.karmatskiyrg.distributed.app.lib.adapters.Adapter;
import ru.liga.karmatskiyrg.service.reflection.source.Test1String;
import ru.liga.karmatskiyrg.service.reflection.source.Test2Integer;
import ru.liga.karmatskiyrg.service.reflection.source.Test3ListOfDouble;
import ru.liga.karmatskiyrg.service.reflection.source.TestInterface;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class TestMethodSignature {

    public static Set<Class<? extends TestInterface<?>>> allVars = new HashSet<>();

    @BeforeAll
    public static void init() {
        allVars.add(Test1String.class);
        allVars.add(Test2Integer.class);
        allVars.add(Test3ListOfDouble.class);
    }

    @Test
    public void testMethodsSignatures() {
        for (Class<? extends TestInterface<?>> cl : allVars) {
            var res = cl.getDeclaredMethods();
            log.debug("{}", (Object[]) res);
        }
    }

    @Test
    public void test1String() {
        var res = Test1String.class.getDeclaredMethods();
        log.debug("length = {}", res.length);
        log.debug("{}", (Object[]) res);
    }

    @Test
    public void test2String() {
        var arrM = Test1String.class.getDeclaredMethods();
        log.debug("length = {}", arrM.length);
        for (Method method : arrM) {
            log.debug("{}", method);
        }
    }

    @Test
    public void testAdapterMethods() {


        Set<Class<? extends Adapter>> classes = new HashSet<>();
        classes.add(TelegramImageAdapter.class);
        classes.add(TelegramTextAdapter.class);

//        List<Class<?>> classHierarchy = new ArrayList<>();
//        Class<?> currentClass = TelegramImageAdapter.class;
//        while (currentClass != Object.class) {
//            classHierarchy.add(currentClass);
//            Class<?>[] interfaces = currentClass.getInterfaces();
//            classHierarchy.addAll(Arrays.asList(interfaces));
//            currentClass = currentClass.getSuperclass();
//        }
//        classHierarchy.stream()
////                .peek(x->log.debug("{}", x))
//                .flatMap(x-> Arrays.stream(x.getDeclaredMethods()))
//                .filter(x -> Modifier.isPublic(x.getModifiers()))
//                .
//                .distinct()
//                .forEach(x->log.debug("{}", x));

        classes.stream()
                .peek(x -> log.debug("{}", x))
                .flatMap(x -> Arrays.stream(x.getDeclaredMethods()))
                .distinct()
                .forEach(x -> log.debug("{}", x));

//        for (Class<?> aClass : classes) {
//            var methods = aClass.getInterfaces().getDeclaredMethods();
//            log.debug("{}, with length = {}", methods, methods.length);
//            for (Method method : methods) {
//                log.debug("{}", method);
//            }
//        }
    }
}
