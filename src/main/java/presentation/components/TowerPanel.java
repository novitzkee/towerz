package presentation.components;

import engine.events.EventEmitter;
import engine.events.EventListener;
import game.events.interaction.PricedSelection;
import game.events.interaction.tower.TowerBuildSelectionChangedEvent;
import game.events.interaction.tower.TowerType;
import lombok.Getter;
import presentation.components.resources.Colors;
import presentation.components.resources.FontProvider;
import presentation.components.selection.TowerBuySelections;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static presentation.config.Dimensions.SELECTION_WIDTH;

public class TowerPanel extends JPanel {

    private final EventEmitter eventEmitter;

    @Getter
    private final List<EventListener<?>> eventListeners = new ArrayList<>();

    private final TowerBuySelections towerBuySelections = new TowerBuySelections();

    public TowerPanel(EventEmitter eventEmitter) {
        this.eventEmitter = eventEmitter;
        towerBuySelections.addSelectionChangeConsumer(this::emitSelectionEvent);
    }

    public void compose() {
        setPreferredSize(new Dimension(SELECTION_WIDTH, 0));
        setBackground(Colors.TRANSPARENT);

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
