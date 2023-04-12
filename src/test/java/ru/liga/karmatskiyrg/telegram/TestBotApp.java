package ru.liga.karmatskiyrg.telegram;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.liga.karmatskiyrg.distributed.app.lib.router.Egg;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Slf4j
public class TestBotApp {

    //    private final static String command = "rate -cur TRY -date 22.02.2030 -alg mist";
    private final static String command = "rate -cur TRY -date 22.02.2030 -alg old";
    private static Egg app;
    private static Update update;

    @BeforeAll
    public static void init() {
        app = new Egg();

        var message = mock(Message.class);
        when(message.getText())
                .thenReturn(command);

        update = mock(Update.class);
        when(update.getMessage())
                .thenReturn(message);

    }

    @Test
    public void testAlg() {
        var res = app.execute(update.getMessage().getText());

        log.info("{}", res);
    }

}
