package com.bench.mac.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractMacProvider {

    private Logger logger = LoggerFactory.getLogger(AbstractMacProvider.class);

    private static int personalNumberCounter = 0;

    protected String generateName() {
        String name;
        synchronized (AbstractMacProvider.class) {
            personalNumberCounter++;
            name = String.format("%s with ID %d", getType(), personalNumberCounter);
        }
        logger.info(String.format("Generating new %s with name \"%s\"", getType(), name));
        return name;
    }

    protected abstract String getType();
}
