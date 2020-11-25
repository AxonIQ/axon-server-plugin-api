/*
 * Copyright (c) 2017-2019 AxonIQ B.V. and/or licensed to AxonIQ B.V.
 * under one or more contributor license agreements.
 *
 *  Licensed under the AxonIQ Open Source License Agreement v1.0;
 *  you may not use this file except in compliance with the license.
 *
 */

package io.axoniq.axonserver.extensions.transform;

/**
 * Defines the interface for an EventTransformer factory.
 *
 * @author Marc Gathier
 */
public interface EventTransformerFactory {

    /**
     * Get an event transformer. The transformer may be based on flags (for existing data) or based on
     * settings in the storage properties (for new data). //TODO rephrase
     *
     * @param flags the flags for existing data
     * @return the transformer
     */
    EventTransformer get(int flags);
}
