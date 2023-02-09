package com.github.towerz.engine.time;

public interface Loop {
    void start();

    void stop();

    void add(TimeAware subject);

    void remove(TimeAware subject);
}
