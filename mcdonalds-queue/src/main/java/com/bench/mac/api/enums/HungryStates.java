package com.bench.mac.api.enums;

import java.util.concurrent.TimeUnit;

public enum HungryStates {
    FED(500L, TimeUnit.MILLISECONDS),
    HUNGRY(1L, TimeUnit.SECONDS),
    EAT_ELEPHANT(5L, TimeUnit.SECONDS);

    private final TimeUnit timeUnit;
    private final long time;

    HungryStates(long time, TimeUnit timeUnit) {
        this.time = time;
        this.timeUnit = timeUnit;
    }

    public TimeUnit timeUnit() {
        return timeUnit;
    }

    public long time() {
        return time;
    }

    public static class Random extends RandomEnum<HungryStates> {
        public Random() {
            super(HungryStates.class);
        }
    }
}
