package fr.gboissinot.kernel.cqs.engine;

/**
 * @author Gregory Boissinot
 */
public class ActionMessageBusException extends RuntimeException {

    public ActionMessageBusException(Exception cause) {
        super(cause);
    }

    public ActionMessageBusException(String message) {
        super(message);
    }

    @Override
    public synchronized Exception getCause() {
        return (Exception) super.getCause();
    }
}
