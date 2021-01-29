package io.axoniq.axonserver.extensions;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marc Gathier
 */
public class DefaultConfigurationListener implements ConfigurationListener {

    private final Configuration configuration;
    private final Map<String, Map<String, ?>> valuesPerContext = new HashMap<>();

    public DefaultConfigurationListener(String name, List<ExtensionPropertyDefinition> properties) {
        this.configuration = new Configuration(properties, name);
    }

    @Override
    public void updated(String context, Map<String, ?> configuration) {
        valuesPerContext.put(context, configuration);
    }

    @Override
    public Configuration configuration() {
        return configuration;
    }

    public <R> R get(String context, String property) {
        return (R) valuesPerContext.getOrDefault(context, Collections.emptyMap())
                                   .get(property);
    }
}
