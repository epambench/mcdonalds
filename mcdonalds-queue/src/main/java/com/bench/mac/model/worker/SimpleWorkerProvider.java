package com.bench.mac.model.worker;

import com.bench.mac.api.model.WorkerProvider;
import com.bench.mac.model.AbstractMacProvider;

import javax.inject.Inject;

public class SimpleWorkerProvider extends AbstractMacProvider implements WorkerProvider{

    @Inject
    private SimpleWorkerProvider() {
    }

    @Override
    public SimpleWorker get() {
        return new SimpleWorker(generateName());
    }

    @Override
    protected String getType() {
        return "Simple Worker";
    }
}
