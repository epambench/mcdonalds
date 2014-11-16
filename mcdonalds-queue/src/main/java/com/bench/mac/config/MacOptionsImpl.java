package com.bench.mac.config;

import com.bench.mac.api.config.MacOptions;

public class MacOptionsImpl implements MacOptions {

    private int[] clients;
    private int workers;

    public MacOptionsImpl() {
        this.clients = new int[0];
        this.workers = 0;
    }

    public int[] getClients() {
        return clients;
    }

    public void setClients(int[] clients) {
        synchronized (this) {
            this.clients = clients;
        }
    }

    public int getWorkers() {
        return workers;
    }

    public void setWorkers(int workers) {
        synchronized (this) {
            this.workers = workers;
        }
    }

}
