package ru.liga.karmatskiyrg.service.help;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.controller.observers.dicts.IsCurrencyString;
import ru.liga.karmatskiyrg.model.dicts.DCurrencyTypes;
import ru.liga.karmatskiyrg.model.dicts.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.test.SuperTest;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class TestTypeChecker extends SuperTest {
    protected static String[] curs = new String[]{"Турецкая лира", "Доллар США", "Евро", "Хрень какя-то"};


    protected IsCurrencyString isCurrencyString = IsCurrencyString.getSingleton();

    @Test
    public void test1() {
        Arrays.stream(curs).map(isCurrencyString::isVariant).forEach(x -> log.info("this", x));
    }

    @Test
    public void test2() {
        Arrays.stream(curs).map(isCurrencyString::getFirstVariant).forEach(x -> log.info("this", x));
    }

    @Test
    public void test3() {
        var type = DCurrencyTypes.EUR;

        var res = isCurrencyString.getFirstVariant(type.getLongName());
        log.info("this", res);
        assertEquals(type, res);
    }

    @Test
    public void test31() {
        var type = DCurrencyTypes.EUR;

        var res = isCurrencyString.getFirstVariant(type.name());
        log.info("this", res);
        assertEquals(type, res);
    }


    @Test
    public void test4() {
        DCurrencyType type = () -> "New";

        isCurrencyString.addVariant("NEW", (x) -> Objects.equals(type.getLongName(), x) ? type : null);

        var res = isCurrencyString.getFirstVariant(type.getLongName());
        log.info("this", res);
        assertEquals(type, res);
    }

    @Test
    public void test5() {
        DCurrencyType type = () -> "New";
        DCurrencyType type1 = () -> "New1";

        isCurrencyString.addVariant("NEW", (x) -> Objects.equals(type.getLongName(), x) ? type : null);

        var res = isCurrencyString.getFirstVariant(type1.getLongName());
        log.info("this", res);
        assertEquals(type, res);
    }
}
