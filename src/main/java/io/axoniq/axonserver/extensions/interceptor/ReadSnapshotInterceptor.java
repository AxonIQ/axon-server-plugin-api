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
 * Interceptor for snapshots read from the event store. Intercepts snapshots read for an aggregate and
 * queried through the ad-hoc query function.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface ReadSnapshotInterceptor extends Ordered {

    Event readSnapshot(Event snapshot, ExtensionUnitOfWork extensionContext);
}
