package com.dev.relish.builder.injector;

import lombok.Getter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class InjectByTypeObjectConfiguratorTest {

    public interface TestInterface {
    }

    public static class TestDependency
            implements TestInterface {
    }

    @Getter
    static class TestClass {
        @InjectByType
        private TestInterface testInterface;
    }

    @Test
    void testConfigureShouldNotInjectForNonAnnotatedField() {
        InjectByTypeObjectConfigurator configurator =
                new InjectByTypeObjectConfigurator();

        Object nonAnnotatedObject = new Object();
        configurator.configure(nonAnnotatedObject);

        assertNotNull(nonAnnotatedObject);
    }

    @Test
    void testConfigureShouldNotInjectForNonAnnotatedFields() {
        InjectByTypeObjectConfigurator configurator =
                new InjectByTypeObjectConfigurator();
        Object nonAnnotatedObject = new Object();
        configurator.configure(nonAnnotatedObject);
        assertNotNull(nonAnnotatedObject);
    }
}
