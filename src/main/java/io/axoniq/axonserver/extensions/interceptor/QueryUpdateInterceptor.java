/*
 * Copyright (c) 2017-2020 AxonIQ B.V. and/or licensed to AxonIQ B.V.
 * under one or more contributor license agreements.
 *
 *  Licensed under the AxonIQ Open Source License Agreement v1.0;
 *  you may not use this file except in compliance with the license.
 *
 */

package io.axoniq.axonserver.extensions.interceptor;

import io.axoniq.axonserver.extensions.ExtensionContext;
import io.axoniq.axonserver.extensions.Ordered;
import io.axoniq.axonserver.grpc.query.QueryUpdate;
import io.axoniq.axonserver.grpc.query.QueryUpdateComplete;
import io.axoniq.axonserver.grpc.query.QueryUpdateCompleteExceptionally;

/**
 * @author Marc Gathier
 */
public interface QueryUpdateInterceptor extends Ordered {

    QueryUpdate queryUpdate(ExtensionContext extensionContext, QueryUpdate update);

    default QueryUpdateComplete queryUpdateComplete(ExtensionContext extensionContext,
                                                    QueryUpdateComplete updateComplete) {
        return updateComplete;
    }

    default QueryUpdateCompleteExceptionally queryUpdateCompleteExceptionally(ExtensionContext extensionContext,
                                                                              QueryUpdateCompleteExceptionally updateCompleteExceptionally) {
        return updateCompleteExceptionally;
    }
}
