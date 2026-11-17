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
 * Interceptor for events read from the event store. Intercepts events for events read for an aggregate,
 * events queried through the ad-hoc query function and events read for an event stream.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface ReadEventInterceptor extends Ordered {

    /**
     * Intercepts an event read from the event store. The interceptor may change the payload and the metadata of
     * the event.
     * If the interceptor throws an exception, the stream reading the events is cancelled with an error.
     *
     * @param event            the read event
     * @param executionContext the execution context for the request
     * @return the (updated) event
     */
    Event readEvent(Event event, ExecutionContext executionContext);
}
