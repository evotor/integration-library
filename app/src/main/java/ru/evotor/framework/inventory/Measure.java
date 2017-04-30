package ru.evotor.framework.inventory;

/**
 * Created by a.kuznetsov on 30/04/2017.
 */

public class Measure {
    private final String uuid;
    private final String name;
    private final int precision;

    public Measure(String uuid, String name, int precision) {
        this.uuid = uuid;
        this.name = name;
        this.precision = precision;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public int getPrecision() {
        return precision;
    }
}
