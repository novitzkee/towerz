package presentation.components;

import engine.events.EventEmitter;
import engine.events.EventListener;
import engine.events.Subscriber;
import lombok.Getter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SelectionPanel extends JPanel implements Subscriber {

    private final EventEmitter eventEmitter;

    @Getter
    private final List<EventListener<?>> eventListeners = new ArrayList<>();

    public SelectionPanel(EventEmitter eventEmitter) {
        this.eventEmitter = eventEmitter;
    }
}
