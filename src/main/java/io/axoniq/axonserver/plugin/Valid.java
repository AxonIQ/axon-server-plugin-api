package io.axoniq.axonserver.plugin;

import java.util.function.Consumer;

/**
 * Implementation of {@link Validated} to be used for valid configuration.
 *
 * @author Stefan Andjelkovic
 * @author Sara Pellegrini
 * @since 2023.0.0
 */
public final class Valid<T> implements Validated<T> {

    private final T value;

    /**
     * Constructs a new {@link Valid} instance with the provided value that was validated and deemed valid
     *
     * @param value value that was validated
     */
    public Valid(T value) {
        this.value = value;
    }

    @Override
    public Validated<T> ifValid(Consumer<T> consumer) {
        consumer.accept(value);
        return this;
    }
}
