package io.axoniq.axonserver.extensions;

/**
 * Values for the cardinality of an {@link ExtensionPropertyDefinition}.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public enum Cardinality {
    /**
     * One value
     */
    SINGLE,
    /**
     * Multiple values, value is set as a list.
     */
    MULTI;
}
