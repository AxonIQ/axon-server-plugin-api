package io.axoniq.axonserver.plugin;

import java.util.Map;

/**
 * @author Marc Gathier
 * @since 4.5.1
 */
public interface AxonServerInformationProvider {

    String PRODUCT = "product";
    String VERSION = "version";
    String SIGNATURE = "signature";
    String SIGNATURE_TIME = "signature_timestamp";

    Map<String,String> properties();

}
