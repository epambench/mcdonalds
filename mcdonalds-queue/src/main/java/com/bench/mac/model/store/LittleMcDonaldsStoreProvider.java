package com.bench.mac.model.store;

import com.bench.mac.api.config.MacOptions;
import com.bench.mac.api.model.ClientProvider;
import com.bench.mac.api.model.MacManagerProvider;
import com.bench.mac.api.model.McDonaldsStore;
import com.bench.mac.api.model.StoreProvider;
import com.bench.mac.api.model.WorkerProvider;
import com.bench.mac.model.AbstractMacProvider;

import javax.inject.Inject;

public class LittleMcDonaldsStoreProvider extends AbstractMacProvider implements StoreProvider {
    private final MacOptions macOptions;

    private final MacManagerProvider macManagerProvider;
    private final ClientProvider clientProvider;
    private final WorkerProvider workerProvider;

    @Inject
    private LittleMcDonaldsStoreProvider(final MacOptions macOptions,
                                         final MacManagerProvider macManagerProvider,
                                         final ClientProvider clientProvider,
                                         final WorkerProvider workerProvider) {
        this.macOptions = macOptions;
        this.macManagerProvider = macManagerProvider;
        this.clientProvider = clientProvider;
        this.workerProvider = workerProvider;
    }

    @Override
    public McDonaldsStore get() {
        return new LittleMcDonaldsStore(generateName(), macOptions, macManagerProvider, clientProvider, workerProvider);
    }

    @Override
    protected String getType() {
        return "McDonalds Store";
    }

}
