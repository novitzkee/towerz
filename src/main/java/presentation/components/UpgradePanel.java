package presentation.components;

import engine.events.EventEmitter;
import engine.events.EventListener;
import engine.events.Subscriber;
import engine.traits.Upgradeable;
import game.events.interaction.tower.TowerSellEvent;
import game.events.interaction.tower.TowerUpgradeEvent;
import game.events.interaction.tower.TowerUpgradeSelectionChangedEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import presentation.components.resources.Colors;
import presentation.components.resources.FontProvider;
import presentation.components.resources.SymbolIcons;
import presentation.components.upgrade.ActiveUpgradeable;
import presentation.components.upgrade.EmptyUpgradeable;
import presentation.components.upgrade.MaxUpgradable;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Consumer;

import static presentation.config.Dimensions.SELECTION_WIDTH;

public class UpgradePanel extends JPanel implements Subscriber {

    private final EventEmitter eventEmitter;

    private final JButton upgradeButton;

    private final JLabel upgradePriceLabel;

    private final JButton sellButton;

    private final JLabel sellPriceLabel;

    private Upgradeable currentTower;

    private JPanel currentPanel;

    @Getter
    private final List<EventListener<?>> eventListeners = List.of(new TowerForUpgradeSelectionListener());

    public UpgradePanel(EventEmitter eventEmitter) {
        final SymbolIcons symbolIcons = new SymbolIcons();

        this.eventEmitter = eventEmitter;

        this.sellButton = new JButton("Sell");
        this.upgradeButton = new JButton("Upgrade");

        sellButton.addActionListener(new EnabledButtonClickListener(this::notifyTowerSold));
        upgradeButton.addActionListener(new EnabledButtonClickListener(this::notifyTowerUpgraded));

        this.upgradePriceLabel = new JLabel("100", symbolIcons.getSmallGoldIcon(), SwingConstants.RIGHT);
        upgradePriceLabel.setFont(FontProvider.get().deriveFont(14f));

        this.sellPriceLabel = new JLabel("100", symbolIcons.getSmallGoldIcon(), SwingConstants.RIGHT);
        sellPriceLabel.setFont(FontProvider.get().deriveFont(14f));

        compose();
    }

    private void compose() {
        setPreferredSize(new Dimension(SELECTION_WIDTH, 0));
        setBackground(Colors.STONE_GRAY);

        final JLabel upgradePanelLabel = new JLabel("Build");
        upgradePanelLabel.setFont(FontProvider.get().deriveFont(18f));
        upgradePanelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        sellButton.setFont(FontProvider.get().deriveFont(16f));
        sellButton.setBackground(Colors.STONE_GRAY);
        sellButton.setBorder(new LineBorder(Color.BLACK));

        upgradeButton.setFont(FontProvider.get().deriveFont(16f));
        upgradeButton.setBackground(Colors.STONE_GRAY);
        upgradeButton.setBorder(new LineBorder(Color.BLACK));

        setLayout(new GridBagLayout());

        final GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.insets = new Insets(10, 0, 0, 0);
        labelConstraints.gridwidth = 2;
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;

        add(upgradePanelLabel, labelConstraints);

        final GridBagConstraints buttonsConstraints = new GridBagConstraints();
        buttonsConstraints.fill = GridBagConstraints.HORIZONTAL;
        buttonsConstraints.insets = new Insets(2, 10, 2, 10);
        buttonsConstraints.gridx = 0;
        buttonsConstraints.gridy = 2;

        add(sellButton, buttonsConstraints);

        buttonsConstraints.gridx = 1;
        buttonsConstraints.gridy = 2;

        add(upgradeButton, buttonsConstraints);

        final GridBagConstraints priceConstraints = new GridBagConstraints();
        buttonsConstraints.fill = GridBagConstraints.HORIZONTAL;

        priceConstraints.gridx = 0;
        priceConstraints.gridy = 3;

        add(sellPriceLabel, priceConstraints);

        priceConstraints.gridx = 1;
        priceConstraints.gridy = 3;

        add(upgradePriceLabel, priceConstraints);

        updateUpgradable(null);
    }

    private void updateUpgradable(Upgradeable upgradeable) {
        currentTower = upgradeable;
        final JPanel newPanel = getPanelToDisplay(upgradeable);
        swapPanel(newPanel);
        updateButtons(upgradeable);
    }

    private JPanel getPanelToDisplay(Upgradeable upgradeable) {
        if (upgradeable == null) {
            return new EmptyUpgradeable();
        } else if (upgradeable.canUpgrade()) {
            return new ActiveUpgradeable(upgradeable);
        } else {
            return new MaxUpgradable(upgradeable);
        }
    }

    private void swapPanel(JPanel newPanel) {
        if (currentPanel != null) {
            remove(currentPanel);
        }

        add(newPanel, createChildPanelConstraints());
        currentPanel = newPanel;

        revalidate();
        repaint();
    }

    private GridBagConstraints createChildPanelConstraints() {
        final GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridwidth = 2;
        layoutConstraints.weighty = 1;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;

        return layoutConstraints;
    }

    private void updateButtons(Upgradeable upgradeable) {
        if (upgradeable == null) {
            setUpgradeActive(false);
            setSellActive(false);
        } else {
            setUpgradeActive(upgradeable.canUpgrade());
            setSellActive(true);
        }
    }

    private void setUpgradeActive(boolean value) {
        upgradePriceLabel.setVisible(value);
        upgradeButton.setEnabled(value);
    }

    private void setSellActive(boolean value) {
        sellPriceLabel.setVisible(value);
        sellButton.setEnabled(value);
    }

    private void notifyTowerUpgraded(Upgradeable tower) {
        final TowerUpgradeEvent event = new TowerUpgradeEvent(tower);
        eventEmitter.emit(event);
    }

    private void notifyTowerSold(Upgradeable tower) {
        final TowerSellEvent event = new TowerSellEvent(tower);
        eventEmitter.emit(event);
    }

    private class TowerForUpgradeSelectionListener implements EventListener<TowerUpgradeSelectionChangedEvent> {

        @Override
        public void onEvent(TowerUpgradeSelectionChangedEvent event) {
            updateUpgradable(event.currentUpgradeable());
        }

        @Override
        public Class<TowerUpgradeSelectionChangedEvent> getEventClass() {
            return TowerUpgradeSelectionChangedEvent.class;
        }
    }

    @RequiredArgsConstructor
    private class EnabledButtonClickListener implements ActionListener {

        private final Consumer<Upgradeable> upgradeableConsumer;

        @Override
        public void actionPerformed(ActionEvent e) {
            final JButton button = (JButton) e.getSource();
            if (button.isEnabled()) {
                upgradeableConsumer.accept(currentTower);
            }
        }
    }
}
