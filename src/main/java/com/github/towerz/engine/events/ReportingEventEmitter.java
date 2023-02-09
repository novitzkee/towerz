package com.github.towerz.engine.events;

public class ReportingEventEmitter implements EventEmitter {
    @Override
    public void emit(Object event) {
        System.out.println(event);
    }
}
