package presentation.components.selection;

import game.events.interaction.PricedSelection;
import lombok.RequiredArgsConstructor;
import presentation.components.resources.Colors;
import presentation.components.resources.FontProvider;
import presentation.components.resources.SymbolIcons;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static presentation.components.SidePanel.SIDE_PANEL_COLOR;

public class BuySelection<T> extends JPanel {

    private final PricedSelection<T> selection;

    private final List<Consumer<PricedSelection<T>>> selectionConsumers = new ArrayList<>();

    public BuySelection(int price, AbstractButton button, T selection, SymbolIcons symbolIcons) {
        this.selection = new PricedSelection<>(price, selection);
        setBackground(Colors.TRANSPARENT);

        button.setBackground(SIDE_PANEL_COLOR);
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

        button.addActionListener(new SelectionToggleChangeListener());
    }

    private String toPaddedString(int price) {
        final String priceValue = String.valueOf(price);
        return String.format("%-" + 4 + "s", priceValue);
    }

    public void addSelectionChangeConsumer(Consumer<PricedSelection<T>> selectionConsumer) {
        selectionConsumers.add(selectionConsumer);
    }

    private void notifySelectionChanged(PricedSelection<T> selection) {
        selectionConsumers.forEach(consumer -> consumer.accept(selection));
    }

    @RequiredArgsConstructor
    private class SelectionToggleChangeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            final AbstractButton abstractButton = (AbstractButton) e.getSource();
            boolean changed = didSelectionChange(abstractButton);
            if(changed) {
                notifySelectionChanged(selection);
            }
        }

        private boolean didSelectionChange(AbstractButton abstractButton) {
            return !(abstractButton instanceof JToggleButton) || abstractButton.getModel().isSelected();
        }
    }
}
