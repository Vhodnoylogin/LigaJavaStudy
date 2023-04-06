package ru.liga.karmatskiyrg.service.telegram.bot;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class TelegramBot extends TelegramLongPollingCommandBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;

    //Класс для обработки сообщений, не являющихся командой
//    private final NonCommand nonCommand;

//    private final Set<String> names = new HashSet<>();

    public TelegramBot(String botName, String botToken) {
        super();
        this.BOT_NAME = botName;
        this.BOT_TOKEN = botToken;
//        создаём вспомогательный класс для работы с сообщениями, не являющимися командами
//        this.nonCommand = new NonCommand();
//        регистрируем команды
//        register(new StartCommand("start", "Старт"));
//        register(new TestCommand("test", "ТЕСТ"));
//        register(new PlusCommand("plus", "Сложение"));
//        register(new MinusCommand("minus", "Вычитание"));
//        register(new PlusMinusCommand("plusminus", "Сложение и вычитание"));
//        register(new HelpCommand("help","Помощь"));
//        register(new SettingsCommand("settings", "Мои настройки"));
//        userSettings = new HashMap<>();

//        this.onClosing();

        // зачем все эти регистрации - непонятно.
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        Message msg = update.getMessage();
        Long chatId = msg.getChatId();
        String userName = getUserName(msg);

        var text = msg.getText();


//        var user = msg.getFrom();
//        var name = user.getFirstName() + " " + user.getLastName() + " " + user.getId() + " " + user.getUserName();
//        this.names.add(name);
//        log.info(name);

//        String answer = nonCommand.nonCommandExecute(chatId, userName, msg.getText());
//        var answer = this.names;
//        setAnswer(chatId, userName, String.valueOf(answer));

    }

    /**
     * Формирование имени пользователя
     *
     * @param msg сообщение
     */
    private String getUserName(Message msg) {
        User user = msg.getFrom();
        String userName = user.getUserName();
        return (userName != null) ? userName : String.format("%s %s", user.getLastName(), user.getFirstName());
    }

    /**
     * Отправка ответа
     *
     * @param chatId   id чата
     * @param userName имя пользователя
     * @param text     текст ответа
     */
    private void setAnswer(Long chatId, String userName, String text) {
        SendMessage answer = new SendMessage();
        answer.setText(text);
        answer.setChatId(chatId.toString());
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            //логируем сбой Telegram Bot API, используя userName
        }
    }
}
