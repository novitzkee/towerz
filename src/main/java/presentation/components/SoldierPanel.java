package presentation.components;

import engine.events.EventEmitter;
import game.events.interaction.PricedSelection;
import game.events.interaction.soldier.SoldierForSpawnSelectionEvent;
import game.events.interaction.soldier.SoldierType;
import presentation.components.resources.Colors;
import presentation.components.resources.FontProvider;
import presentation.components.selection.SoldierBuySelections;

import javax.swing.*;
import java.awt.*;

import static presentation.config.Dimensions.SELECTION_WIDTH;

public class SoldierPanel extends JPanel {

    private final EventEmitter eventEmitter;

    private final SoldierBuySelections soldierBuySelections = new SoldierBuySelections();

    public SoldierPanel(EventEmitter eventEmitter) {
        this.eventEmitter = eventEmitter;
        compose();
        soldierBuySelections.addSelectionChangeConsumer(this::notifySelected);
    }

    private void compose() {
        setPreferredSize(new Dimension(SELECTION_WIDTH, 0));
        setBackground(Colors.STONE_GRAY);

        final JLabel soldierPanelLabel = new JLabel("Soldiers");
        soldierPanelLabel.setFont(FontProvider.get().deriveFont(18f));
        soldierPanelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        setLayout(new GridBagLayout());
        final GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.gridwidth = 4;

        add(soldierPanelLabel, labelConstraints);

        final GridBagConstraints towerSelectionConstraints = new GridBagConstraints();

        towerSelectionConstraints.fill = GridBagConstraints.HORIZONTAL;
        towerSelectionConstraints.weightx = 1;
        towerSelectionConstraints.weighty = 1;
        towerSelectionConstraints.gridx = 0;
        towerSelectionConstraints.gridy = 1;

        add(soldierBuySelections.getLightSoldierSelection(), towerSelectionConstraints);

        towerSelectionConstraints.gridx = 1;
        towerSelectionConstraints.gridy = 1;

        add(soldierBuySelections.getMediumSoldierSelection(), towerSelectionConstraints);

        towerSelectionConstraints.gridx = 2;
        towerSelectionConstraints.gridy = 1;

        add(soldierBuySelections.getHeavySoldierSelection(), towerSelectionConstraints);

        towerSelectionConstraints.gridx = 3;
        towerSelectionConstraints.gridy = 1;

        add(soldierBuySelections.getSkeletonSoldierSelection(), towerSelectionConstraints);
    }

    private void notifySelected(PricedSelection<SoldierType> soldierType) {
        final SoldierForSpawnSelectionEvent event = new SoldierForSpawnSelectionEvent(soldierType);
        eventEmitter.emit(event);
    }
}
