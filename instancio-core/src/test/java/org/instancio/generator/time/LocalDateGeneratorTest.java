/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.instancio.generator.time;

import org.instancio.exception.InstancioApiException;
import org.instancio.generator.GeneratorContext;
import org.instancio.internal.random.RandomProvider;
import org.instancio.internal.random.RandomProviderImpl;
import org.instancio.settings.Settings;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LocalDateGeneratorTest {

    private static final Settings settings = Settings.create();
    private static final RandomProvider random = new RandomProviderImpl();
    private static final GeneratorContext context = new GeneratorContext(settings, random);

    private final LocalDateGenerator generator = new LocalDateGenerator(context);

    @Test
    void past() {
        generator.past();
        assertThat(generator.generate(random)).isBefore(LocalDate.now());
    }

    @Test
    void future() {
        generator.future();
        assertThat(generator.generate(random)).isAfter(LocalDate.now());
    }

    @Test
    void validateRange() {
        final LocalDate date = LocalDate.of(1970, 1, 1);

        assertThatThrownBy(() -> generator.range(date, date))
                .isExactlyInstanceOf(InstancioApiException.class)
                .hasMessage("Start date must be before end date by at least 1 day: 1970-01-01, 1970-01-01");

        generator.range(date, date.plusDays(1)); // no error
    }

    @Test
    void range() {
        final LocalDate min = LocalDate.now().plusDays(5);
        final LocalDate max = min.plusDays(1);
        generator.range(min, max);
        assertThat(generator.generate(random)).isBetween(min, max);
    }
}
