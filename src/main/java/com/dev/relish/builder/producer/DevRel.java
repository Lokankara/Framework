package com.dev.relish.builder.producer;

import com.dev.relish.builder.analyzer.DevRelAnalyzer;
import com.dev.relish.builder.injector.InjectByType;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class DevRel {

    @InjectByType
    private DevRelAnalyzer analyzer;
    @InjectByType
    private MetricsCollector metricCollector;
    @InjectByType
    private DevRelActionProducer producer;

    @PostConstruct
    public void init() {
        log.info("************** " + analyzer);
    }

    @Deprecated
    public void executeDevRelStrategy() {
        String devRelActivity = analyzer.findMostCriticalActivity();
        double howMuch = metricCollector.collect(devRelActivity);
        producer.produce(devRelActivity);
        log.info("improved by: " + (metricCollector.collect(
                devRelActivity) - howMuch) * 100 + " DevDollars");
    }
}
