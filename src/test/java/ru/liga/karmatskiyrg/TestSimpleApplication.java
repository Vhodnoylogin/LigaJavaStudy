package ru.liga.karmatskiyrg;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.distributed.app.lib.router.Egg;

@Slf4j
public class TestSimpleApplication {

    public static Egg egg;

    @BeforeAll
    public static void init() {
        egg = new Egg();
    }

    @Test
    public void testTestCommand() {
        var input = "test --cur EUR";
        var res = egg.execute(input);
        log.debug("{}", res);
    }

    @Test
    public void testSimplePeriod() {
        var input = "rate --cur EUR --period week --alg old";
        var res = egg.execute(input);
        log.debug("{}", res);
    }

    @Test
    public void testSimpleDate() {
        var input = "rate --cur EUR --date 10.05.2001 --alg old";
        var res = egg.execute(input);
        log.debug("{}", res);
    }

    @Test
    public void testSimplePeriodWithOutput() {
        var input = "rate --cur EUR --period week --alg old --output list";
        var res = egg.execute(input);
        log.debug("{}", res);
    }
}
