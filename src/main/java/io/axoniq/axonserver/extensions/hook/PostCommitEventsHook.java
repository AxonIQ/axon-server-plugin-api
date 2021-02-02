/*
 * Copyright (c) 2017-2020 AxonIQ B.V. and/or licensed to AxonIQ B.V.
 * under one or more contributor license agreements.
 *
 *  Licensed under the AxonIQ Open Source License Agreement v1.0;
 *  you may not use this file except in compliance with the license.
 *
 */

package io.axoniq.axonserver.extensions.hook;

import io.axoniq.axonserver.extensions.ExtensionUnitOfWork;
import io.axoniq.axonserver.extensions.Ordered;
import io.axoniq.axonserver.grpc.event.Event;

import java.util.List;

/**
 * Hook that is called after a transaction with events is committed in Axon Server.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface PostCommitEventsHook extends Ordered {

    /**
     * Intercepts a transaction after it is committed. The interceptor can not
     * change the contents of the transaction.
     * If the interceptor throws an exception the exception is logged, but the transaction still completes
     * successfully.
     *
     * @param events              the (unmodifiable) list of events in the transaction
     * @param extensionUnitOfWork the unit of work for the transaction
     */
    void onPostCommitEvent(List<Event> events, ExtensionUnitOfWork extensionUnitOfWork);
}
