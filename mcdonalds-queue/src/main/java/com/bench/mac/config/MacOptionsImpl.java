package com.bench.mac.config;

import com.bench.mac.api.config.MacOptions;

public class MacOptionsImpl implements MacOptions{

    private int clients = 0;
    private int consumers = 0;

    public int getClients() {
        return clients;
    }

    public synchronized void setClients(int clients) {
        this.clients = clients;
    }

    public int getConsumers() {
        return consumers;
    }

    public synchronized void setConsumers(int consumers) {
        this.consumers = consumers;
    }

}
