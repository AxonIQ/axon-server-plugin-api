package io.axoniq.axonserver.extensions;

/**
 * @author Sara Pellegrini
 * @since 4.5
 */
public interface RevertibleExtensionContext extends ExtensionContext {

    /**
     * Registers an action to compensate state changes made by the interceptor when the request fails to execute
     * successfully.
     *
     * @param revertingAction the action to execute when the request failed
     */
    void registerRevertingAction(Runnable revertingAction);
}
