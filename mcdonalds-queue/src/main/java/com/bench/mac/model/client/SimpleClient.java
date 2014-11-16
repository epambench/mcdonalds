package com.bench.mac.model.client;

import com.bench.mac.api.enums.EatSpeed;
import com.bench.mac.api.enums.HungryStates;
import com.bench.mac.api.model.Client;
import com.bench.mac.config.guice.logger.InjectLogger;
import org.slf4j.Logger;

class SimpleClient implements Client {
    @InjectLogger
    private Logger logger;

    private final String name;
    private final EatSpeed eatSpeed;
    private final HungryStates hungryState;

    SimpleClient(final String name, final EatSpeed eatSpeed, final HungryStates hungryState) {
        this.name = name;
        this.eatSpeed = eatSpeed;
        this.hungryState = hungryState;
    }

    public String getName() {
        return name;
    }

    public HungryStates getHungryState() {
        return hungryState;
    }

    @Override
    public void eat() {
        logger.info(String.format("Client \"%s\" is eating", getName()));
        try {
            eatSpeed.timeUnit().sleep(eatSpeed.time());
        } catch (InterruptedException e) {
            logger.info(String.format("Client \"%s\"is mad! Someone interrupted him eating!", getName()));
        }
        logger.info(String.format("Client \"%s\" eat everything and is leaving MacDac happy", getName()));
    }

}
