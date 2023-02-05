package game.interactions;

import engine.events.EventListener;
import engine.events.Subscriber;
import engine.utils.RNG;
import engine.utils.RandomRNG;
import game.creature.Soldier;
import game.creature.SoldierFactory;
import game.events.interaction.PricedSelection;
import game.events.interaction.soldier.SoldierForSpawnSelectionEvent;
import game.events.interaction.soldier.SoldierType;
import game.interactions.targets.SoldierSpawnInteractionTarget;
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
    private final List<EventListener<?>> eventListeners = List.of(new SoldierForSpawnEventListener());

    private void handleSpawnSelection(PricedSelection<SoldierType> soldierSelection) {
        if(!gameStatisticsHolder.canPurchase(soldierSelection.price())) return;

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

    private class SoldierForSpawnEventListener implements EventListener<SoldierForSpawnSelectionEvent> {

        @Override
        public void onEvent(SoldierForSpawnSelectionEvent event) {
            handleSpawnSelection(event.currentSelection());
        }

        @Override
        public Class<SoldierForSpawnSelectionEvent> getEventClass() {
            return SoldierForSpawnSelectionEvent.class;
        }
    }
}
