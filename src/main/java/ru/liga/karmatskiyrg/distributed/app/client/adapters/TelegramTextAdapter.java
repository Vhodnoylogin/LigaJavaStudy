package ru.liga.karmatskiyrg.distributed.app.client.adapters;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.liga.karmatskiyrg.distributed.app.client.adapters.context.TelegramContext;
import ru.liga.karmatskiyrg.distributed.app.lib.annotations.AdapterAnno;

@AdapterAnno
public class TelegramTextAdapter extends TelegramAdapter {
    private final String data;

    public TelegramTextAdapter(String data) {
        this.data = data;
    }

    @Override
    public <C> void doIt(C c) {

        if (c instanceof TelegramContext context) {
            try {

                var bot = context.getBot();
                var id = context.getChatId();

                var msg = new SendMessage();
                msg.setChatId(id);
                msg.setText(this.data);

                bot.execute(msg);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException();
        }
    }

}
