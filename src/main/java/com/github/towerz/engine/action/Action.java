package com.github.towerz.engine.action;

import com.github.towerz.engine.time.TimeAware;

public interface Action extends TimeAware {
    boolean isFinished();
}
