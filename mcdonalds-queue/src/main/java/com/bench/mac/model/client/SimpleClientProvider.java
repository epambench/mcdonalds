package com.bench.mac.model.client;

import com.bench.mac.api.enums.EatSpeed;
import com.bench.mac.api.enums.HungryStates;
import com.bench.mac.api.model.ClientProvider;
import com.bench.mac.model.AbstractMacProvider;

import javax.inject.Inject;

public class SimpleClientProvider extends AbstractMacProvider implements ClientProvider {

    @Inject
    private SimpleClientProvider() {
    }

    @Override
    public SimpleClient get() {
        EatSpeed.Random eatSpeedRandom = new EatSpeed.Random();
        HungryStates.Random hungryStatesRandom = new HungryStates.Random();
        return new SimpleClient(generateName(), eatSpeedRandom.next(), hungryStatesRandom.next());
    }

    @Override
    protected String getType() {
        return "Simple Client";
    }
}
