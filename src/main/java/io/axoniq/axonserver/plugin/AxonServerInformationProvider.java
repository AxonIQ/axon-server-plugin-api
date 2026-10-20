/*
 * Copyright (c) 2020-2026. Axoniq B.V.
 *
 * Licensed under the Axoniq Terms of Service (the "Terms"). You may not
 * use this file except in compliance with the Terms, available at
 * https://www.axoniq.io/legal/terms-of-service
 */

package io.axoniq.axonserver.plugin;

import java.util.Map;

/**
 * @author Marc Gathier
 * @since 4.6.0
 */
public interface AxonServerInformationProvider {

    String PUBKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3Bh8GCm/kGOjOIvDyLKq\n"
            + "CAfYdd1mJ1P3Hqi+xspLEw5QKSdt8OElTJxSicEvxhQcUWQlAxnPLk7KBNseMjUv\n"
            + "UQ4A/0ktkOa6BRmRabhwCxw9zHOOo4PAeq4OW9eNDO12Wgoq7FekFxiExlUbDuHS\n"
            + "2dSN7xldC1pehBI8onxTKS7s6GTSxpuf9TlrBhDOPVAERGoTd167mC9yXasKJuFm\n"
            + "J5O8O9ZMTgUMry2RI7kiXF7c/DJ3+xvWhytMQrG72hiuICVwYTctP9mTwEKzI4VB\n"
            + "K1kUns4vBMZAZ4MSmS5W+gkF1qPxPDHs2oVSIkrY8ZwOvx/nrdzPyt8bBT2lLMEv\n"
            + "7wIDAQAB\n";

    String EDITION = "edition";
    String VERSION = "version";
    String SIGNATURE = "signature";
    String SIGNATURE_TIME = "signature_timestamp";

    Map<String, String> properties();
}
