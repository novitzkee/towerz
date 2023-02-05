package presentation.components.upgrade;

import engine.traits.Upgradeable;

import javax.swing.*;
import java.awt.*;

import static presentation.components.SidePanel.SIDE_PANEL_COLOR;

public class MaxUpgradable extends JPanel {

    public MaxUpgradable(Upgradeable upgradeable) {
        setBackground(SIDE_PANEL_COLOR);
        setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel icon = new JLabel("", upgradeable.getCurrentIcon(), SwingConstants.CENTER);
        add(icon);
    }
}
