package ru.liga.karmatskiyrg.model.dicts.outputs;

import lombok.NonNull;
import ru.liga.karmatskiyrg.model.dicts.outputs.interfaces.DOutputType;

public enum DOutputTypes implements DOutputType {
    GRAPH("graph"),
    LIST("list");
    private final String paramName;

    DOutputTypes(@NonNull String paramName) {
        this.paramName = paramName.toLowerCase();
    }

    public static DOutputType getType(@NonNull String name) {
        for (var type : DOutputTypes.values()) {
            if (type.getLongName().equals(name.toLowerCase()))
                return type;
        }
        return null;
    }

    @Override
    public String getLongName() {
        return this.paramName;
    }
}
