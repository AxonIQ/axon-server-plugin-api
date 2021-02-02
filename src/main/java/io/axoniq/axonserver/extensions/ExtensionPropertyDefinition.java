package io.axoniq.axonserver.extensions;

import java.util.List;

/**
 * Defines a property that can be configured through Axon Server. Use the {@link Builder} to create an instance.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public class ExtensionPropertyDefinition {

    private final String id;
    private final String name;
    private final Cardinality cardinality;
    private final Object defaultValue;
    private final AttributeType type;
    private final String description;
    private final List<String> optionLabels;
    private final List<String> optionValues;

    private ExtensionPropertyDefinition(String id, String name, Cardinality cardinality, Object defaultValue,
                                        AttributeType type, String description, List<String> optionLabels,
                                        List<String> optionValues) {
        this.id = id;
        this.name = name;
        this.cardinality = cardinality;
        this.defaultValue = defaultValue;
        this.type = type;
        this.description = description;
        this.optionLabels = optionLabels;
        this.optionValues = optionValues;
    }

    /**
     * Returns the internal id of the property.
     *
     * @return the id of the property
     */
    public String id() {
        return id;
    }

    /**
     * Returns the name of the property.
     *
     * @return the name of the property
     */
    public String name() {
        return name;
    }

    /**
     * Returns the cardinality of the property (default value is {@code Cardinality.SINGLE}).
     *
     * @return the cardinality of the property
     */
    public Cardinality cardinality() {
        return cardinality;
    }

    /**
     * Returns the default value for the property. The type of the default value should be correct for the
     * {@code type()} value. If the cardinality is {@code Cardinality.MULTI}), the default value should be a
     * {@link List}.
     *
     * @return the default value for the property
     */
    public Object defaultValue() {
        return defaultValue;
    }

    /**
     * Returns the type of the property (default is {@code AttributeType.STRING}).
     *
     * @return the type of the property
     */
    public AttributeType type() {
        return type;
    }

    /**
     * Returns labels to display when the property has a fixed set of values.
     *
     * @return labels to display when the property has a fixed set of values
     */
    public List<String> optionLabels() {
        return optionLabels;
    }

    /**
     * Returns values for the property when it has a fixed set of values.
     *
     * @return values for the property when it has a fixed set of values
     */
    public List<String> optionValues() {
        return optionValues;
    }

    /**
     * Returns a description for th property.
     *
     * @return a description for th property
     */
    public String description() {
        return description;
    }

    /**
     * Creates a builder to build an {@link ExtensionPropertyDefinition} object.
     *
     * @param id   the id of the property
     * @param name the name of the property
     * @return the builder to further configure the property
     */
    public static Builder newBuilder(String id, String name) {
        return new Builder(id, name);
    }

    public static class Builder {

        private final String id;
        private final String name;
        private Cardinality cardinality = Cardinality.SINGLE;
        private String description;
        private AttributeType type = AttributeType.STRING;
        private Object defaultValue;
        private List<String> optionLabels;
        private List<String> optionValues;

        private Builder(String id, String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * Adds a description for the property.
         *
         * @param description the description for the property
         * @return the builder
         */
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Adds a v for the property.
         *
         * @param defaultValue the defaultValue for the property
         * @return the builder
         */
        public Builder defaultValue(Object defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        /**
         * Adds options for the property. The order of the labels and values should match and the list should have
         * the same size.
         *
         * @param optionLabels labels for the options
         * @param optionValues values for the options
         * @return the builder
         */
        public Builder options(List<String> optionLabels, List<String> optionValues) {
            assert optionLabels.size() == optionValues.size();
            this.optionLabels = optionLabels;
            this.optionValues = optionValues;
            return this;
        }

        /**
         * Sets the type for the property.
         *
         * @param type the type for the property
         * @return the builder
         */
        public Builder type(AttributeType type) {
            this.type = type;
            return this;
        }

        /**
         * Sets the cardinality for the property.
         *
         * @param cardinality the cardinality for the property
         * @return the builder
         */
        public Builder cardinality(Cardinality cardinality) {
            this.cardinality = cardinality;
            return this;
        }

        /**
         * Builds the {@link ExtensionPropertyDefinition} object.
         *
         * @return the {@link ExtensionPropertyDefinition}
         */
        public ExtensionPropertyDefinition build() {
            return new ExtensionPropertyDefinition(id,
                                                   name,
                                                   cardinality,
                                                   defaultValue,
                                                   type,
                                                   description,
                                                   optionLabels,
                                                   optionValues);
        }
    }
}
