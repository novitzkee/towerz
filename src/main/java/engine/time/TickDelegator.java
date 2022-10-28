package engine.time;

import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class TickDelegator implements TimeAware {

    private final Consumer<Runnable> delegationTarget;
    private final TimeAware subject;

    @Override
    public void tick() {
        delegationTarget.accept(subject::tick);
    }
}
