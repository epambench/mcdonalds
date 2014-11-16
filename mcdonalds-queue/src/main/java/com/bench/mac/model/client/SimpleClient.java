package com.bench.mac.model.client;

import com.bench.mac.api.enums.EatSpeed;
import com.bench.mac.api.enums.HungryStates;
import com.bench.mac.api.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SimpleClient implements Client {
    private static final Logger logger = LoggerFactory.getLogger(SimpleClient.class);

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
        logger.info(String.format("\"%s\": is eating", getName()));
        try {
            eatSpeed.timeUnit().sleep(eatSpeed.time());
        } catch (InterruptedException e) {
            logger.info(String.format("\"%s\": is mad! Someone interrupted him eating!", getName()));
        }
        logger.info(String.format("\"%s\": eat everything and is leaving MacDac happy", getName()));
    }

}
