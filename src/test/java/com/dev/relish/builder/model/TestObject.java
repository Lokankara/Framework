package com.dev.relish.builder.model;

import com.dev.relish.builder.injector.InjectRandomInt;
import lombok.Getter;

@Getter
public class TestObject {

    public static final int MIN_VALUE = 5;
    public static final int MAX_VALUE = 10;

    @InjectRandomInt(min = MIN_VALUE, max = MAX_VALUE)
    private int injectedValue;
    private String nonInjectedValue;
}
