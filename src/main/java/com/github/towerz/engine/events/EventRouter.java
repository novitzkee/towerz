package com.github.towerz.engine.events;

import java.util.Collection;

public interface EventRouter {

    void add(EventListener<?> listener);

    void addAll(Collection<EventListener<?>> listeners);

    void remove(EventListener<?> listener);

    void removeAll(Collection<EventListener<?>> listeners);
}
