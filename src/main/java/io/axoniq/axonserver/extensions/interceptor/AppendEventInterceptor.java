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
     * @param event               the new event
     * @param extensionUnitOfWork the unit of work for the transaction
     * @return the new event
     */
    Event appendEvent(Event event, ExtensionUnitOfWork extensionUnitOfWork);
}
