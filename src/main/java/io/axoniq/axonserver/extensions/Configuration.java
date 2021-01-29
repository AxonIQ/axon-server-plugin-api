package io.axoniq.axonserver.extensions;

import java.util.Collections;
import java.util.List;

/**
 * @author Marc Gathier
 */
public class Configuration {

    public static final Configuration DEFAULT = new Configuration(Collections.emptyList(), "Configuration");
    private final List<ExtensionPropertyDefinition> properties;
    private final String name;

    public Configuration(List<ExtensionPropertyDefinition> properties, String name) {
        this.properties = Collections.unmodifiableList(properties);
        this.name = name;
    }

    public String name() {
        return name;
    }

    public List<ExtensionPropertyDefinition> properties() {
        return properties;
    }
}
