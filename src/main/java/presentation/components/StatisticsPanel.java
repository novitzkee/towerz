package presentation.components;

import engine.events.EventListener;
import engine.events.Subscriber;
import lombok.Getter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StatisticsPanel extends JPanel implements Subscriber {

    @Getter
    private final List<EventListener<?>> eventListeners = new ArrayList<>();

    public StatisticsPanel() {

    }
}
