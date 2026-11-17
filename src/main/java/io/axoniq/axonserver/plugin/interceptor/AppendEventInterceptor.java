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
import io.axoniq.axonserver.grpc.event.Event;

/**
 * Interceptor that is called when an event is sent to Axon Server. The interceptor is called immediately when
 * the event arrives in Axon Server, before the transaction is committed.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface AppendEventInterceptor extends Ordered {

    /**
     * Intercepts an event when it is received by Axon Server. The interceptor may change the payload and the
     * metadata of the event.
     * If the interceptor throws an exception the exception the transaction is cancelled.
     *
     * @param event            the new event
     * @param executionContext the execution context for the transaction
     * @return the new event
     */
    Event appendEvent(Event event, ExecutionContext executionContext);
}
