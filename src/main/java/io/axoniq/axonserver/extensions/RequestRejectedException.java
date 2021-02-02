package io.axoniq.axonserver.extensions;

/**
 * Exception that an interceptor can throw if it rejects the request.
 *
 * @author Marc Gathier
 * @since 4.5
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
