package com.github.towerz.presentation.components;

import com.github.towerz.engine.events.EventEmitter;
import com.github.towerz.engine.events.EventListener;
import com.github.towerz.game.events.interaction.PricedSelection;
import com.github.towerz.game.events.interaction.tower.TowerBuildSelectionChangedEvent;
import com.github.towerz.game.events.interaction.tower.TowerType;
import com.github.towerz.presentation.components.resources.Colors;
import com.github.towerz.presentation.components.resources.FontProvider;
import com.github.towerz.presentation.components.selection.TowerBuySelections;
import com.github.towerz.presentation.config.Dimensions;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TowerPanel extends JPanel {

    private final EventEmitter eventEmitter;

    @Getter
    private final List<EventListener<?>> eventListeners = new ArrayList<>();

    private final TowerBuySelections towerBuySelections = new TowerBuySelections();

    public TowerPanel(EventEmitter eventEmitter) {
        this.eventEmitter = eventEmitter;
        compose();
        towerBuySelections.addSelectionChangeConsumer(this::emitSelectionEvent);
    }

    private void compose() {
        setPreferredSize(new Dimension(Dimensions.SELECTION_WIDTH, 0));
        setBackground(Colors.STONE_GRAY);

        final JLabel towerPanelLabel = new JLabel("Towers");
        towerPanelLabel.setFont(FontProvider.get().deriveFont(18f));
        towerPanelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        setLayout(new GridBagLayout());
        final GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.insets = new Insets(20, 0, 0, 0);
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.gridwidth = 4;

        add(towerPanelLabel, labelConstraints);

        final GridBagConstraints towerSelectionConstraints = new GridBagConstraints();

        towerSelectionConstraints.fill = GridBagConstraints.HORIZONTAL;
        towerSelectionConstraints.weightx = 1;
        towerSelectionConstraints.weighty = 1;
        towerSelectionConstraints.gridx = 0;
        towerSelectionConstraints.gridy = 1;

        add(towerBuySelections.getArrowTowerSelection(), towerSelectionConstraints);

        towerSelectionConstraints.gridx = 1;
        towerSelectionConstraints.gridy = 1;

        add(towerBuySelections.getElectricTowerSelection(), towerSelectionConstraints);

        towerSelectionConstraints.gridx = 2;
        towerSelectionConstraints.gridy = 1;

        add(towerBuySelections.getCandyTowerSelection(), towerSelectionConstraints);

        towerSelectionConstraints.gridx = 3;
        towerSelectionConstraints.gridy = 1;

        add(towerBuySelections.getBastionTowerSelection(), towerSelectionConstraints);
    }

    private void emitSelectionEvent(PricedSelection<TowerType> selection) {
        final TowerBuildSelectionChangedEvent event = new TowerBuildSelectionChangedEvent(selection);
        eventEmitter.emit(event);
    }
}
