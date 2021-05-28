package com.dev.relish.builder;

import com.dev.relish.builder.factory.ObjectFactory;
import com.dev.relish.builder.producer.DevRel;

public class BuilderApplication {

    public static void main(String[] args) {
        ObjectFactory.getInstance().createObject(DevRel.class);
        ObjectFactory.getInstance().createObject(DevRel.class);
        ObjectFactory.getInstance().createObject(DevRel.class);
        ObjectFactory.getInstance().createObject(DevRel.class);
        DevRel devRel = ObjectFactory.getInstance().createObject(DevRel.class);
        devRel.executeDevRelStrategy();
        devRel.executeDevRelStrategy();
    }
}
