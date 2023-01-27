package presentation.components;

import engine.events.EventEmitter;
import engine.events.EventListener;
import engine.events.EventRouter;
import engine.events.Subscriber;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TowerPanel extends JPanel implements Subscriber {

    private final EventEmitter eventEmitter;

    private final List<EventListener<?>> eventListeners = new ArrayList<>();

    public TowerPanel(EventEmitter eventEmitter) {
        this.eventEmitter = eventEmitter;
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
