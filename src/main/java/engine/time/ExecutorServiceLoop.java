package engine.time;


import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

@RequiredArgsConstructor
public class ExecutorServiceLoop implements Loop {

    private boolean isRunning = false;

    private final Delay delay;

    private final Map<TimeAware, ScheduledFuture<?>> tasksForSubjects = new HashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @Override
    public synchronized void start() {
        if(isRunning) return;

        tasksForSubjects.keySet().forEach(sub -> tasksForSubjects.put(sub, scheduleTask(sub)));
        isRunning = true;
    }

    @Override
    public synchronized void stop() {
        if(!isRunning) return;

        tasksForSubjects.values().forEach(task -> task.cancel(false));
        tasksForSubjects.keySet().forEach(sub -> tasksForSubjects.put(sub, null));
        isRunning = false;
    }

    @Override
    public synchronized void add(TimeAware subject) {
        tasksForSubjects.putIfAbsent(subject, isRunning ? scheduleTask(subject) : null);
    }

    private ScheduledFuture<?> scheduleTask(TimeAware subject) {
        return scheduler.scheduleAtFixedRate(subject::tick, delay.amount(), delay.amount(), delay.timeUnit());
    }

    @Override
    public synchronized void remove(TimeAware subject) {
        Optional.of(tasksForSubjects.get(subject)).ifPresent(t -> t.cancel(false));
        tasksForSubjects.remove(subject);
    }
}
