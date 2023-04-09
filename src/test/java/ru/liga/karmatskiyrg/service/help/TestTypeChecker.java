package ru.liga.karmatskiyrg.service.help;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.distributed.app.client.controller.observers.dicts.IsCurrencyString;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.currencies.DCurrencyTypes;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.currencies.interfaces.DCurrencyType;
import ru.liga.karmatskiyrg.init.InitTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
public class TestTypeChecker extends InitTest {
    public static final List<String> standardModel = new ArrayList<>();
    protected final IsCurrencyString isCurrencyString = IsCurrencyString.getSingleton();

    @BeforeAll
    public static void initClass() {
        standardModel.add("Турецкая лира");
        standardModel.add("Доллар США");
        standardModel.add("Хрень какя-то");
    }

    @Test
    public void testCheckingTypes() {
        var res = standardModel.stream()
                .map(isCurrencyString::isVariant)
                .peek(x -> log.debug("print type = {}", x))
                .toList();
        assertThat(res)
                .containsAnyOf(true, false);
    }

    @Test
    public void testFindTypesAndReturnItsValue() {
        var res = standardModel.stream()
                .map(isCurrencyString::getFirstVariant)
                .peek(x -> log.debug("print type = {}", x))
                .toList();
        assertThat(res)
                .containsNull()
                .containsAnyOf(DCurrencyTypes.USD, DCurrencyTypes.TRY)
                .doesNotContain(DCurrencyTypes.EUR)
                .hasSize(3);
    }

    @Test
    public void testFindTypeByItsLongName() {
        var type = DCurrencyTypes.EUR;
        var res = isCurrencyString.getFirstVariant(type.getLongName());

        log.debug("Result type = {}", res);
        assertThat(res)
                .isEqualTo(type);
    }

    @Test
    public void testFindTypeByItsTypeName() {
        var type = DCurrencyTypes.EUR;
        var res = isCurrencyString.getFirstVariant(type.name());

        log.debug("Result type = {}", res);
        assertThat(res)
                .isEqualTo(type);
    }


    @Test
    public void testNewTypeNotInDict() {
        DCurrencyType type = () -> "New";

        isCurrencyString.addVariant("testNewTypeNotInDict", (x) -> Objects.equals(type.getLongName(), x) ? type : null);
        var res = isCurrencyString.getFirstVariant(type.getLongName());

        log.debug("Result type = {}", res);
        assertThat(res)
                .isEqualTo(type);
    }

    @Test
    public void testNewTypeInDictNotEqual() {
        DCurrencyType type = "New"::toLowerCase;
        DCurrencyType type1 = "New1"::toLowerCase;

        isCurrencyString.addVariant("testNewTypeInDictNotEqual", (x) -> Objects.equals(type.getLongName(), x) ? type : null);
        var res = isCurrencyString.getFirstVariant(type.getLongName());

        log.debug("Result type = {}", res);
        assertThat(res)
                .isEqualTo(type)
                .isNotEqualTo(type1);
    }

    @Test
    public void testNewTypeNotInDictNotEqual() {
        DCurrencyType type = "New"::toLowerCase;
        DCurrencyType type1 = "New1"::toLowerCase;

        isCurrencyString.addVariant("testNewTypeNotInDictNotEqual", (x) -> Objects.equals(type.getLongName(), x) ? type : null);
        var res = isCurrencyString.getFirstVariant(type1.getLongName());

        log.debug("Result type = {}", res);
        assertThat(res)
                .isNull();
    }
}
