/*
 * Copyright (c) 2017-2020 AxonIQ B.V. and/or licensed to AxonIQ B.V.
 * under one or more contributor license agreements.
 *
 *  Licensed under the AxonIQ Open Source License Agreement v1.0;
 *  you may not use this file except in compliance with the license.
 *
 */

package io.axoniq.axonserver.extensions.interceptor;

import io.axoniq.axonserver.extensions.ExtensionUnitOfWork;
import io.axoniq.axonserver.extensions.Ordered;
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
     * @param response            the query response
     * @param extensionUnitOfWork the unit of work for the request
     * @return the (updated) response
     */
    QueryResponse queryResponse(QueryResponse response, ExtensionUnitOfWork extensionUnitOfWork);
}
