package com.github.towerz.engine.action;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RepeatedAction implements Action {

    private final int delayBetween;

    private final int timesToRepeat;

    private final Runnable action;

    private int counter = 0;

    private int timesExecuted = 0;

    @Override
    public void tick() {
        counter++;

        if (!isFinished() && counter >= delayBetween) {
            action.run();
            counter %= delayBetween;
            timesExecuted++;
        }
    }

    @Override
    public boolean isFinished() {
        return timesExecuted >= timesToRepeat;
    }
}
