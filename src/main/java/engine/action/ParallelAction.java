package engine.action;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class ParallelAction implements Action {

    private final List<Action> actions;

    public ParallelAction(Action... actions) {
        this(Arrays.asList(actions));
    }


    @Override
    public void tick() {
        actions.forEach(Action::tick);
    }

    @Override
    public boolean isFinished() {
        return actions.stream().allMatch(Action::isFinished);
    }
}
