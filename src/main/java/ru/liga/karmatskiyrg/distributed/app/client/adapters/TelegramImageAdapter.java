package ru.liga.karmatskiyrg.distributed.app.client.adapters;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.liga.karmatskiyrg.distributed.app.client.adapters.context.TelegramContext;
import ru.liga.karmatskiyrg.distributed.app.lib.annotations.AdapterAnno;

@AdapterAnno
public class TelegramImageAdapter extends TelegramAdapter {
    private final InputFile data;

    public TelegramImageAdapter(InputFile data) {
        this.data = data;
    }

    @Override
    public <C> void doIt(C c) {

        if (c instanceof TelegramContext context) {
            try {

                var bot = context.getBot();
                var id = context.getChatId();

                var msg = new SendPhoto();
                msg.setChatId(id);
                msg.setPhoto(this.data);

                bot.execute(msg);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException();
        }
    }

}
