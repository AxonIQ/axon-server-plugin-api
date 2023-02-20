package io.axoniq.axonserver.plugin;

import java.util.Objects;
import java.util.StringJoiner;
import javax.annotation.Nonnull;

/**
 * Used to describe a single error found during the validation of the configuration.
 *
 * @author Stefan Andjelkovic
 * @author Sara Pellegrini
 * @since 2023.0.0
 */
public class ConfigurationError {

    private final String fieldKey;

    private final String message;

    /**
     * Creates a new configuration error with provided field key and error message.
     * The field key property should match the property path of the {@link Configuration} in the plugin configuration
     *
     * @param fieldKey key to match the invalid configuration field
     * @param message  description of the validation error
     */
    public ConfigurationError(@Nonnull String fieldKey, @Nonnull String message) {
        this.fieldKey = fieldKey;
        this.message = message;
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConfigurationError that = (ConfigurationError) o;
        return fieldKey.equals(that.fieldKey) && message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldKey, message);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ConfigurationError.class.getSimpleName() + "[", "]")
                .add("fieldKey='" + fieldKey + "'")
                .add("message='" + message + "'")
                .toString();
    }
}
