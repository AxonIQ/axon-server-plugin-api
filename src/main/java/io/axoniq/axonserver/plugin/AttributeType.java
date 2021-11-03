package io.axoniq.axonserver.plugin;

/**
 * Enum of attribute types for configurable properties used in a service.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public enum AttributeType {
    /**
     * Mapped to a Java {@link String}.
     */
    STRING,
    /**
     * Mapped to a Java {@link String}. Displayed in Axon Server UI as text entry block.
     */
    TEXT,
    /**
     * Mapped to a Java {@link Integer}.
     */
    INTEGER,
    /**
     * Mapped to a Java {@link Long}.
     */
    LONG,
    /**
     * Mapped to a Java {@link Float}.
     */
    FLOAT,
    /**
     * Mapped to a Java {@link Double}.
     */
    DOUBLE,
    /**
     * Mapped to a Java {@link Boolean}.
     */
    BOOLEAN,
    /**
     * Mapped to a Java {@link String}.
     */
    PASSWORD
}
