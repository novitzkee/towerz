package com.github.towerz.presentation.components.selection;

import com.github.towerz.game.events.interaction.PricedSelection;
import com.github.towerz.game.events.interaction.soldier.SoldierType;
import com.github.towerz.presentation.components.resources.SoldierIcons;
import lombok.Getter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Getter
public class SoldierBuySelections implements SelectionChangeNotifier<SoldierType> {

    private static final int LIGHT_SOLDIERS_PRICE = 150;

    private static final int MEDIUM_SOLDIERS_PRICE = 200;

    private static final int HEAVY_SOLDIERS_PRICE = 300;

    private static final int SKELETON_SOLDIERS_PRICE = 400;

    private final BuySelection<SoldierType> lightSoldierSelection;

    private final BuySelection<SoldierType> mediumSoldierSelection;

    private final BuySelection<SoldierType> heavySoldierSelection;

    private final BuySelection<SoldierType> skeletonSoldierSelection;

    private final List<Consumer<PricedSelection<SoldierType>>> soldierSelectionConsumer = new ArrayList<>();

    public SoldierBuySelections() {
        final SoldierIcons soldierIcons = new SoldierIcons();

        final JButton lightSoldierButton = new JButton(soldierIcons.getLightSoldierIcon());
        final JButton mediumSoldierButton = new JButton(soldierIcons.getMediumSoldierIcon());
        final JButton heavySoldierButton = new JButton(soldierIcons.getHeavySoldierIcon());
        final JButton skeletonSoldierButton = new JButton(soldierIcons.getSkeletonSoldierIcon());

        this.lightSoldierSelection = new BuySelection<>(LIGHT_SOLDIERS_PRICE, lightSoldierButton, SoldierType.LIGHT);
        this.mediumSoldierSelection = new BuySelection<>(MEDIUM_SOLDIERS_PRICE, mediumSoldierButton, SoldierType.MEDIUM);
        this.heavySoldierSelection = new BuySelection<>(HEAVY_SOLDIERS_PRICE, heavySoldierButton, SoldierType.HEAVY);
        this.skeletonSoldierSelection = new BuySelection<>(SKELETON_SOLDIERS_PRICE, skeletonSoldierButton, SoldierType.SKELETON);

        Stream.of(lightSoldierSelection, mediumSoldierSelection, heavySoldierSelection, skeletonSoldierSelection)
                .forEach(towerSelection -> towerSelection.addSelectionChangeConsumer(this::notifyAnySelectionChanged));
    }

    private void notifyAnySelectionChanged(PricedSelection<SoldierType> soldierType) {
        soldierSelectionConsumer.forEach(consumer -> consumer.accept(soldierType));
    }

    @Override
    public void addSelectionChangeConsumer(Consumer<PricedSelection<SoldierType>> consumer) {
        soldierSelectionConsumer.add(consumer);
    }
}
