package com.bench.mac.config;

import com.bench.mac.api.config.MacOptions;

public class MacOptionsImpl implements MacOptions{

    private int clients;
    private int consumers;

    public MacOptionsImpl() {
        this.clients = 0;
        this.consumers = 0;
    }

    public int getClients() {
        return clients;
    }

    public void setClients(int clients) {
        synchronized (this) {
            this.clients = clients;
        }
    }

    public int getConsumers() {
        return consumers;
    }

    public void setConsumers(int consumers) {
        synchronized (this) {
            this.consumers = consumers;
        }
    }

}
