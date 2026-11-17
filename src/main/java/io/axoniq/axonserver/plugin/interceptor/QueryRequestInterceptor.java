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
import io.axoniq.axonserver.grpc.query.QueryRequest;

/**
 * Interceptor that intercepts a query. The interceptor may change the query.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface QueryRequestInterceptor extends Ordered {

    /**
     * Intercepts a query before it is sent to a query handler. The interceptor can return an updated query to execute.
     * If the interceptor throws an exception, Axon Server returns a query response with an error to the client, and
     * it will not send the query to any handler.
     *
     * @param query            the query to execute
     * @param executionContext the execution context for the request
     * @return the (updated) query
     *
     * @throws RequestRejectedException if the interceptor rejects the query
     */
    QueryRequest queryRequest(QueryRequest query, ExecutionContext executionContext) throws
                                                                                     RequestRejectedException;
}
