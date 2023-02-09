package com.github.towerz.presentation.components;

import com.github.towerz.engine.events.EventEmitter;
import com.github.towerz.engine.events.EventListener;
import com.github.towerz.engine.events.Subscriber;
import com.github.towerz.presentation.components.resources.Colors;
import com.github.towerz.presentation.components.resources.FontProvider;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static com.github.towerz.presentation.config.Dimensions.SELECTION_WIDTH;


@RequiredArgsConstructor
public class SidePanel extends JPanel implements Subscriber {

    private final UpgradePanel upgradePanel;

    private final TowerPanel towerPanel;

    private final SoldierPanel soldierPanel;

    private final StatisticsPanel statisticsPanel;

    public SidePanel(EventEmitter eventEmitter) {
        this.upgradePanel = new UpgradePanel(eventEmitter);
        this.towerPanel = new TowerPanel(eventEmitter);
        this.soldierPanel = new SoldierPanel(eventEmitter);
        this.statisticsPanel = new StatisticsPanel();

        compose();
    }

    private void compose() {
        setPreferredSize(new Dimension(SELECTION_WIDTH, GUI.JFRAME_Y_SIZE));
        setVisible(true);
        setBackground(Colors.STONE_GRAY);

        final JLabel titleLabel = new JLabel("Towerz");
        titleLabel.setFont(FontProvider.get().deriveFont(42f));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        setLayout(new GridBagLayout());

        final GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.fill = GridBagConstraints.VERTICAL;
        layoutConstraints.weighty = 1;
        layoutConstraints.gridx = 0;

        add(titleLabel);
        add(upgradePanel, layoutConstraints);
        add(towerPanel, layoutConstraints);
        add(soldierPanel, layoutConstraints);
        add(statisticsPanel, layoutConstraints);
    }

    @Override
    public List<EventListener<?>> getEventListeners() {
        return Stream.of(upgradePanel, statisticsPanel)
                .map(Subscriber::getEventListeners)
                .flatMap(Collection::stream)
                .toList();
    }
}
