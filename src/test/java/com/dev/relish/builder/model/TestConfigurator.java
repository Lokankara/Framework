package com.dev.relish.builder.model;

import com.dev.relish.builder.config.ObjectConfigurator;

public class TestConfigurator
        implements ObjectConfigurator {
    @Override
    public void configure(Object obj) {
        if (obj instanceof TestClass) {
            ((TestClass) obj).setConfigured(true);
        }
    }
}