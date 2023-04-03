package ru.liga.karmatskiyrg.controller.telergam;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.liga.karmatskiyrg.controller.errors.NotValidCommand;
import ru.liga.karmatskiyrg.utils.parse.tokens.Token;

import java.util.List;

public class ExitCommandController {
    public SendMessage action(List<Token> tokens, Update update) {
        throw new NotValidCommand("EXIT");
    }
}
