package com.bench.mac.api.enums;

import java.util.concurrent.TimeUnit;

public enum EatSpeed {
    BOY(100L, TimeUnit.MILLISECONDS),
    GIRL(300L, TimeUnit.MILLISECONDS),
    OFFICE_WORKER(5L, TimeUnit.MILLISECONDS);

    private final TimeUnit timeUnit;
    private final long time;

    EatSpeed(long time, TimeUnit timeUnit) {
        this.time = time;
        this.timeUnit = timeUnit;
    }

    public TimeUnit timeUnit() {
        return timeUnit;
    }

    public long time() {
        return time;
    }

    public static class Random extends RandomEnum<EatSpeed> {
        public Random() {
            super(EatSpeed.class);
        }
    }
}
