package ru.liga.karmatskiyrg.distributed.app.client.utils.parse.tokens;

import org.apache.commons.lang3.tuple.Pair;

public class Token extends Pair<String, String> {
    private final Pair<String, String> pair;

    public Token(String l, String r) {
        this.pair = Pair.of(l, r);
    }

    public static Token of(String l, String r) {
        return new Token(l, r);
    }

    @Override
    public String getLeft() {
        return pair.getLeft();
    }

    @Override
    public String getRight() {
        return pair.getRight();
    }

    @Override
    public String setValue(String value) {
        return pair.setValue(value);
    }
}
