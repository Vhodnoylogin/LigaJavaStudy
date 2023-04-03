package ru.liga.karmatskiyrg.application;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.liga.karmatskiyrg.application.telegram.InnerApp;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Slf4j
public class TestBotApp {

    //    private final static String command = "rate -cur TRY -date 22.02.2030 -alg mist";
    private final static String command = "rate -cur TRY -date 22.02.2030 -alg old";
    private static InnerApp app;
    private static Update update;

    @BeforeAll
    public static void init() {
        app = new InnerApp();
        app.initCommands();

        var message = mock(Message.class);
        when(message.getText())
                .thenReturn(command);

        update = mock(Update.class);
        when(update.getMessage())
                .thenReturn(message);

    }

    @Test
    public void testAlg() {
        var res = app.content(update);

        var text = res.getText();
        log.info("{}", text);
    }

}
