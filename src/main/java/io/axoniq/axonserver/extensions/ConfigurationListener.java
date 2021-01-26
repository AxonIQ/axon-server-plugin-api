package io.axoniq.axonserver.extensions;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Interface for a component that receives configuration information from Axon Server.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface ConfigurationListener {

    /**
     * @return a category for this configuration listener
     */
    String category();

    /**
     * Callback that is invoked when Axon Server has updated configuration for a context.
     *
     * @param context       the name of the context
     * @param configuration the new configuration properties for the context
     */
    void updated(String context, Map<String, ?> configuration);

    /**
     * Returns a list of properties that can be set in Axon Server.s
     *
     * @return list of properties that can be set in Axon Server
     */
    default List<ExtensionPropertyDefinition> attributes() {
        return Collections.emptyList();
    }
}
