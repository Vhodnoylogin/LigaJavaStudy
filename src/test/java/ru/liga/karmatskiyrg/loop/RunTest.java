package ru.liga.karmatskiyrg.loop;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.controller.observers.dicts.IsCurrencyString;
import ru.liga.karmatskiyrg.service.initialize.Init;
import ru.liga.karmatskiyrg.utils.loop.Context;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Slf4j
public class RunTest {

    private static final InputStream input = new ByteArrayInputStream("""
            wrwrw
            USD
            Евро
            fsfs
            exit
            """.getBytes(StandardCharsets.UTF_8));
    private static final Scanner scanner = new Scanner(input);



    private static void context(Context context) {
        var text = scanner.next();

        // exitLoop() НЕ завершает цикл сразу, а лишь дает команду остановиться. Одна итерация цикла все еще выполнится.
        // это баг или фича?
        if ("exit".equals(text)) context.getControl().exitLoop();

        var res = IsCurrencyString.getSingleton().getFirstVariant(text);
        log.info("Print res", res);
    }

    @Test
    public void main() {
        var loop = new TestLoop();
        loop.setInitAction(x -> Init.initDicts());
        loop.setAction(RunTest::context);
        loop.run();
    }
}
