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

    CommandResponse commandResponse(CommandResponse command, ExtensionUnitOfWork extensionContext);
}
