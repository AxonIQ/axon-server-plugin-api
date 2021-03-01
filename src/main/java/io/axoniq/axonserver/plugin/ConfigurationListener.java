package io.axoniq.axonserver.plugin;

import java.util.Map;

/**
 * Interface for a component that receives configuration information from Axon Server.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface ConfigurationListener {

    /**
     * Callback that is invoked when Axon Server has updated configuration for a context.
     *
     * @param context       the name of the context
     * @param configuration the new configuration properties for the context
     */
    void updated(String context, Map<String, ?> configuration);

    /**
     * Returns {@link Configuration} object containing a list of properties that can be set in Axon Server and
     * a name for this configuration.
     *
     * @return list of properties that can be set in Axon Server
     */
    default Configuration configuration() {
        return Configuration.DEFAULT;
    }
}
