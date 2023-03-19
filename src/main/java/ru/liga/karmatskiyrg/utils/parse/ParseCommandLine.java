package ru.liga.karmatskiyrg.utils.parse;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ParseCommandLine {
    protected static final Pattern pattern = Pattern.compile("\"([^\"]*)\"|(\\S+)");

    public static List<String> parseCommand(String commandLine) {
        var commandList = new ArrayList<String>();
        var matcher = pattern.matcher(commandLine);
        while (matcher.find()) {
            commandList.add(
                    matcher.group(1) != null ? matcher.group(1) : matcher.group(2)
            );
        }
        return commandList;
    }
}
