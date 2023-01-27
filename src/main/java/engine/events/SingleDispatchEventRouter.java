package engine.events;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SingleDispatchEventRouter implements EventEmitter, EventRouter {

    private final Map<Class<?>, Set<EventListener<?>>> listenersByEventType = new HashMap<>();

    public void add(EventListener<?> element) {
        final Class<?> eventClass = element.getEventClass();
        listenersByEventType.merge(eventClass, Set.of(element), this::union);
    }

    @Override
    public void addAll(Collection<EventListener<?>> elements) {
        elements.forEach(this::add);
    }

    private <T> Set<T> union(Set<T> first, Set<T> second) {
        return Stream.of(first, second)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public void remove(EventListener<?> element) {
        final Class<?> eventClass = element.getEventClass();
        Optional.ofNullable(listenersByEventType.get(eventClass))
                .ifPresent(set -> set.remove(element));
    }

    @Override
    public void emit(Object event) {
        final Class<?> eventClass = event.getClass();
        Optional.ofNullable(listenersByEventType.get(eventClass))
                .ifPresent(listeners -> castAndDispatch(listeners, event));
    }

    @SuppressWarnings("unchecked") // Not elegant but type system won't let me do it otherwise.
    private void castAndDispatch(Set<EventListener<?>> listeners, Object event) {
        listeners.stream()
                .map(listener -> (EventListener<Object>)listener)
                .forEach(listener -> listener.onEvent(event));
    }

    @Override
    public Stream<EventListener<?>> getElements() {
        return listenersByEventType.values()
                .stream()
                .flatMap(Collection::stream);
    }
}
