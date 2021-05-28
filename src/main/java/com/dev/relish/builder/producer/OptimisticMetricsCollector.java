package com.dev.relish.builder.producer;

import com.dev.relish.builder.injector.InjectRandomInt;

public class OptimisticMetricsCollector
        implements MetricsCollector {

    @InjectRandomInt(min = 200, max = 700)
    private int initialMetric;
    private final int delta = 2;

    private int counter = 0;

    @Override
    @Deprecated
    public double collect(String devRelActivity) {
        counter++;
        if (counter == 1) {
            return initialMetric;
        }
        return counter * initialMetric * delta;
    }
}