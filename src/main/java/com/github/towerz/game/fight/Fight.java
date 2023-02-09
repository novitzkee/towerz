package com.github.towerz.game.fight;

import com.github.towerz.engine.events.EventListener;
import com.github.towerz.engine.events.Subscriber;
import com.github.towerz.engine.geometry.Range;
import com.github.towerz.engine.graphics.DrawingTarget;
import com.github.towerz.engine.graphics.Paintable;
import com.github.towerz.engine.time.TimeAware;
import com.github.towerz.game.creature.Creature;
import com.github.towerz.game.creature.CreatureType;
import com.github.towerz.game.creature.EnemySpawner;
import com.github.towerz.game.creature.SoldierSpawner;
import com.github.towerz.game.events.world.ProjectileHitEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class Fight implements Paintable, TimeAware, Subscriber {

    private final Towers towers;

    private final Creatures creatures;

    private final EnemySpawner enemySpawner;

    private final SoldierSpawner soldierSpawner;

    @Getter
    private final List<EventListener<?>> eventListeners = List.of(
            EventListener.of(this::handleProjectileHit, ProjectileHitEvent.class));

    @Override
    public void draw(DrawingTarget drawingTarget) {
        creatures.draw(drawingTarget);
        towers.draw(drawingTarget);
    }

    @Override
    public void tick() {
        towers.tick();
        creatures.tick();
        soldierSpawner.tick();
        enemySpawner.tick();
    }

    private void handleProjectileHit(ProjectileHitEvent projectileHitEvent) {
        final Range damageRange = projectileHitEvent.damageRange();
        final List<Creature> damagedMonsters = creatures.getCreaturesInRange(damageRange, CreatureType.ATTACKER);
        damagedMonsters.forEach(creature -> creature.takeDamage(projectileHitEvent.damage()));
    }
}
