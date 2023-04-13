package ru.liga.karmatskiyrg.service.help;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.currencies.DCurrencyTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
public class TestTypeChecker {
    public static final List<String> standardModel = new ArrayList<>();

    @BeforeAll
    public static void initClass() {
        standardModel.add("Турецкая лира");
        standardModel.add("Доллар США");
        standardModel.add("Хрень какя-то");
    }

    @Test
    public void testCheckingTypes() {
        var res = standardModel.stream()
                .map(DCurrencyTypes::getShortNameType)
                .map(Objects::isNull)
                .peek(x -> log.debug("print type = {}", x))
                .toList();
        assertThat(res)
                .containsAnyOf(true, false);
    }

    @Test
    public void testFindTypesAndReturnItsValue() {
        var res = standardModel.stream()
                .map(DCurrencyTypes::getShortNameType)
                .peek(x -> log.debug("print type = {}", x))
                .toList();
        assertThat(res)
                .containsNull()
                .containsAnyOf(DCurrencyTypes.USD, DCurrencyTypes.TRY)
                .doesNotContain(DCurrencyTypes.EUR)
                .hasSize(3);
    }


}
