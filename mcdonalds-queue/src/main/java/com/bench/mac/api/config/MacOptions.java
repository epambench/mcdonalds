package com.bench.mac.api.config;

public interface MacOptions {
    int[] getClients();

    void setClients(int[] clients);

    int getWorkers();

    void setWorkers(int consumers);
}
