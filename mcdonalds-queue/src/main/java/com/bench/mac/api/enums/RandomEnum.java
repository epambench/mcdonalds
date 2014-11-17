package com.bench.mac.api.enums;

class RandomEnum<E extends Enum> {
    private static final java.util.Random RND = new java.util.Random();
    private final E[] values;

    public RandomEnum(Class<E> token) {
        values = token.getEnumConstants();
    }

    public E next() {
        return values[RND.nextInt(values.length)];
    }
}
