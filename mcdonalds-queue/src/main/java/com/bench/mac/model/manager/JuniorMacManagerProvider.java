package com.bench.mac.model.manager;

import com.bench.mac.api.model.MacManagerProvider;
import com.bench.mac.model.AbstractMacProvider;

import javax.inject.Inject;

public class JuniorMacManagerProvider extends AbstractMacProvider implements MacManagerProvider {

    @Inject
    private JuniorMacManagerProvider() {
    }

    @Override
    public JuniorMacManager get() {
        return new JuniorMacManager(generateName());
    }

    @Override
    protected String getType() {
        return "Junior Mac Manager";
    }
}
