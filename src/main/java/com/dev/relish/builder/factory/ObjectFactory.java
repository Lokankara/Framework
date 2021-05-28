package com.dev.relish.builder.factory;

import com.dev.relish.builder.config.Config;
import com.dev.relish.builder.config.ObjectConfigurator;
import com.dev.relish.builder.config.ProxyConfigurator;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;

public class ObjectFactory {

    private static final ObjectFactory INSTANCE = new ObjectFactory();
    private final Config config;
    private final List<ObjectConfigurator> objectConfigurators;
    private final List<ProxyConfigurator> proxyConfigurators;
    private final Map<Class<?>, Object> singletonInstances =
            new ConcurrentHashMap<>();

    private ObjectFactory() {
        String basePackageName =
                System.getProperty("base.package.name", "com.dev.relish");
        config = new Config(basePackageName);

        objectConfigurators = config
                .getImplementations(ObjectConfigurator.class)
                .stream()
                .map(this::createInstance)
                .collect(toList());

        proxyConfigurators = config
                .getImplementations(ProxyConfigurator.class)
                .stream()
                .map(this::createInstance)
                .collect(toList());
    }

    public static ObjectFactory getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        if (singletonInstances.containsKey(type)) {
            return (T) singletonInstances.get(type);
        }

        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplementation(type);
        }

        T t = createInstance(implClass);
        if (isSingleton(implClass)) {
            singletonInstances.put(implClass, t);
        }
        configureObject(t);
        initializePostConstruct(t);
        return wrapWithProxyConfigurators(t, implClass);
    }

    private boolean isSingleton(Class<?> implClass) {
        return implClass.isAnnotationPresent(Singleton.class);
    }

    @SneakyThrows
    private <T> T createInstance(Class<? extends T> type) {
        return type.getDeclaredConstructor().newInstance();
    }

    private <T> void configureObject(T t) {
        objectConfigurators.forEach(configurator -> configurator.configure(t));
    }

    private <T> T wrapWithProxyConfigurators(T t, Class<? extends T> type) {
        for (ProxyConfigurator proxyConfigurator : proxyConfigurators) {
            t = proxyConfigurator.configureProxy(t, type);
        }
        return t;
    }

    @SneakyThrows
    private void initializePostConstruct(Object obj) {
        for (Method method : obj.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.setAccessible(true);
                method.invoke(obj);
            }
        }
    }
}
