package io.axoniq.axonserver.extensions.interceptor;

/**
 * @author Marc Gathier
 */
public interface OrderedInterceptor {

    default int order() {
        return 0;
    }
}
