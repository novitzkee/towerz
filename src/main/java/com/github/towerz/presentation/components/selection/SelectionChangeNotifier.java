package com.github.towerz.presentation.components.selection;

import com.github.towerz.game.events.interaction.PricedSelection;

import java.util.function.Consumer;

public interface SelectionChangeNotifier<T> {

    void addSelectionChangeConsumer(Consumer<PricedSelection<T>> selectionConsumer);

}
