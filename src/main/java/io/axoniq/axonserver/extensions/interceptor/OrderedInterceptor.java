package io.axoniq.axonserver.extensions.interceptor;

/**
 * Interface to enable ordering of interceptors.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface OrderedInterceptor {

    /**
     * Returns the sort order value for the interceptor. Lower values will be executed first. Default value is 0.
     *
     * @return sort order value for the interceptor
     */
    default int order() {
        return 0;
    }
}
