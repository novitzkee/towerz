package com.github.towerz.presentation.components.selection;

import com.github.towerz.game.events.interaction.PricedSelection;
import com.github.towerz.game.events.interaction.tower.TowerType;
import com.github.towerz.presentation.components.resources.TowerIcons;
import lombok.Getter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Getter
public class TowerBuySelections implements SelectionChangeNotifier<TowerType> {

    private static final int ARROW_TOWER_PRICE = 500;

    private static final int ELECTRIC_TOWER_PRICE = 1000;

    private static final int CANDY_TOWER_PRICE = 1500;

    private static final int BASTION_TOWER_PRICE = 2000;

    private final BuySelection<TowerType> arrowTowerSelection;

    private final BuySelection<TowerType> electricTowerSelection;

    private final BuySelection<TowerType> candyTowerSelection;

    private final BuySelection<TowerType> bastionTowerSelection;

    private final List<Consumer<PricedSelection<TowerType>>> towerSelectionConsumers = new ArrayList<>();

    public TowerBuySelections() {
        final TowerIcons towerIcons = new TowerIcons();

        final JToggleButton arrowTowerButton = new JToggleButton(towerIcons.getArrowTowerIcon());
        final JToggleButton electricTowerButton = new JToggleButton(towerIcons.getElectricTowerIcon());
        final JToggleButton candyTowerButton = new JToggleButton(towerIcons.getCandyTowerIcon());
        final JToggleButton bastionTowerButton = new JToggleButton(towerIcons.getBastionTowerIcon());

        final ToggleableButtonGroup buttonGroup = new ToggleableButtonGroup(this::notifySelectionCancelled);

        Stream.of(arrowTowerButton, electricTowerButton, candyTowerButton, bastionTowerButton)
                .forEach(buttonGroup::add);

        this.arrowTowerSelection = new BuySelection<>(ARROW_TOWER_PRICE, arrowTowerButton, TowerType.ARROW);
        this.electricTowerSelection = new BuySelection<>(ELECTRIC_TOWER_PRICE, electricTowerButton, TowerType.ELECTRIC);
        this.candyTowerSelection = new BuySelection<>(CANDY_TOWER_PRICE, candyTowerButton, TowerType.CANDY);
        this.bastionTowerSelection = new BuySelection<>(BASTION_TOWER_PRICE, bastionTowerButton, TowerType.BASTION);

        Stream.of(arrowTowerSelection, electricTowerSelection, candyTowerSelection, bastionTowerSelection)
                .forEach(towerSelection -> towerSelection.addSelectionChangeConsumer(this::notifyAnySelectionChanged));
    }

    private void notifySelectionCancelled() {
        towerSelectionConsumers.forEach(consumer -> consumer.accept(PricedSelection.empty()));
    }

    private void notifyAnySelectionChanged(PricedSelection<TowerType> towerType) {
        towerSelectionConsumers.forEach(consumer -> consumer.accept(towerType));
    }

    @Override
    public void addSelectionChangeConsumer(Consumer<PricedSelection<TowerType>> consumer) {
        towerSelectionConsumers.add(consumer);
    }
}
