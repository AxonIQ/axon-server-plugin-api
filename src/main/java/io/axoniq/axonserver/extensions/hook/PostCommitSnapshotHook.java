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

/**
 * Hook that is called after a snapshot is committed in Axon Server.
 *
 * @author Marc Gathier
 * @since 4.5
 */
public interface PostCommitSnapshotHook extends Ordered {

    /**
     * Intercepts a snapshot after it is committed. The interceptor can not
     * change the contents of the snapshot.
     * If the interceptor throws an exception the exception is logged, but the transaction still completes
     * successfully.
     *
     * @param snapshot            the snapshot that has been committed
     * @param extensionUnitOfWork the unit of work for the transaction
     */
    void onPostCommitSnapshot(Event snapshot, ExtensionUnitOfWork extensionUnitOfWork);
}
