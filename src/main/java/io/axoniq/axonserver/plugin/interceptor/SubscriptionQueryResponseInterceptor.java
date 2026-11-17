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
import io.axoniq.axonserver.grpc.query.SubscriptionQueryResponse;

/**
 * Interceptor that intercepts any subscription query response.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface SubscriptionQueryResponseInterceptor extends Ordered {

    /**
     * Intercepts a response message for a subscription query. The interceptor may change the content of the
     * response message.
     * If the interceptor throws an exception, Axon Server cancels the subscription query request.
     *
     * @param subscriptionQueryResponse the response
     * @param executionContext          the execution context for the request
     * @return the (updated) response
     */
    SubscriptionQueryResponse subscriptionQueryResponse(SubscriptionQueryResponse subscriptionQueryResponse,
                                                        ExecutionContext executionContext);
}
