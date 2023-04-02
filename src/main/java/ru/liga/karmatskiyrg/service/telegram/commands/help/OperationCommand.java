package ru.liga.karmatskiyrg.service.telegram.commands.help;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Суперкласс для команд создания заданий с различными операциями
 */
public abstract class OperationCommand extends BotCommand {
//    private PlusMinusService service;

    public OperationCommand(String identifier, String description) {
        super(identifier, description);
//        this.service = new PlusMinusService(new WordFileProcessorImpl(), new Calculator());
    }

    /**
     * Отправка ответа пользователю
     */
    public void sendAnswer(AbsSender absSender, Long chatId, String description, String commandName, String userName) {
        try {
//            absSender.execute(createDocument(chatId, operations, description));
            SendMessage message = new SendMessage();
            //включаем поддержку режима разметки, чтобы управлять отображением текста и добавлять эмодзи
            message.enableMarkdown(true);
            message.setChatId(chatId.toString());
            message.setText(description);

            absSender.execute(message);
        } catch (IllegalArgumentException e) {
            sendError(absSender, chatId, commandName, userName);
            e.printStackTrace();
        } catch (TelegramApiException e) {
            //логируем сбой Telegram Bot API, используя commandName и userName
        }
    }

//    /**
//     * Создание документа для отправки пользователю
//     * @param chatId id чата
//     * @param operations список типов операций (сложение и/или вычитание)
//     * @param fileName имя, которое нужно присвоить файлу
//     */
//    private SendDocument createDocument(Long chatId, List<OperationEnum> operations, String fileName) throws IOException {
//        FileInputStream stream = service.getPlusMinusFile(operations, Bot.getUserSettings(chatId));
//        SendDocument document = new SendDocument();
//        document.setChatId(chatId.toString());
//        document.setDocument(new InputFile(stream, String.format("%s.docx", fileName)));
//        return document;
//    }

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
