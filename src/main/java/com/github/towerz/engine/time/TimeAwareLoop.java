package com.github.towerz.engine.time;

import java.util.HashSet;
import java.util.Set;

public class TimeAwareLoop implements TimeAware, Loop {

    private boolean isRunning = false;
    private final Set<TimeAware> subjects = new HashSet<>();

    @Override
    public synchronized void tick() {
        if (isRunning)
            subjects.forEach(TimeAware::tick);
    }

    @Override
    public synchronized void start() {
        isRunning = true;
    }

    @Override
    public synchronized void stop() {
        isRunning = false;
    }

    @Override
    public synchronized void add(TimeAware subject) {
        subjects.add(subject);
    }

    @Override
    public synchronized void remove(TimeAware subject) {
        subjects.remove(subject);
    }
}
