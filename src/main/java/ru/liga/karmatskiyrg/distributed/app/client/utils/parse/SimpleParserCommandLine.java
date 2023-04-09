package ru.liga.karmatskiyrg.distributed.app.client.utils.parse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Парсим строку по правилам:
 * Разбиваем по пробелам
 * Если часть строки обрамлена кавычками "", то она не разделяется
 * Пример: АААА "целая строка" раздельная строка => ["АААА", "целая строка", "раздельная", "строка"]
 */
public class SimpleParserCommandLine {
    public static final SimpleParserCommandLine SIMPLE_COMMAND_PARSER = new SimpleParserCommandLine();


    private static final Pattern pattern = Pattern.compile("\"([^\"]*)\"|(\\S+)");

    //    @Override
    public List<String> getTokenFromCommandString(String commandLine) {
        var commandList = new ArrayList<String>();
        var matcher = pattern.matcher(commandLine);
        while (matcher.find()) {
            commandList.add(
                    matcher.group(1) != null ? matcher.group(1) : matcher.group(2)
            );
        }
        return Collections.unmodifiableList(commandList);
    }
}
