package ru.liga.karmatskiyrg.loop;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.controller.observers.dicts.IsCurrencyString;
import ru.liga.karmatskiyrg.service.initialize.Init;
import ru.liga.karmatskiyrg.utils.loop.Context;
import ru.liga.karmatskiyrg.utils.loop.Loop;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Slf4j
public class RunTest {
    private static final Scanner scanner = new Scanner(new ByteArrayInputStream("""
            wrwrw
            USD
            Евро
            fsfs
            exit
            """.getBytes(StandardCharsets.UTF_8)));

    private static void context(Context context) {
        var text = scanner.nextLine();

        // exitLoop() НЕ завершает цикл сразу, а лишь дает команду остановиться. Одна итерация цикла все еще выполнится.
        // это баг или фича?
        if ("exit".equals(text)) context.getControl().exitLoop();

        var res = IsCurrencyString.getSingleton().getFirstVariant(text);
        log.info(String.valueOf(res));
    }

    @Test
    public void main() {
        var loop = new TestLoop();
        loop.setInitAction(x -> Init.initDicts());
        loop.setAction(RunTest::context);
        loop.run();
    }

    public static class TestLoop extends Loop<Context> {
        {
            this.context = () -> new LoopControl<>(this);
        }
    }
}
