package com.github.towerz.presentation.components.upgrade;

import com.github.towerz.engine.traits.Upgradeable;
import com.github.towerz.presentation.components.resources.Colors;

import javax.swing.*;
import java.awt.*;

public class MaxUpgradable extends JPanel {

    public MaxUpgradable(Upgradeable upgradeable) {
        compose(upgradeable);
    }

    private void compose(Upgradeable upgradeable) {
        setBackground(Colors.STONE_GRAY);
        setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel icon = new JLabel("", upgradeable.getCurrentIcon(), SwingConstants.CENTER);
        add(icon);
    }
}
