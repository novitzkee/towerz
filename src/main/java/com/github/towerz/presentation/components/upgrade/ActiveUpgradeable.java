package com.github.towerz.presentation.components.upgrade;

import com.github.towerz.engine.traits.Upgradeable;
import com.github.towerz.presentation.components.resources.Colors;
import com.github.towerz.presentation.components.resources.SymbolIcons;

import javax.swing.*;
import java.awt.*;

public class ActiveUpgradeable extends JPanel {

    public ActiveUpgradeable(Upgradeable upgradeable) {
        compose(upgradeable);
    }

    private void compose(Upgradeable upgradeable) {
        final SymbolIcons symbolIcons = new SymbolIcons();
        setBackground(Colors.STONE_GRAY);
        setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel currentIconDummyLabel = new JLabel("", upgradeable.getCurrentIcon(), SwingConstants.CENTER);
        final JLabel arrowDummyLabel = new JLabel("", symbolIcons.getArrowIcon(), SwingConstants.CENTER);
        final JLabel upgradeIconDummyLabel = new JLabel("", upgradeable.getUpgradedIcon(), SwingConstants.CENTER);

        setLayout(new GridBagLayout());
        final GridBagConstraints iconConstraints = new GridBagConstraints();
        iconConstraints.fill = GridBagConstraints.HORIZONTAL;
        iconConstraints.insets = new Insets(0, 10, 0, 10);

        add(currentIconDummyLabel, iconConstraints);
        add(arrowDummyLabel, iconConstraints);
        add(upgradeIconDummyLabel, iconConstraints);
    }
}
