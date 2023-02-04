package presentation.components;

import engine.events.EventEmitter;
import engine.events.EventListener;
import engine.events.Subscriber;
import lombok.Getter;
import presentation.components.resources.Colors;
import presentation.components.resources.FontProvider;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static presentation.config.Dimensions.SELECTION_WIDTH;

public class UpgradePanel extends JPanel implements Subscriber {

    private final EventEmitter eventEmitter;

    @Getter
    private final List<EventListener<?>> eventListeners = new ArrayList<>();

    public UpgradePanel(EventEmitter eventEmitter) {
        this.eventEmitter = eventEmitter;
    }

    public void compose() {
        setPreferredSize(new Dimension(SELECTION_WIDTH, 0));
        setBackground(Colors.TRANSPARENT);

        final JLabel upgradePanelLabel = new JLabel("Upgrade");
        upgradePanelLabel.setFont(FontProvider.get().deriveFont(18f));
        upgradePanelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(upgradePanelLabel);
    }
}
