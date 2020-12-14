package fr.gboissinot.kernel.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Gregory Boissinot
 */
public class AsyncEventBus<E extends Event> implements EventBus<E>, Runnable {

    private static final Logger LOGGER = LogManager.getLogger(AsyncEventBus.class);

    private final List<EventInterceptor<E>> processors;
    private final SimpleEventDispatcher<E> dispatcher;
    private final BlockingQueue<E> queue;

    public AsyncEventBus() {
        this.processors = new ArrayList<>();
        this.dispatcher = new SimpleEventDispatcher<>();
        this.queue = new ArrayBlockingQueue<>(1024);
        startAsyncEventRunnner();
    }

    private void startAsyncEventRunnner() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(this);
    }

    @Override
    public void post(E event) {
        E e = Objects.requireNonNull(event);
        LOGGER.debug("Posting {}", e.name());
        try {
            queue.put(e);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void registerInterceptor(EventInterceptor<E> eventInterceptor) {
        EventInterceptor<E> interceptor = Objects.requireNonNull(eventInterceptor);
        LOGGER.debug("Registering interceptor {}", interceptor.getClass().getSimpleName());
        this.processors.add(interceptor);
    }

    @Override
    public void registerHandler(EventHandler<E> eventHandler) {
        EventHandler<E> handler = Objects.requireNonNull(eventHandler);
        LOGGER.debug("Registering handler {}", handler.getClass().getSimpleName());
        this.dispatcher.registerHandler(handler);
    }

    @SuppressWarnings("all")
    @Override
    public void run() {
        while (this.getRunning()) {
            try {
                E e = queue.take();
                for (EventInterceptor<E> interceptor : this.processors) {
                    interceptor.intercept(e);
                }
                this.dispatcher.dispatch(e);
            } catch (InterruptedException e) {
                LOGGER.error("Can't dispatch correctly the Audit event", e);
            }
        }
    }

    private boolean getRunning() {
        return true;
    }
}
