package com.dev.relish.builder;

import com.dev.relish.builder.config.ObjectConfigurator;
import com.dev.relish.builder.injector.InjectRandomIntAnnotationObjectConfigurator;
import com.dev.relish.builder.model.TestObject;
import com.dev.relish.builder.model.TestObjectWithoutAnnotation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InjectRandomIntAnnotationObjectConfiguratorTest {

    ObjectConfigurator configurator;

    @BeforeEach
    void setUp() {
        configurator = new InjectRandomIntAnnotationObjectConfigurator();
    }

    @Test
    void configureShouldInjectRandomIntIntoAnnotatedField() {
        TestObject testObject = new TestObject();
        configurator.configure(testObject);

        int fieldValue = testObject.getInjectedValue();
        assertTrue(
                fieldValue >= TestObject.MIN_VALUE && fieldValue <= TestObject.MAX_VALUE,
                "The injected value should be within the specified range.");

        assertNull(testObject.getNonInjectedValue(),
                "The non-annotated field should remain null.");
    }

    @Test
    void configureShouldNotAffectNonAnnotatedFields() {
        TestObject testObject = new TestObject();
        configurator.configure(testObject);
        assertNull(testObject.getNonInjectedValue(),
                "The non-annotated field should remain null.");
    }

    @Test
    void configure_shouldHandleObjectWithNoAnnotatedFields() {
        TestObjectWithoutAnnotation testObject =
                new TestObjectWithoutAnnotation();
        configurator.configure(testObject);
        assertNull(testObject.getValue(),
                "No annotated fields, so nothing should be injected.");
    }

    @Test
    void configure_shouldNotInjectRandomIntForNonAnnotatedField() {
        InjectRandomIntAnnotationObjectConfigurator configurator =
                new InjectRandomIntAnnotationObjectConfigurator();
        Object nonAnnotatedObject = new Object();
        configurator.configure(nonAnnotatedObject);
        assertNotNull(nonAnnotatedObject);
    }
}
