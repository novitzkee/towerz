package presentation.components.selection;

import game.events.interaction.PricedSelection;
import game.events.interaction.TowerType;
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

    private final BuySelection<TowerType> arrowTowerSelection;

    private final BuySelection<TowerType> electricTowerSelection;

    private final BuySelection<TowerType> candyTowerSelection;

    private final BuySelection<TowerType> bastionTowerSelection;

    private final List<Consumer<PricedSelection<TowerType>>> towerSelectionConsumers = new ArrayList<>();

    public TowerBuySelections() {
        final SymbolIcons symbolIcons = new SymbolIcons();
        final TowerIcons towerIcons = new TowerIcons();

        final JToggleButton arrowTowerButton = new JToggleButton(towerIcons.getArrowTowerIcon());
        final JToggleButton electricTowerButton = new JToggleButton(towerIcons.getElectricTowerIcon());
        final JToggleButton candyTowerButton = new JToggleButton(towerIcons.getCandyTowerIcon());
        final JToggleButton bastionTowerButton = new JToggleButton(towerIcons.getBastionTowerIcon());

        final ToggleableButtonGroup buttonGroup = new ToggleableButtonGroup(this::notifySelectionCancelled);

        Stream.of(arrowTowerButton, electricTowerButton, candyTowerButton, bastionTowerButton)
                .forEach(buttonGroup::add);

        this.arrowTowerSelection = new BuySelection<>(ARROW_TOWER_PRICE, arrowTowerButton, TowerType.ARROW, symbolIcons);
        this.electricTowerSelection = new BuySelection<>(ELECTRIC_TOWER_PRICE, electricTowerButton, TowerType.ELECTRIC, symbolIcons);
        this.candyTowerSelection = new BuySelection<>(CANDY_TOWER_PRICE, candyTowerButton, TowerType.CANDY, symbolIcons);
        this.bastionTowerSelection = new BuySelection<>(BASTION_TOWER_PRICE, bastionTowerButton, TowerType.BASTION, symbolIcons);

        Stream.of(arrowTowerSelection, electricTowerSelection, candyTowerSelection, bastionTowerSelection)
                .forEach(towerSelection -> towerSelection.addSelectionChangeConsumer(this::notifyAnySelectionChanged));
    }

    private void notifySelectionCancelled() {
        towerSelectionConsumers.forEach(consumer -> consumer.accept(PricedSelection.empty()));
    }

    private void notifyAnySelectionChanged(PricedSelection<TowerType> towerType) {
        towerSelectionConsumers.forEach(consumer -> consumer.accept(towerType));
    }

    public void addSelectionChangeConsumer(Consumer<PricedSelection<TowerType>> consumer) {
        towerSelectionConsumers.add(consumer);
    }
}
