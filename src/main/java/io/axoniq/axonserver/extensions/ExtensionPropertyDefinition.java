package io.axoniq.axonserver.extensions;

import java.util.List;

/**
 * @author Marc Gathier
 */
public class ExtensionPropertyDefinition {

    private final String id;
    private final String name;
    private final int cardinality;
    private final String defaultValue;
    private final AttributeType type;
    private final String description;
    private final List<String> optionLabels;
    private final List<String> optionValues;

    public ExtensionPropertyDefinition(String id, String name, int cardinality, String defaultValue,
                                       AttributeType type, String description) {
        this(id, name, cardinality, defaultValue, type, description, null, null);
    }

    public ExtensionPropertyDefinition(String id, String name, int cardinality, String defaultValue,
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

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public int cardinality() {
        return cardinality;
    }

    public String defaultValue() {
        return defaultValue;
    }

    public AttributeType type() {
        return type;
    }

    public List<String> optionLabels() {
        return optionLabels;
    }

    public List<String> optionValues() {
        return optionValues;
    }

    public String description() {
        return description;
    }
}
