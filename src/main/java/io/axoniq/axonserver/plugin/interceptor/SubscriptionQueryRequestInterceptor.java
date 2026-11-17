/*
 * Copyright (c) 2020-2026. Axoniq B.V.
 *
 * Licensed under the Axoniq Terms of Service (the "Terms"). You may not
 * use this file except in compliance with the Terms, available at
 * https://www.axoniq.io/legal/terms-of-service
 */

package io.axoniq.axonserver.plugin.interceptor;

import io.axoniq.axonserver.plugin.ExecutionContext;
import io.axoniq.axonserver.plugin.Ordered;
import io.axoniq.axonserver.plugin.RequestRejectedException;
import io.axoniq.axonserver.grpc.query.SubscriptionQueryRequest;

/**
 * Interceptor that intercepts any subscription query request.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface SubscriptionQueryRequestInterceptor extends Ordered {

    /**
     * Intercepts a {@link SubscriptionQueryRequest}. The interceptor may change the content of the request.
     * If the interceptor throws an exception the request is cancelled with an error.
     *
     * @param subscriptionQueryRequest the request
     * @param executionContext         the execution context for the request
     * @return the (updated) request
     *
     * @throws RequestRejectedException if the interceptor rejects the request
     */
    SubscriptionQueryRequest subscriptionQueryRequest(SubscriptionQueryRequest subscriptionQueryRequest,
                                                      ExecutionContext executionContext)
            throws RequestRejectedException;
}
