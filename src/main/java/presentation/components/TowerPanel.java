package presentation.components;

import engine.events.EventEmitter;
import engine.events.EventListener;
import game.events.interaction.TowerBuildSelectionChangedEvent;
import game.events.interaction.TowerSelection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import presentation.components.resources.TowerPanelResources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static presentation.config.Dimensions.SELECTION_WIDTH;

public class TowerPanel extends JPanel {

    private static final Dimension TOWER_BUTTON_DIMENSION = new Dimension(100, 200);
    private final EventEmitter eventEmitter;

    @Getter
    private final List<EventListener<?>> eventListeners = new ArrayList<>();

    public TowerPanel(EventEmitter eventEmitter) {
        this.eventEmitter = eventEmitter;
    }

    public void compose() {
        setPreferredSize(new Dimension(SELECTION_WIDTH, 400));
        setBackground(Color.ORANGE);

        final TowerPanelResources towerPanelResources = new TowerPanelResources();

        final JToggleButton arrowTowerButton = createTowerButton(towerPanelResources.getArrowTowerIcon(), TowerSelection.ARROW);
        final JToggleButton electricTowerButton = createTowerButton(towerPanelResources.getElectricTowerIcon(), TowerSelection.ELECTRIC);
        final JToggleButton candyTowerButton = createTowerButton(towerPanelResources.getCandyTowerIcon(), TowerSelection.CANDY);
        final JToggleButton bastionTowerButton = createTowerButton(towerPanelResources.getBastionTowerIcon(), TowerSelection.BASTION);

        final ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(arrowTowerButton);
        buttonGroup.add(electricTowerButton);
        buttonGroup.add(candyTowerButton);
        buttonGroup.add(bastionTowerButton);

        setLayout(new GridLayout(2, 2));

        add(arrowTowerButton);
        add(electricTowerButton);
        add(candyTowerButton);
        add(bastionTowerButton);
    }

    private JToggleButton createTowerButton(ImageIcon imageIcon, TowerSelection towerSelection) {
        final JToggleButton button = new JToggleButton(imageIcon);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setPreferredSize(TOWER_BUTTON_DIMENSION);
        button.setBackground(Color.LIGHT_GRAY);
        registerActionListener(button, towerSelection);
        return button;
    }

    private void registerActionListener(JToggleButton jToggleButton, TowerSelection towerSelection) {
        jToggleButton.addActionListener(new SelectionToggleListener(towerSelection));
    }

    @RequiredArgsConstructor
    private class SelectionToggleListener implements ActionListener {

        private final TowerSelection targetSelection;

        @Override
        public void actionPerformed(ActionEvent e) {
            final AbstractButton abstractButton = (AbstractButton) e.getSource();
            boolean selected = abstractButton.getModel().isSelected();
            if(selected) {
                final TowerBuildSelectionChangedEvent event = new TowerBuildSelectionChangedEvent(targetSelection);
                eventEmitter.emit(event);
            }
        }
    }
}
