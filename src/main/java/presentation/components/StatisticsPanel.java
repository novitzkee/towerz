package presentation.components;

import engine.events.EventListener;
import engine.events.EventRouter;
import engine.events.Subscriber;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StatisticsPanel extends JPanel implements Subscriber {

    private final List<EventListener<?>> eventListeners = new ArrayList<>();

    public StatisticsPanel() {

    }

    @Override
    public void subscribe(EventRouter eventRouter) {
        eventRouter.addAll(eventListeners);
    }

    @Override
    public void unsubscribe(EventRouter eventRouter) {
        eventRouter.removeAll(eventListeners);
    }
}
