package com.github.towerz.engine.time.delegators;

import com.github.towerz.engine.time.TimeAware;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CatchDelegator implements TimeAware {

    private final TimeAware subject;

    @Override
    public void tick() {
        try {
            subject.tick();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
