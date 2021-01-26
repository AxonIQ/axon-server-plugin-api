package io.axoniq.axonserver.extensions;

/**
 * @author Marc Gathier
 */
public class RequestRejectedException extends Exception {

    public RequestRejectedException() {
        super();
    }

    public RequestRejectedException(String message) {
        super(message);
    }

    public RequestRejectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestRejectedException(Throwable cause) {
        super(cause);
    }
}
