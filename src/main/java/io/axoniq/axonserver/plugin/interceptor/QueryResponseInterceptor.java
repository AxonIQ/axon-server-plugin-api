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
import io.axoniq.axonserver.grpc.query.QueryResponse;

/**
 * Interceptor that intercepts a query response. The interceptor may change the response.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface QueryResponseInterceptor extends Ordered {

    /**
     * Intercepts the response of a query before sending it to the client. The interceptor may return
     * an updated version of the response.
     * If the interceptor throws an exception, Axon Server will return a query response with an error to
     * the client, instead of the actual response.
     *
     * @param response         the query response
     * @param executionContext the execution context for the request
     * @return the (updated) response
     */
    QueryResponse queryResponse(QueryResponse response, ExecutionContext executionContext);
}
