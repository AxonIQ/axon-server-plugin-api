/*
 * Copyright (c) 2020-2026. Axoniq B.V.
 *
 * Licensed under the Axoniq Terms of Service (the "Terms"). You may not
 * use this file except in compliance with the Terms, available at
 * https://www.axoniq.io/legal/terms-of-service
 */

package io.axoniq.axonserver.plugin;

import java.util.function.Consumer;

/**
 * Implementation of {@link Validated} to be used for invalid configuration.
 *
 * @author Stefan Andjelkovic
 * @author Sara Pellegrini
 * @since 2023.0.0
 */
public final class Invalid<T> implements Validated<T> {

    private final Iterable<ConfigurationError> errors;

    /**
     * Constructs a new {@link Invalid} instance with the errors detected during the validation.
     *
     * @param errors the errors detected during the validation
     */
    public Invalid(Iterable<ConfigurationError> errors) {
        this.errors = errors;
    }

    @Override
    public Validated<T> ifInvalid(Consumer<Iterable<ConfigurationError>> consumer) {
        consumer.accept(errors);
        return this;
    }
}