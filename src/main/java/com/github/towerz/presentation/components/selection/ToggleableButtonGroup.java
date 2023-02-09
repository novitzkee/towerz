package com.github.towerz.presentation.components.selection;

import lombok.RequiredArgsConstructor;

import javax.swing.*;

@RequiredArgsConstructor
public class ToggleableButtonGroup extends ButtonGroup {

    private final Runnable clearSelectionAction;

    @Override
    public void setSelected(ButtonModel model, boolean selected) {
        if (selected) {
            super.setSelected(model, true);
        } else {
            clearSelection();
            clearSelectionAction.run();
        }
    }
}
