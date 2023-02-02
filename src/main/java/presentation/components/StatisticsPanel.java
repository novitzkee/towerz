package presentation.components;

import engine.events.EventListener;
import engine.events.Subscriber;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static presentation.config.Dimensions.SELECTION_WIDTH;

public class StatisticsPanel extends JPanel implements Subscriber {

    @Getter
    private final List<EventListener<?>> eventListeners = new ArrayList<>();

    public StatisticsPanel() {
        // TODO
    }

    public void compose() {
        setPreferredSize(new Dimension(SELECTION_WIDTH, 200));
        setBackground(Color.YELLOW);
    }
}
