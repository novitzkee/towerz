package com.github.towerz.presentation.components.upgrade;

import com.github.towerz.presentation.components.resources.Colors;
import com.github.towerz.presentation.components.resources.FontProvider;

import javax.swing.*;
import java.awt.*;

public class EmptyUpgradeable extends JPanel {

    public EmptyUpgradeable() {
        compose();
    }

    private void compose() {
        setBackground(Colors.STONE_GRAY);
        setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel label = new JLabel("No tower selected");
        label.setFont(FontProvider.get().deriveFont(14f));

        add(label);
    }
}
