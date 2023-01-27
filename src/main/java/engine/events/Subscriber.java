package engine.events;

public interface Subscriber {
    void subscribe(EventRouter eventRouter);
    void unsubscribe(EventRouter eventRouter);
}
