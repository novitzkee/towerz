package com.github.towerz.engine.events;

import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

public interface EventListener<T> {
    void onEvent(T event);

    Class<T> getEventClass();

    static <E> EventListener<E> of(Consumer<E> eventConsumer, Class<E> eventClass) {
        return new ConsumerEventListener<>(eventConsumer, eventClass);
    }

    @RequiredArgsConstructor
    class ConsumerEventListener<T> implements EventListener<T> {

        private final Consumer<T> eventConsumer;

        private final Class<T> eventClazz;

        @Override
        public void onEvent(T event) {
            eventConsumer.accept(event);
        }

        @Override
        public Class<T> getEventClass() {
            return eventClazz;
        }
    }
}
