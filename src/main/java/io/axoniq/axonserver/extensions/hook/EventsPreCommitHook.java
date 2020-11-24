/*
 * Copyright (c) 2017-2020 AxonIQ B.V. and/or licensed to AxonIQ B.V.
 * under one or more contributor license agreements.
 *
 *  Licensed under the AxonIQ Open Source License Agreement v1.0;
 *  you may not use this file except in compliance with the license.
 *
 */

package io.axoniq.axonserver.extensions.hook;

import io.axoniq.axonserver.extensions.Context;
import io.axoniq.axonserver.extensions.Ordered;
import io.axoniq.axonserver.grpc.event.Event;

import java.util.List;

/**
 * Interceptor that is called when the client commits a transaction with new events.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface EventsPreCommitHook extends Ordered {

    /**
     * Intercepts a transaction before it is committed. The interceptor can no longer
     * change the contents of the transaction.
     *
     * @param context the context for the request
     * @param events  the list of events in the transaction
     */
    void onPreCommit(Context context, List<Event> events);
}
