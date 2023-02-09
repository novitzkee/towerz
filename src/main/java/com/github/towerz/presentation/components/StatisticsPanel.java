package com.github.towerz.presentation.components;

import com.github.towerz.engine.events.EventListener;
import com.github.towerz.engine.events.Subscriber;
import com.github.towerz.game.events.interaction.castle.CastleGoldChangeEvent;
import com.github.towerz.game.events.interaction.castle.CastleHealthChangeEvent;
import com.github.towerz.game.events.interaction.gameplay.WaveElapsedEvent;
import com.github.towerz.presentation.components.resources.Colors;
import com.github.towerz.presentation.components.resources.FontProvider;
import com.github.towerz.presentation.components.resources.SymbolIcons;
import com.github.towerz.presentation.config.Gameplay;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static com.github.towerz.game.creature.WaveConfiguration.TOTAL_WAVE_AMOUNT;
import static com.github.towerz.presentation.config.Dimensions.SELECTION_WIDTH;

public class StatisticsPanel extends JPanel implements Subscriber {

    private final JLabel healthLabel;

    private final JLabel goldLabel;

    private final JLabel waveLabel;

    @Getter
    private final List<EventListener<?>> eventListeners = List.of(
            EventListener.of(this::updateHealth, CastleHealthChangeEvent.class),
            EventListener.of(this::updateGold, CastleGoldChangeEvent.class),
            EventListener.of(this::updateWave, WaveElapsedEvent.class));

    public StatisticsPanel() {
        final SymbolIcons symbolIcons = new SymbolIcons();

        final ImageIcon heartIcon = symbolIcons.getBigHeartIcon();
        this.healthLabel = new JLabel(String.valueOf(Gameplay.STARTING_CASTLE_HEALTH), heartIcon, SwingConstants.RIGHT);

        final ImageIcon goldIcon = symbolIcons.getBigGoldIcon();
        this.goldLabel = new JLabel(String.valueOf(Gameplay.STARTING_GOLD_AMOUNT), goldIcon, SwingConstants.RIGHT);

        final ImageIcon swordIcon = symbolIcons.getSwordIcon();
        final String waveLabelText = String.format("%d/%d", 1, TOTAL_WAVE_AMOUNT);
        this.waveLabel = new JLabel(waveLabelText, swordIcon, SwingConstants.RIGHT);

        compose();
    }

    private void compose() {
        healthLabel.setFont(FontProvider.get().deriveFont(18f));
        healthLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        goldLabel.setFont(FontProvider.get().deriveFont(18f));
        goldLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        waveLabel.setFont(FontProvider.get().deriveFont(18f));
        goldLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        setPreferredSize(new Dimension(SELECTION_WIDTH, 0));
        setBackground(Colors.STONE_GRAY);

        final JLabel upgradePanelLabel = new JLabel("Stats");
        upgradePanelLabel.setFont(FontProvider.get().deriveFont(18f));
        upgradePanelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        setLayout(new GridBagLayout());

        final GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.fill = GridBagConstraints.VERTICAL;
        layoutConstraints.weighty = 1;
        layoutConstraints.gridx = 0;

        add(upgradePanelLabel, layoutConstraints);

        layoutConstraints.gridy = 1;

        add(goldLabel, layoutConstraints);

        layoutConstraints.gridy = 2;

        add(healthLabel, layoutConstraints);

        layoutConstraints.gridy = 3;

        add(waveLabel, layoutConstraints);
    }

    private void updateHealth(CastleHealthChangeEvent castleHealthChangeEvent) {
        healthLabel.setText(String.valueOf(castleHealthChangeEvent.currentHealth()));
    }

    private void updateGold(CastleGoldChangeEvent castleGoldChangeEvent) {
        goldLabel.setText(String.valueOf(castleGoldChangeEvent.currentGoldAmount()));
    }

    private void updateWave(WaveElapsedEvent waveElapsedEvent) {
        waveLabel.setText(String.format("%d/%d",waveElapsedEvent.currentWave() + 1, waveElapsedEvent.totalWaves()));
    }
}
