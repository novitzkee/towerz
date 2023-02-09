package com.github.towerz.presentation.components.selection;

import com.github.towerz.game.events.interaction.PricedSelection;
import com.github.towerz.presentation.components.listeners.ButtonSelectionGroupToggleListener;
import com.github.towerz.presentation.components.resources.Colors;
import com.github.towerz.presentation.components.resources.FontProvider;
import com.github.towerz.presentation.components.resources.SymbolIcons;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BuySelection<T> extends JPanel {

    private final PricedSelection<T> selection;

    private final List<Consumer<PricedSelection<T>>> selectionConsumers = new ArrayList<>();

    public BuySelection(int price, AbstractButton button, T selection) {
        this.selection = new PricedSelection<>(price, selection);
        compose(button, price);
        button.addActionListener(new ButtonSelectionGroupToggleListener(this::notifySelectionChanged));
    }

    private void compose(AbstractButton button, int price) {
        final SymbolIcons symbolIcons = new SymbolIcons();

        setBackground(Colors.STONE_GRAY);

        button.setBackground(Colors.STONE_GRAY);
        button.setBorder(new LineBorder(Color.BLACK));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        final ImageIcon goldIcon = symbolIcons.getSmallGoldIcon();
        final JLabel label = new JLabel(toPaddedString(price), goldIcon, SwingConstants.RIGHT);
        label.setFont(FontProvider.get().deriveFont(16f));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        setLayout(new GridBagLayout());
        final GridBagConstraints layoutConstraints = new GridBagConstraints();

        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;

        add(button, layoutConstraints);

        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.ipady = 20;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;

        add(label, layoutConstraints);
    }

    private String toPaddedString(int price) {
        final String priceValue = String.valueOf(price);
        return String.format("%-" + 4 + "s", priceValue);
    }

    public void addSelectionChangeConsumer(Consumer<PricedSelection<T>> selectionConsumer) {
        selectionConsumers.add(selectionConsumer);
    }

    private void notifySelectionChanged() {
        selectionConsumers.forEach(consumer -> consumer.accept(selection));
    }
}
