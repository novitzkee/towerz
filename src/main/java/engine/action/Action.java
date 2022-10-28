package engine.action;

import engine.time.TimeAware;

public interface Action extends Runnable, TimeAware {
    boolean isCancelled();
}
