package ru.liga.karmatskiyrg.distributed.app.client.model.dicts.algorithms;

import lombok.NonNull;
import ru.liga.karmatskiyrg.distributed.app.client.model.dicts.algorithms.interfaces.DAlgorithm;

public enum DAlgorithms implements DAlgorithm {
    OLD("old");
    private final String longName;

    DAlgorithms(@NonNull String s) {
        this.longName = s.toLowerCase();
    }

    public static DAlgorithm getType(@NonNull String name) {
        for (var type : DAlgorithms.values()) {
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
