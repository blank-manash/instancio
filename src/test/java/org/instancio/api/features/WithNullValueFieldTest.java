package org.instancio.api.features;

import org.instancio.Instancio;
import org.instancio.pojo.basic.ClassWithInitializedField;
import org.instancio.pojo.basic.StringHolder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Generators.nullValue;

class WithNullValueFieldTest {

    @Test
    @DisplayName("A null field should remain null")
    void generatesNullValue() {
        final StringHolder holder = Instancio.of(StringHolder.class)
                .with("value", nullValue())
                .create();

        assertThat(holder.getValue()).isNull();
    }

    @Test
    @DisplayName("Initialized field should be set to null")
    void overwritesInitializedFieldValue() {
        final ClassWithInitializedField holder = Instancio.of(ClassWithInitializedField.class)
                .with("value", nullValue())
                .create();

        assertThat(holder.getValue()).isNull();
    }
}