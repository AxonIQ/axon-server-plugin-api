/*
 * Copyright (c) 2017-2020 AxonIQ B.V. and/or licensed to AxonIQ B.V.
 * under one or more contributor license agreements.
 *
 *  Licensed under the AxonIQ Open Source License Agreement v1.0;
 *  you may not use this file except in compliance with the license.
 *
 */

package io.axoniq.axonserver.extensions.interceptor;

import io.axoniq.axonserver.grpc.command.Command;

/**
 * Interceptor that intercepts a command. The interceptor may change the command.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface CommandRequestInterceptor extends OrderedInterceptor {

    /**
     * Interceptor for an incoming command.
     *
     * @param interceptorContext the context of the request
     * @param command            the command that was sent
     * @return the updated command
     */
    Command commandRequest(InterceptorContext interceptorContext, Command command);
}
