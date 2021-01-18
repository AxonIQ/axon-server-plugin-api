package io.axoniq.axonserver.extensions;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Marc Gathier
 */
public interface ConfigurationListener {

    String id();

    void updated(String context, Map<String, ?> configuration);

    default List<ExtensionPropertyDefinition> attributes() {
        return Collections.emptyList();
    }
}
