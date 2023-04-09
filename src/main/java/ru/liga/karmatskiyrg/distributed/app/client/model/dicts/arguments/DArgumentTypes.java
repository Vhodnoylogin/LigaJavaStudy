package ru.liga.karmatskiyrg.distributed.app.client.model.dicts.arguments;

import lombok.NonNull;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.arguments.interfaces.DArgumentType;

public enum DArgumentTypes implements DArgumentType {
    PERIOD("period"),
    DATE("date"),
    ALG("alg"),
    OUT("output"),
    CUR("cur");
    private final String longName;

    DArgumentTypes(@NonNull String s) {
        this.longName = s.toLowerCase();
    }

    public static DArgumentType getType(@NonNull String name) {
        for (var type : DArgumentTypes.values()) {
            if (type.getLongName().equals(name.toLowerCase()))
                return type;
        }
        return null;
    }

    @Override
    public String getLongName() {
        return this.longName;
    }
}
