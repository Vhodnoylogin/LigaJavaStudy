package ru.liga.karmatskiyrg.distributed.app.client.adapters;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.liga.karmatskiyrg.distributed.app.lib.annotations.Adapter;

@Adapter
public class TelegramImageAdapter extends TelegramAdapter<SendPhoto> {
    @Override
    public void doIt() {
        try {

            this.getContext().execute(this.getData());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
