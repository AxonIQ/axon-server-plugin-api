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
 * The result of the validation of the configuration.
 * The two implementation proposed ({@link Valid} and {@link Invalid}) should be enough for all use case.
 *
 * @author Stefan Andjelkovic
 * @author Sara Pellegrini
 * @since 2023.0.0
 */
public interface Validated<T> {

    /**
     * Invoke the consumer only if the result is valid.
     * Returns {@code this} for fluid API.
     *
     * @param consumer the consumer of the valid result
     * @return this
     */
    default Validated<T> ifValid(Consumer<T> consumer) {
        return this;
    }

    /**
     * Invoke the consumer of {@link ConfigurationError} only if the result is invalid.
     * Returns {@code this} for fluid API.
     *
     * @param consumer the consumer of the errors
     * @return this
     */
    default Validated<T> ifInvalid(Consumer<Iterable<ConfigurationError>> consumer) {
        return this;
    }

}
