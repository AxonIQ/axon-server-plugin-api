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
import io.axoniq.axonserver.extensions.RequestRejectedException;
import io.axoniq.axonserver.grpc.event.Event;

/**
 * Interceptor that is called when a snapshot is sent to Axon Server.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface AppendSnapshotInterceptor extends Ordered {

    Event appendSnapshot(Event snapshot, ExtensionUnitOfWork extensionContext) throws RequestRejectedException;
}
