package com.github.towerz.engine;

import com.github.towerz.engine.time.TimeAware;
import lombok.Getter;

public class TickCounter implements TimeAware {

    @Getter
    private int ticks = 0;

    @Override
    public void tick() {
        ticks++;
    }

    public void reset() {
        ticks = 0;
    }
}
