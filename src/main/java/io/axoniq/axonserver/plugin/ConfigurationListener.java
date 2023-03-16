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
     * Callback invoked before a new configuration is effectively saved.
     * The implementation can reject the new configuration before it will be saved, returning an {@link Invalid} result.
     * By default, the method returns a {@link Valid} result for backward compatibility.
     *
     * @param context       the name of the context
     * @param configuration the proposed new configuration properties for the context
     * @return validation result
     */
    default <R extends Map<String, ?>> Validated<R> validate(String context, R configuration) {
        return new Valid<>(configuration);
    }


    /**
     * Callback that is invoked when Axon Server has removed configuration for a context.
     *
     * @param context the name of the context
     */
    void removed(String context);

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
