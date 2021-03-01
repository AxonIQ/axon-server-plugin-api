package io.axoniq.axonserver.plugin;

/**
 * Values for the cardinality of an {@link PluginPropertyDefinition}.
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
