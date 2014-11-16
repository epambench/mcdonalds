package com.bench.mac.api.model;

public interface Worker extends Person {
    boolean processClient(final Client client);
    boolean busy();
}
