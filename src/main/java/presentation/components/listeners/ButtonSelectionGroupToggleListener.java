package presentation.components.listeners;

import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@RequiredArgsConstructor
public class ButtonSelectionGroupToggleListener implements ActionListener {

    private final Runnable selectionChangeAction;

    @Override
    public void actionPerformed(ActionEvent e) {
        final AbstractButton abstractButton = (AbstractButton) e.getSource();
        boolean changed = didSelectionChange(abstractButton);
        if (changed) {
            selectionChangeAction.run();
        }
    }

    private boolean didSelectionChange(AbstractButton abstractButton) {
        return !(abstractButton instanceof JToggleButton) || abstractButton.getModel().isSelected();
    }
}
