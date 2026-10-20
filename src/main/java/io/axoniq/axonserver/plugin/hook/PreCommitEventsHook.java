/*
 * Copyright (c) 2020-2026. Axoniq B.V.
 *
 * Licensed under the Axoniq Terms of Service (the "Terms"). You may not
 * use this file except in compliance with the Terms, available at
 * https://www.axoniq.io/legal/terms-of-service
 */

package io.axoniq.axonserver.plugin.hook;

import io.axoniq.axonserver.plugin.ExecutionContext;
import io.axoniq.axonserver.plugin.Ordered;
import io.axoniq.axonserver.plugin.RequestRejectedException;
import io.axoniq.axonserver.grpc.event.Event;

import java.util.List;

/**
 * Interceptor that is called when the client commits a transaction with new events.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface PreCommitEventsHook extends Ordered {

    /**
     * Intercepts a transaction before it is committed. The interceptor can no longer
     * change the contents of the transaction.
     * If the interceptor throws an exception the exception the transaction is cancelled.
     *
     * @param events           the list of events in the transaction
     * @param executionContext the context for the request
     * @throws RequestRejectedException to indicate that the request was rejected by the interceptor
     */
    void onPreCommitEvents(List<Event> events, ExecutionContext executionContext) throws RequestRejectedException;
}
