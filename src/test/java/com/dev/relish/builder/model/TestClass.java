package com.dev.relish.builder.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class TestClass
        implements TestInterface {

    private boolean configured = false;
    private boolean postConstruct = true;

    @Override
    public void testMethod() {
        log.info("Test method executed");
    }

    public boolean isPostConstructCalled() {
        return postConstruct;
    }
}
