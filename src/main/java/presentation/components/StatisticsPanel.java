package presentation.components;

import engine.events.EventListener;
import engine.events.Subscriber;
import game.events.interaction.castle.CastleGoldChangeEvent;
import game.events.interaction.castle.CastleHealthChangeEvent;
import lombok.Getter;
import presentation.components.resources.FontProvider;
import presentation.components.resources.SymbolIcons;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static presentation.components.SidePanel.SIDE_PANEL_COLOR;
import static presentation.config.Dimensions.SELECTION_WIDTH;
import static presentation.config.Gameplay.STARTING_CASTLE_HEALTH;
import static presentation.config.Gameplay.STARTING_GOLD_AMOUNT;

public class StatisticsPanel extends JPanel implements Subscriber {

    private final JLabel healthLabel;

    private final JLabel goldLabel;

    @Getter
    private final List<EventListener<?>> eventListeners = List.of(
            new CastleHealthChangeListener(),
            new CastleGoldChangeListener());

    public StatisticsPanel() {
        final SymbolIcons symbolIcons = new SymbolIcons();
        final ImageIcon heartIcon = symbolIcons.getBigHeartIcon();

        this.healthLabel = new JLabel(String.valueOf(STARTING_CASTLE_HEALTH), heartIcon, SwingConstants.RIGHT);
        healthLabel.setFont(FontProvider.get().deriveFont(18f));
        healthLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        final ImageIcon goldIcon = symbolIcons.getBigGoldIcon();
        this.goldLabel = new JLabel(String.valueOf(STARTING_GOLD_AMOUNT), goldIcon, SwingConstants.RIGHT);
        goldLabel.setFont(FontProvider.get().deriveFont(18f));
        goldLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public void compose() {
        setPreferredSize(new Dimension(SELECTION_WIDTH, 0));
        setBackground(SIDE_PANEL_COLOR);

        final JLabel upgradePanelLabel = new JLabel("Stats");
        upgradePanelLabel.setFont(FontProvider.get().deriveFont(18f));
        upgradePanelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        setLayout(new GridBagLayout());

        final GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.fill = GridBagConstraints.VERTICAL;
        layoutConstraints.weighty = 1;
        layoutConstraints.gridx = 0;

        add(upgradePanelLabel, layoutConstraints);

        layoutConstraints.fill = GridBagConstraints.WEST;
        layoutConstraints.anchor = GridBagConstraints.WEST;
        layoutConstraints.gridy = 1;

        add(goldLabel, layoutConstraints);

        layoutConstraints.fill = GridBagConstraints.WEST;
        layoutConstraints.anchor = GridBagConstraints.WEST;
        layoutConstraints.gridy = 2;

        add(healthLabel, layoutConstraints);
    }

    private void updateHealth(int health) {
        healthLabel.setText(String.valueOf(health));
    }

    private void updateGold(int gold) {
        goldLabel.setText(String.valueOf(gold));
    }

    private class CastleHealthChangeListener implements EventListener<CastleHealthChangeEvent> {

        @Override
        public void onEvent(CastleHealthChangeEvent event) {
            updateHealth(event.currentHealth());
        }

        @Override
        public Class<CastleHealthChangeEvent> getEventClass() {
            return CastleHealthChangeEvent.class;
        }
    }

    private class CastleGoldChangeListener implements EventListener<CastleGoldChangeEvent> {

        @Override
        public void onEvent(CastleGoldChangeEvent event) {
            updateGold(event.currentGoldAmount());
        }

        @Override
        public Class<CastleGoldChangeEvent> getEventClass() {
            return CastleGoldChangeEvent.class;
        }
    }
}
