package presentation.components.selection;

import game.events.interaction.PricedSelection;

import java.util.function.Consumer;

public interface SelectionChangeNotifier<T> {

    void addSelectionChangeConsumer(Consumer<PricedSelection<T>> selectionConsumer);

}
