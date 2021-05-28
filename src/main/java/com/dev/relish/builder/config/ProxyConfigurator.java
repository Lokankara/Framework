package com.dev.relish.builder.config;

public interface ProxyConfigurator {
    <T> T configureProxy(T object, Class<? extends T> type);
}