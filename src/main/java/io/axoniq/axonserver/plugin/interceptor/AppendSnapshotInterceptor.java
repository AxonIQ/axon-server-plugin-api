/*
 * Copyright (c) 2017-2020 AxonIQ B.V. and/or licensed to AxonIQ B.V.
 * under one or more contributor license agreements.
 *
 *  Licensed under the AxonIQ Open Source License Agreement v1.0;
 *  you may not use this file except in compliance with the license.
 *
 */

package io.axoniq.axonserver.plugin.interceptor;

import io.axoniq.axonserver.plugin.ExecutionContext;
import io.axoniq.axonserver.plugin.Ordered;
import io.axoniq.axonserver.plugin.RequestRejectedException;
import io.axoniq.axonserver.grpc.event.Event;

/**
 * Interceptor that is called when a snapshot is sent to Axon Server.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface AppendSnapshotInterceptor extends Ordered {

    /**
     * Intercepts a snapshot when it is received by Axon Server. The interceptor may change the payload and the
     * metadata of the snapshot.
     * If the interceptor throws an exception the exception the transaction is cancelled.
     *
     * @param snapshot         the snapshot to insert
     * @param executionContext the execution context for the transaction
     * @return the (updated) snapshot
     *
     * @throws RequestRejectedException to indicate that the request was rejected by the interceptor
     */
    Event appendSnapshot(Event snapshot, ExecutionContext executionContext) throws RequestRejectedException;
}
