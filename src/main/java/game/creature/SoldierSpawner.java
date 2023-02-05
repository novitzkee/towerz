package game.creature;

import engine.action.Action;
import engine.action.ConsecutiveAction;
import engine.time.TimeAware;
import game.fight.Creatures;
import game.interactions.targets.SoldierSpawnInteractionTarget;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

@RequiredArgsConstructor
public class SoldierSpawner implements TimeAware {

    private final Creatures creatures;

    private final Queue<Action> activeSpawnActions = new ArrayDeque<>();

    @Getter
    private final SoldierSpawnInteractionTarget interactionTarget = new SpawningInteractionTarget();

    @Override
    public void tick() {
        if (activeSpawnActions.isEmpty()) return;
        activeSpawnActions.peek().tick();

        if(activeSpawnActions.peek().isGarbage()) {
            activeSpawnActions.poll();
        }
    }

    private class SpawningInteractionTarget implements SoldierSpawnInteractionTarget {

        @Override
        public void spawn(List<Soldier> soldiers) {
            scheduleSpawn(soldiers);
        }

        private void scheduleSpawn(List<Soldier> soldiers) {
            final List<Runnable> spawningRunnables = soldiers.stream()
                    .map(this::createSpawningRunnable)
                    .toList();

            final Action spawningAction = new ConsecutiveAction(20, spawningRunnables);
            activeSpawnActions.offer(spawningAction);
        }

        private Runnable createSpawningRunnable(Soldier soldier) {
            return () -> creatures.add(soldier, CreatureType.DEFENDER);
        }
    }
}
