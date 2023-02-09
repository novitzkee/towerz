package com.github.towerz.engine.events;

import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor
public class EventMapper<T> implements EventListener<T> {

    private final Class<T> clazz;

    private final Function<T, Object> eventMapping;

    private final EventEmitter eventEmitter;

    @Override
    public void onEvent(T event) {
        eventEmitter.emit(eventMapping.apply(event));
    }

    @Override
    public Class<T> getEventClass() {
        return clazz;
    }
}
