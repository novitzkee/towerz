package com.github.towerz.engine.action;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class ConsecutiveAction implements Action {

    private final int delayBetween;

    private final List<Action> actions;

    private int counter = 0;

    private int currentActionIndex = 0;

    public ConsecutiveAction(int delayBetween, Action... actions) {
        this(delayBetween, Arrays.asList(actions));
    }

    @Override
    public void tick() {
        counter++;

        if (!isFinished() && counter >= delayBetween) {
            tickCurrentAction();
        }
    }

    private void tickCurrentAction() {
        final Action currentAction = actions.get(currentActionIndex);
        currentAction.tick();

        if (currentAction.isFinished()) {
            currentActionIndex++;
            counter = 0;
        }
    }

    @Override
    public boolean isFinished() {
        return currentActionIndex > actions.size() - 1;
    }
}
