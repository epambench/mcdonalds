package com.bench.mac.api.model;

public interface MacManager extends Person {
    void assignTheStore(final McDonaldsStore mcDonaldsStore);

    boolean pickClient();

    void pickWorker();

    void orderToProcess();
}
