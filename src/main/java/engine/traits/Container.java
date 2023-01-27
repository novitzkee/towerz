package engine.traits;

import java.util.Collection;
import java.util.stream.Stream;

public interface Container<T> {

    void add(T element);

    default void addAll(Collection<T> elements) {
        elements.forEach(this::add);
    }

    void remove(T element);

    default void removeAll(Collection<T> elements) {
        elements.forEach(this::remove);
    }

    Stream<T> getElements();
}
