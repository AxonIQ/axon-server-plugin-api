/*
 * Copyright (c) 2017-2020 AxonIQ B.V. and/or licensed to AxonIQ B.V.
 * under one or more contributor license agreements.
 *
 *  Licensed under the AxonIQ Open Source License Agreement v1.0;
 *  you may not use this file except in compliance with the license.
 *
 */

package io.axoniq.axonserver.plugin.interceptor;

import io.axoniq.axonserver.plugin.PluginUnitOfWork;
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
     * @param event               the read event
     * @param extensionUnitOfWork the unit of work for the request
     * @return the (updated) event
     */
    Event readEvent(Event event, PluginUnitOfWork extensionUnitOfWork);
}
