package engine.action;

import engine.time.TimeAware;

public interface Action extends TimeAware {
    boolean isFinished();
}
