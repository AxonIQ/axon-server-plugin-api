package io.axoniq.axonserver.plugin;

import java.util.Collections;
import java.util.List;

/**
 * Describes the configuration that can be set through Axon Server. As there can be multiple configuration objects in
 * a single interceptor package, each configuration has a name. The configuration also contains a list of {@link
 * PluginPropertyDefinition}
 * objects, that define the properties that can be set for this configuration.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public class Configuration {

    public static final Configuration DEFAULT = new Configuration(Collections.emptyList(), "Configuration");
    private final List<PluginPropertyDefinition> properties;
    private final String name;

    /**
     * Constructs a configuration object.
     *
     * @param properties list of properties defined by this configuration
     * @param name       the name of the configuration
     */
    public Configuration(List<PluginPropertyDefinition> properties, String name) {
        this.properties = Collections.unmodifiableList(properties);
        this.name = name;
    }

    /**
     * Returns the name of the configuration
     *
     * @return the name of the configuration
     */
    public String name() {
        return name;
    }

    /**
     * Returns a list of properties defined by this configuration.
     *
     * @return a list of properties defined by this configuration
     */
    public List<PluginPropertyDefinition> properties() {
        return properties;
    }
}
