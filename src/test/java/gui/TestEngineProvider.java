package gui;

import engine.events.EventEmitter;
import engine.events.EventRouter;
import engine.events.ReportingEventEmitter;
import engine.events.SingleDispatchEventRouter;
import engine.time.Loop;
import engine.time.TimeAware;

import java.util.ArrayList;
import java.util.List;

public class TestEngineProvider {

    public static final EventEmitter eventEmitter = new ReportingEventEmitter();

    public static final EventRouter eventRouter = new SingleDispatchEventRouter();

    public static final Loop repaintLoop = new SingleTimeRepaintLoop();

    private static class SingleTimeRepaintLoop implements Loop {

        private final List<TimeAware> subjects = new ArrayList<>();

        @Override
        public void start() {
            subjects.forEach(TimeAware::tick);
        }

        @Override
        public void stop() { }

        @Override
        public void add(TimeAware subject) {
            subjects.add(subject);
        }

        @Override
        public void remove(TimeAware subject) {
            subjects.remove(subject);
        }
    }
}
