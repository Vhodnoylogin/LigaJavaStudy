package ru.liga.karmatskiyrg.distributed.app.lib.adapters;

import ru.liga.karmatskiyrg.distributed.app.lib.adapters.interfaces.Adapter;
import ru.liga.karmatskiyrg.distributed.app.lib.adapters.interfaces.AdapterPutContext;
import ru.liga.karmatskiyrg.distributed.app.lib.adapters.interfaces.AdapterPutData;

public abstract class AbstractAdapter<C, D> implements Adapter<C>, AdapterPutContext<C>, AdapterPutData<D> {
    private C context;
    private D data;

    protected C getContext() {
        return this.context;
    }

    protected D getData() {
        return this.data;
    }

    @Override
    public void putContext(C context) {
        this.context = context;
    }

    @Override
    public void putDate(D data) {
        this.data = data;
    }
}
