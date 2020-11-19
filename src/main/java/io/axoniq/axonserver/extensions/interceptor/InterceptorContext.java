package io.axoniq.axonserver.extensions.interceptor;

import java.util.Map;
import java.util.Set;

/**
 * Context information provided to all intercepted requests. The same context instance is shared in the whole request
 * flow.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface InterceptorContext extends OrderedInterceptor {

    /**
     * @return the name of the Axon Server context for the request
     */
    String context();

    /**
     * @return the name of the application sending the request
     */
    String principal();

    /**
     * @return the roles of the application sending the request
     */
    Set<String> principalRoles();

    /**
     * @return a map of properties for the application
     */
    Map<String, String> principalMetaData();

    /**
     * Registers an action to compensate state changes made by the interceptor when the request fails to execute
     * successfully.
     *
     * @param compensatingAction the action to execute when the request failed
     */
    void registerCompensatingAction(Runnable compensatingAction);

    /**
     * Enables the interceptor to add custom data to the interceptor context. This data can be used in other
     * interceptors
     * for the same request.
     *
     * @param key   the key of the item
     * @param value the value
     */
    void addDetails(String key, Object value);

    /**
     * Retrieves custom data from the interceptor context.
     *
     * @param key the key of the custom data field
     * @return the custom data
     */
    Object getDetails(String key);
}
