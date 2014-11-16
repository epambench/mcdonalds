package com.bench.mac.model.worker;

import com.bench.mac.api.enums.HungryStates;
import com.bench.mac.api.model.Client;
import com.bench.mac.api.model.Worker;
import com.bench.mac.config.guice.logger.InjectLogger;
import org.slf4j.Logger;

class SimpleWorker implements Worker {
    @InjectLogger
    private Logger logger;

    private final String name;

    SimpleWorker(final String name) {
        this.name = name;
    }

    public boolean processClient(final Client client) {
        logger.info(String.format("Worker \"%s\" is processing client: \"%s\"", getName(), client.getName()));
        try {
            HungryStates hungryState = client.getHungryState();
            hungryState.timeUnit().sleep(hungryState.time());
        } catch (InterruptedException e) {
            logger.info(String.format("Client \"%s\" is mad! Someone interrupted the worker \"%s\"!",
                    client.getName(), getName()));
            return false;
        }
        logger.info(String.format("Worker \"%s\" has process client \"%s\" successfully", getName(), client.getName()));
        return true;
    }

    @Override
    public String getName() {
        return name;
    }
}
