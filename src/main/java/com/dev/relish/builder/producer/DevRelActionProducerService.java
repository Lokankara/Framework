package com.dev.relish.builder.producer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DevRelActionProducerService
        implements DevRelActionProducer {
    @Override
    public void produce(String criticalActivity) {
        log.info("starting DevRel activity: " + criticalActivity);
        log.info("providing non tech sessions");
        log.info("Gathering best potential speakers");
        log.info("Providing speaker courses");
    }
}
