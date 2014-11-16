package com.bench.mac.model.worker;

import com.bench.mac.api.enums.HungryStates;
import com.bench.mac.api.model.Client;
import com.bench.mac.api.model.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

class SimpleWorker implements Worker {
    private static final Logger logger = LoggerFactory.getLogger(SimpleWorker.class);

    private final String name;

    private AtomicBoolean busy = new AtomicBoolean(false);

    SimpleWorker(final String name) {
        this.name = name;
    }

    public boolean processClient(final Client client) {
        if (busy.get()) {
            throw new IllegalStateException("\"%s\": busy now, can't work with 2 guys same time!");
        }
        busy.set(true);
        logger.info(String.format("\"%s\": processing client: \"%s\"", getName(), client.getName()));
        try {
            HungryStates hungryState = client.getHungryState();
            hungryState.timeUnit().sleep(hungryState.time());
        } catch (InterruptedException e) {
            logger.info(String.format("\"%s\": interrupted! Client \"%s\" is mad!",
                    client.getName(), getName()));
            return false;
        }
        logger.info(String.format("\"%s\": client processed \"%s\" successfully", getName(), client.getName()));
        busy.set(false);
        return true;
    }

    @Override
    public boolean busy() {
        return busy.get();
    }

    @Override
    public String getName() {
        return name;
    }
}
