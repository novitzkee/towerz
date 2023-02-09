package com.github.towerz.engine.time.delegators;

import com.github.towerz.engine.time.TimeAware;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScalingDelegator implements TimeAware {

    private final TimeAware subject;

    private float remainingTicks = 1f;

    private float scale = 1f;

    public ScalingDelegator(float scale, TimeAware subject) {
        this.scale = scale;
        this.subject = subject;
    }

    public synchronized void setScale(float newScale) {
        scale = newScale;
    }

    @Override
    public synchronized void tick() {
        remainingTicks += scale;

        if(remainingTicks >= 1)
            executeRemainingTicks();
    }

    private void executeRemainingTicks() {
        for (int i = 1; i <= remainingTicks; i++) {
            subject.tick();
        }

        remainingTicks %= 1f;
    }
}
