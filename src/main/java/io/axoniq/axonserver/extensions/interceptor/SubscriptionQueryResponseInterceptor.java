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
import io.axoniq.axonserver.grpc.query.SubscriptionQueryResponse;

/**
 * Interceptor that intercepts any subscription query response.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface SubscriptionQueryResponseInterceptor extends Ordered {

    SubscriptionQueryResponse subscriptionQueryResponse(SubscriptionQueryResponse subscriptionQueryResponse,
                                                        ExtensionUnitOfWork extensionContext);
}
