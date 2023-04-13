package ru.liga.karmatskiyrg.distributed.app.lib.adapters;

import ru.liga.karmatskiyrg.distributed.app.lib.adapters.interfaces.Adapter;
import ru.liga.karmatskiyrg.distributed.app.lib.adapters.interfaces.AdapterPutContent;
import ru.liga.karmatskiyrg.distributed.app.lib.adapters.interfaces.AdapterPutData;

public abstract class AbstractAdapter<C, D> implements Adapter, AdapterPutContent<C>, AdapterPutData<D> {
    private C content;
    private D data;

    @Override
    public void putContent(C content) {
        this.content = content;
    }

    @Override
    public void putDate(D data) {
        this.data = data;
    }
}
