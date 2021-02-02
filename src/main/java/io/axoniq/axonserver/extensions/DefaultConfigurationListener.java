package io.axoniq.axonserver.extensions;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Default {@link ConfigurationListener} implementation that keeps a map of configuration values per
 * context, which is updated when the configuration is changed through Axon Server.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public class DefaultConfigurationListener implements ConfigurationListener {

    private final Configuration configuration;
    private final Map<String, Map<String, ?>> valuesPerContext = new HashMap<>();

    /**
     * Constructor.
     *
     * @param name       the name of the configuration
     * @param properties the properties that are defined in this configuration
     */
    public DefaultConfigurationListener(String name, List<ExtensionPropertyDefinition> properties) {
        this.configuration = new Configuration(properties, name);
    }

    @Override
    public void updated(String context, Map<String, ?> configuration) {
        valuesPerContext.put(context, configuration);
    }

    /**
     * Returns the configuration defined in this listener.
     *
     * @return the {@link Configuration} defined in this listener.
     */
    @Override
    public Configuration configuration() {
        return configuration;
    }

    /**
     * Finds the value of the property in the context. If there are no properties set for the context it returns null.
     *
     * @param context  the name of the context
     * @param property the name of the property
     * @param <R>      the return type
     * @return the value of the property in the context
     */
    public <R> R get(String context, String property) {
        return (R) valuesPerContext.getOrDefault(context, Collections.emptyMap())
                                   .get(property);
    }
}
