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
import io.axoniq.axonserver.grpc.command.Command;

/**
 * Interceptor that intercepts a command. The interceptor may change the command.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface CommandRequestInterceptor extends Ordered {

    /**
     * Interceptor for an incoming command. The interceptor may change the content of the request.
     * If the interceptor throws an exception the client receives a command response with an error, and the
     * command is not sent to any command handler.
     *
     * @param command          the command that was sent
     * @param executionContext the execution context for the command
     * @return the updated command
     *
     * @throws RequestRejectedException if the interceptor rejects the command
     */
    Command commandRequest(Command command, ExecutionContext executionContext) throws RequestRejectedException;
}
