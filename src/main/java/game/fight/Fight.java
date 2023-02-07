package game.fight;

import engine.events.EventListener;
import engine.events.Subscriber;
import engine.geometry.Range;
import engine.graphics.DrawingTarget;
import engine.graphics.Paintable;
import engine.time.TimeAware;
import game.creature.Creature;
import game.creature.CreatureType;
import game.creature.EnemySpawner;
import game.creature.SoldierSpawner;
import game.events.world.ProjectileHitEvent;
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
