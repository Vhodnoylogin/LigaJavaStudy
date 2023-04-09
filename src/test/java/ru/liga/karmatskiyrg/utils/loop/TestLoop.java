package ru.liga.karmatskiyrg.utils.loop;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.liga.karmatskiyrg.distributed.app.client.controller.observers.dicts.IsCurrencyString;
import ru.liga.karmatskiyrg.distributed.app.client.controller.observers.initialize.Init;
import ru.liga.karmatskiyrg.distributed.app.client.utils.loop.Loop;
import ru.liga.karmatskiyrg.distributed.app.client.utils.loop.LoopContext;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TestLoop {
    private static final Scanner scanner = new Scanner(new ByteArrayInputStream("""
            wrwrw
            USD
            Евро
            fsfs
            exit
            """.getBytes(StandardCharsets.UTF_8)));

    private static void context(LoopContext loopContext) {
        var text = scanner.nextLine();

        // exitLoop() НЕ завершает цикл сразу, а лишь дает команду остановиться. Одна итерация цикла все еще выполнится.
        // это баг или фича?
        if ("exit".equals(text)) loopContext.getControl().exitLoop();

        var res = IsCurrencyString.getSingleton().getFirstVariant(text);
        log.info("res = {}", res);

        assertThat(res);
    }

    @Test
    public void main() {
        var loop = new TestingLoop();
        loop.setInitAction(x -> Init.initDictionaries());
        loop.setAction(TestLoop::context);
        loop.run();
    }

    public static class TestingLoop extends Loop<LoopContext> {
        {
            this.setContext(() -> new Loop.LoopControl<>(this));
        }
    }
}
