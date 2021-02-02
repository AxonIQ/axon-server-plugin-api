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
import io.axoniq.axonserver.grpc.command.CommandResponse;

/**
 * Interceptor that intercepts a command response. The interceptor may change the response.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface CommandResponseInterceptor extends Ordered {

    /**
     * Interceptor that is called before the response is returned to the client. The interceptor may return an
     * updated response.
     * If the interceptor throws an exception Axon Server returns a command response with an error to the client,
     * instead of the original command response.
     *
     * @param commandResponse     the command response
     * @param extensionUnitOfWork the unit of work for the request
     * @return the (updated) command response
     */
    CommandResponse commandResponse(CommandResponse commandResponse, ExtensionUnitOfWork extensionUnitOfWork);
}
