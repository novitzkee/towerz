package engine.events;

public interface EventListener<T> {
    void onEvent(T event);
}
