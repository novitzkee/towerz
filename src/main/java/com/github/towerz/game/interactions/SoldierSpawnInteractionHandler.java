package com.github.towerz.game.interactions;

import com.github.towerz.engine.events.EventListener;
import com.github.towerz.engine.events.Subscriber;
import com.github.towerz.engine.utils.RNG;
import com.github.towerz.engine.utils.RandomRNG;
import com.github.towerz.game.creature.Soldier;
import com.github.towerz.game.creature.SoldierFactory;
import com.github.towerz.game.events.interaction.PricedSelection;
import com.github.towerz.game.events.interaction.soldier.SoldierForSpawnSelectionEvent;
import com.github.towerz.game.events.interaction.soldier.SoldierType;
import com.github.towerz.game.interactions.targets.SoldierSpawnInteractionTarget;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class SoldierSpawnInteractionHandler implements Subscriber {

    private final RNG rng = new RandomRNG();

    private final GameStatisticsHolder gameStatisticsHolder;

    private final SoldierFactory soldierFactory;

    private final SoldierSpawnInteractionTarget soldierSpawnInteractionTarget;

    @Getter
    private final List<EventListener<?>> eventListeners = List.of(
            EventListener.of(this::handleSpawnSelectionEvent, SoldierForSpawnSelectionEvent.class));

    private void handleSpawnSelectionEvent(SoldierForSpawnSelectionEvent soldierForSpawnSelectionEvent) {
        final PricedSelection<SoldierType> soldierSelection = soldierForSpawnSelectionEvent.currentSelection();

        if (!gameStatisticsHolder.canPurchase(soldierSelection.price())) return;

        gameStatisticsHolder.purchase(soldierSelection.price());
        final List<Soldier> soldiers = createSoldiersForSelection(soldierSelection.selection());
        soldierSpawnInteractionTarget.spawn(soldiers);
    }

    private List<Soldier> createSoldiersForSelection(SoldierType soldierType) {
        final int numberOfSoldiers = rng.randomInt(3, 6);
        return Stream.generate(() -> createSoldier(soldierType))
                .limit(numberOfSoldiers)
                .toList();
    }

    private Soldier createSoldier(SoldierType soldierType) {
        return switch (soldierType) {
            case LIGHT -> soldierFactory.createLightSoldier();
            case MEDIUM -> soldierFactory.createMediumSoldier();
            case HEAVY -> soldierFactory.createHeavySoldier();
            case SKELETON -> soldierFactory.createSkeletonSoldier();
        };
    }
}
