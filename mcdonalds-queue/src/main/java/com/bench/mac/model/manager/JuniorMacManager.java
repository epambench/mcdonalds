package com.bench.mac.model.manager;

import com.bench.mac.api.model.MacManager;
import com.bench.mac.config.guice.logger.InjectLogger;
import org.slf4j.Logger;

class JuniorMacManager implements MacManager{
    @InjectLogger
    private Logger logger;

    private final String name;

    JuniorMacManager(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
