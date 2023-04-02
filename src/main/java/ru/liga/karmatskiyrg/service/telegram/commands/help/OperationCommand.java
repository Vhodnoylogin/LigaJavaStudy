package ru.liga.karmatskiyrg.service.telegram.commands.help;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Суперкласс для команд создания заданий с различными операциями
 */
@Slf4j
public abstract class OperationCommand extends BotCommand {
//    private PlusMinusService service;

    public OperationCommand(String identifier, String description) {
        super(identifier, description);
//        this.service = new PlusMinusService(new WordFileProcessorImpl(), new Calculator());
    }

    /**
     * Отправка ответа пользователю
     */
    public void sendAnswer(AbsSender absSender, Long chatId, String description, String commandName, String userName, String text) {
        try {
//            absSender.execute(createDocument(chatId, operations, description));
            SendMessage message = new SendMessage();
            //включаем поддержку режима разметки, чтобы управлять отображением текста и добавлять эмодзи
            message.enableMarkdown(true);
            message.setChatId(chatId.toString());
            message.setText("TETERTERERTERTBDFBRT " + userName);
//            log.info();


            absSender.execute(message);
        } catch (IllegalArgumentException e) {
            sendError(absSender, chatId, commandName, userName);
            e.printStackTrace();
        } catch (TelegramApiException e) {
            //логируем сбой Telegram Bot API, используя commandName и userName
        }
    }

    /**
     * Отправка пользователю сообщения об ошибке
     */
    private void sendError(AbsSender absSender, Long chatId, String commandName, String userName) {
        try {
            absSender.execute(new SendMessage(chatId.toString(), "Похоже, я сломался. Попробуйте позже"));
        } catch (TelegramApiException e) {
            //логируем сбой Telegram Bot API, используя commandName и userName
        }
    }
}
