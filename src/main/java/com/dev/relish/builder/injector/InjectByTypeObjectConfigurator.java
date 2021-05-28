package com.dev.relish.builder.injector;

import com.dev.relish.builder.config.ObjectConfigurator;
import com.dev.relish.builder.factory.ObjectFactory;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class InjectByTypeObjectConfigurator
        implements ObjectConfigurator {

    @SneakyThrows
    @Override
    public void configure(Object obj) {
        Class<?> implClass = obj.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectByType.class)) {
                field.setAccessible(true);
                Object value = ObjectFactory
                        .getInstance()
                        .createObject(field.getType());
                field.set(obj, value);
            }
        }
    }
}
