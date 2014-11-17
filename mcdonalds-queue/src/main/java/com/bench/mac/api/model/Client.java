package com.bench.mac.api.model;

import com.bench.mac.api.enums.HungryStates;

public interface Client extends Person {
    HungryStates getHungryState();
    void eat();
}
