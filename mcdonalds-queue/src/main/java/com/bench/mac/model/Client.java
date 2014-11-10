package com.bench.mac.model;

import com.bench.mac.api.enums.HungryStates;

public class Client {

    private String name;
    private int order;
    private long eatTime;
    private HungryStates hungryState;
    private int queueNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public long getEatTime() {
        return eatTime;
    }

    public void setEatTime(long eatTime) {
        this.eatTime = eatTime;
    }

    public HungryStates getHungryState() {
        return hungryState;
    }

    public void setHungryState(HungryStates hungryState) {
        this.hungryState = hungryState;
    }

    public int getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(int queueNumber) {
        this.queueNumber = queueNumber;
    }
}
