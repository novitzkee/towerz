package com.github.towerz.engine.action;

import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class PredicateAction implements Action {

    private final Runnable action;

    private final Supplier<Boolean> condition;

    private boolean hasExecuted = false;

    @Override
    public void tick() {
        if (!hasExecuted && condition.get()) {
            action.run();
            hasExecuted = true;
        }
    }

    @Override
    public boolean isFinished() {
        return hasExecuted;
    }
}
