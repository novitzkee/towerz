package presentation.components.selection;

import game.events.interaction.TowerSelection;
import lombok.Getter;
import presentation.components.resources.SymbolIcons;
import presentation.components.resources.TowerIcons;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Getter
public class TowerBuySelections {

    private static final int ARROW_TOWER_PRICE = 200;

    private static final int ELECTRIC_TOWER_PRICE = 500;

    private static final int CANDY_TOWER_PRICE = 1000;

    private static final int BASTION_TOWER_PRICE = 2000;

    private final BuySelection<TowerSelection> arrowTowerSelection;

    private final BuySelection<TowerSelection> electricTowerSelection;

    private final BuySelection<TowerSelection> candyTowerSelection;

    private final BuySelection<TowerSelection> bastionTowerSelection;

    private final List<Consumer<TowerSelection>> towerSelectionConsumers = new ArrayList<>();

    public TowerBuySelections() {
        final SymbolIcons symbolIcons = new SymbolIcons();
        final TowerIcons towerIcons = new TowerIcons();

        final JToggleButton arrowTowerButton = new JToggleButton(towerIcons.getArrowTowerIcon());
        final JToggleButton electricTowerButton = new JToggleButton(towerIcons.getElectricTowerIcon());
        final JToggleButton candyTowerButton = new JToggleButton(towerIcons.getCandyTowerIcon());
        final JToggleButton bastionTowerButton = new JToggleButton(towerIcons.getBastionTowerIcon());

        final ToggleableButtonGroup buttonGroup = new ToggleableButtonGroup(() -> notifyAnySelectionChanged(null));

        Stream.of(arrowTowerButton, electricTowerButton, candyTowerButton, bastionTowerButton)
                .forEach(buttonGroup::add);

        this.arrowTowerSelection = new BuySelection<>(ARROW_TOWER_PRICE, arrowTowerButton, TowerSelection.ARROW, symbolIcons);
        this.electricTowerSelection = new BuySelection<>(ELECTRIC_TOWER_PRICE, electricTowerButton, TowerSelection.ELECTRIC, symbolIcons);
        this.candyTowerSelection = new BuySelection<>(CANDY_TOWER_PRICE, candyTowerButton, TowerSelection.CANDY, symbolIcons);
        this.bastionTowerSelection = new BuySelection<>(BASTION_TOWER_PRICE, bastionTowerButton, TowerSelection.BASTION, symbolIcons);

        Stream.of(arrowTowerSelection, electricTowerSelection, candyTowerSelection, bastionTowerSelection)
                .forEach(towerSelection -> towerSelection.addSelectionChangeConsumer(this::notifyAnySelectionChanged));
    }

    private void notifyAnySelectionChanged(TowerSelection towerSelection) {
        towerSelectionConsumers.forEach(consumer -> consumer.accept(towerSelection));
    }

    public void addSelectionChangeConsumer(Consumer<TowerSelection> consumer) {
        towerSelectionConsumers.add(consumer);
    }
}
