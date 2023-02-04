package engine.action;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ConsecutiveAction implements Action {

    private final int delayBetween;

    private final List<Runnable> actions;

    private int counter = 0;

    private int currentAction = 0;

    @Override
    public void tick() {
        counter++;

        if (!isGarbage() && counter >= delayBetween) {
            actions.get(currentAction).run();
            counter %= delayBetween;
            currentAction++;
        }
    }

    @Override
    public boolean isGarbage() {
        return currentAction > actions.size() - 1;
    }
}
