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
 * Interceptor for snapshots read from the event store. Intercepts snapshots read for an aggregate and
 * queried through the ad-hoc query function.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface ReadSnapshotInterceptor extends Ordered {

    /**
     * Intercepts an snapshot read from the event store. The interceptor may change the payload and the metadata of
     * the snapshot.
     * If the interceptor throws an exception, the request is cancelled with an error.
     *
     * @param snapshot         the read snapshot
     * @param executionContext the execution context for the request
     * @return the (updated) snapshot
     */
    Event readSnapshot(Event snapshot, ExecutionContext executionContext);
}
