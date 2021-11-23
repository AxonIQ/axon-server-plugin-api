package io.axoniq.axonserver.plugin;

import java.util.Map;

/**
 * @author Marc Gathier
 * @since 4.5.1
 */
public interface AxonServerInformationProvider {

    String PRODUCT = "product";
    String VERSION = "version";

    Map<String,String> properties();

}
