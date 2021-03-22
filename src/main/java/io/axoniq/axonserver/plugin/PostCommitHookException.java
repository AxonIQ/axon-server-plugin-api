package io.axoniq.axonserver.plugin;

/**
 * Exception thrown by Axon Server when there was an exception in executing
 * {@link io.axoniq.axonserver.plugin.hook.PostCommitSnapshotHook} or
 * {@link io.axoniq.axonserver.plugin.hook.PostCommitEventsHook} hooks.
 * Plugin developers can use this in failure handlers to determine if the exception occurred after the
 * events/snapshots were committed.
 * @author Marc Gathier
 * @since 4.5
 */
public class PostCommitHookException extends RuntimeException {

    public PostCommitHookException(String message) {
        super(message);
    }

    public PostCommitHookException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostCommitHookException(Throwable cause) {
        super(cause);
    }

}
