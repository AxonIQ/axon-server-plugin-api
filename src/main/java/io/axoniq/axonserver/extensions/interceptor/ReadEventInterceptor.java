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
import io.axoniq.axonserver.grpc.event.Event;

/**
 * Interceptor for events read from the event store. Intercepts events for events read for an aggregate and
 * events read for an event stream.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface ReadEventInterceptor extends Ordered {

    /**
     * Intercepts an event read from the event store. The interceptor may change the event.
     *
     * @param event            the read event
     * @param extensionContext the request context
     * @return the read event
     */
    Event readEvent(Event event, ExtensionUnitOfWork extensionContext);
}
