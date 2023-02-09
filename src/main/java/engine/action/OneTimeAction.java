package engine.action;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OneTimeAction implements Action {

    private final Runnable action;

    private boolean hasExecuted;

    @Override
    public void tick() {
        action.run();
        hasExecuted = true;
    }

    @Override
    public boolean isFinished() {
        return hasExecuted;
    }
}
