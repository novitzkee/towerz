package presentation.components.selection;

import game.events.interaction.SoldierSelection;
import lombok.Getter;
import presentation.components.resources.SoldierIcons;
import presentation.components.resources.SymbolIcons;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Getter
public class SoldierBuySelections {

    private static final int LIGHT_SOLDIERS_PRICE = 100;

    private static final int MEDIUM_SOLDIERS_PRICE = 200;

    private static final int HEAVY_SOLDIERS_PRICE = 300;

    private static final int SKELETON_SOLDIERS_PRICE = 500;

    private final BuySelection<SoldierSelection> lightSoldierSelection;

    private final BuySelection<SoldierSelection> mediumSoldierSelection;

    private final BuySelection<SoldierSelection> heavySoldierSelection;

    private final BuySelection<SoldierSelection> skeletonSoldierSelection;

    private final List<Consumer<SoldierSelection>> soldierSelectionConsumer = new ArrayList<>();

    public SoldierBuySelections() {
        final SymbolIcons symbolIcons = new SymbolIcons();
        final SoldierIcons soldierIcons = new SoldierIcons();

        final JButton lightSoldierButton = new JButton(soldierIcons.getLightSoldierIcon());
        final JButton mediumSoldierButton = new JButton(soldierIcons.getMediumSoldierIcon());
        final JButton heavySoldierButton = new JButton(soldierIcons.getHeavySoldierIcon());
        final JButton skeletonSoldierButton = new JButton(soldierIcons.getSkeletonSoldierIcon());

        this.lightSoldierSelection = new BuySelection<>(LIGHT_SOLDIERS_PRICE, lightSoldierButton, SoldierSelection.LIGHT, symbolIcons);
        this.mediumSoldierSelection = new BuySelection<>(MEDIUM_SOLDIERS_PRICE, mediumSoldierButton, SoldierSelection.MEDIUM, symbolIcons);
        this.heavySoldierSelection = new BuySelection<>(HEAVY_SOLDIERS_PRICE, heavySoldierButton, SoldierSelection.HEAVY, symbolIcons);
        this.skeletonSoldierSelection = new BuySelection<>(SKELETON_SOLDIERS_PRICE, skeletonSoldierButton, SoldierSelection.SKELETON, symbolIcons);

        Stream.of(lightSoldierSelection, mediumSoldierSelection, heavySoldierSelection, skeletonSoldierSelection)
                .forEach(towerSelection -> towerSelection.addSelectionChangeConsumer(this::notifyAnySelectionChanged));
    }

    private void notifyAnySelectionChanged(SoldierSelection soldierSelection) {
        soldierSelectionConsumer.forEach(consumer -> consumer.accept(soldierSelection));
    }

    public void addSelectionChangeConsumer(Consumer<SoldierSelection> consumer) {
        soldierSelectionConsumer.add(consumer);
    }
}
