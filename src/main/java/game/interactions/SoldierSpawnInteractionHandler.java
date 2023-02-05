package game.interactions;

import engine.events.EventListener;
import engine.events.Subscriber;
import engine.utils.RNG;
import engine.utils.RandomRNG;
import game.creature.Soldier;
import game.creature.SoldierFactory;
import game.events.interaction.SoldierForSpawnSelectionEvent;
import game.events.interaction.SoldierSelection;
import game.interactions.targets.SoldierSpawnInteractionTarget;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class SoldierSpawnInteractionHandler implements Subscriber {

    private final RNG rng = new RandomRNG();

    private final SoldierFactory soldierFactory;

    private final SoldierSpawnInteractionTarget soldierSpawnInteractionTarget;

    @Getter
    private final List<EventListener<?>> eventListeners = List.of(new SoldierForSpawnEventListener());

    private List<Soldier> createSoldiersForSelection(SoldierSelection soldierSelection) {
        final int numberOfSoldiers = rng.randomInt(3, 6);
        return Stream.generate(() -> createSoldier(soldierSelection))
                .limit(numberOfSoldiers)
                .toList();
    }

    private Soldier createSoldier(SoldierSelection soldierSelection) {
        return switch (soldierSelection) {
            case LIGHT -> soldierFactory.createLightSoldier();
            case MEDIUM -> soldierFactory.createMediumSoldier();
            case HEAVY -> soldierFactory.createHeavySoldier();
            case SKELETON -> soldierFactory.createSkeletonSoldier();
        };
    }

    private class SoldierForSpawnEventListener implements EventListener<SoldierForSpawnSelectionEvent> {

        @Override
        public void onEvent(SoldierForSpawnSelectionEvent event) {
            final List<Soldier> soldiers = createSoldiersForSelection(event.soldierSelection());
            soldierSpawnInteractionTarget.spawn(soldiers);
        }

        @Override
        public Class<SoldierForSpawnSelectionEvent> getEventClass() {
            return SoldierForSpawnSelectionEvent.class;
        }
    }
}
