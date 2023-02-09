package com.github.towerz.game.creature;

import com.github.towerz.engine.action.Action;
import com.github.towerz.engine.action.ConsecutiveAction;
import com.github.towerz.engine.action.OneTimeAction;
import com.github.towerz.engine.time.TimeAware;
import com.github.towerz.game.fight.Creatures;
import com.github.towerz.game.interactions.targets.SoldierSpawnInteractionTarget;
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
