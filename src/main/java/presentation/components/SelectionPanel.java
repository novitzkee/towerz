package presentation.components;

import engine.events.EventEmitter;
import engine.events.EventListener;
import engine.events.Subscriber;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static presentation.config.Dimensions.SELECTION_WIDTH;

public class SelectionPanel extends JPanel implements Subscriber {

    private final EventEmitter eventEmitter;

    @Getter
    private final List<EventListener<?>> eventListeners = new ArrayList<>();

    public SelectionPanel(EventEmitter eventEmitter) {
        this.eventEmitter = eventEmitter;
        compose();
    }

    public void compose() {
        setPreferredSize(new Dimension(SELECTION_WIDTH, 200));
        setBackground(Color.PINK);
    }
}
