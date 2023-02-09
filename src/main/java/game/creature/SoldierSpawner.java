package game.creature;

import engine.action.Action;
import engine.action.ConsecutiveAction;
import engine.action.OneTimeAction;
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

        if(activeSpawnActions.peek().isFinished()) {
            activeSpawnActions.poll();
        }
    }

    private class SpawningInteractionTarget implements SoldierSpawnInteractionTarget {

        @Override
        public void spawn(List<Soldier> soldiers) {
            scheduleSpawn(soldiers);
        }

        private void scheduleSpawn(List<Soldier> soldiers) {
            final List<Action> spawnActions = soldiers.stream()
                    .map(this::createSpawningAction)
                    .toList();

            final Action spawningAction = new ConsecutiveAction(20, spawnActions);
            activeSpawnActions.offer(spawningAction);
        }

        private Action createSpawningAction(Soldier soldier) {
            final Runnable spawn = () -> creatures.add(soldier, CreatureType.DEFENDER);
            return new OneTimeAction(spawn);
        }
    }
}
