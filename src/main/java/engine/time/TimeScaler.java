package engine.time;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TimeScaler implements TimeAware {

    private float remainingTicks = 0f;

    private float scale;
    private final TimeAware subject;

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
