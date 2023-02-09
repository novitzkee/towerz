package com.github.towerz.game.tower.projectiles;

import com.github.towerz.engine.events.EventEmitter;
import com.github.towerz.engine.geometry.Angle;
import com.github.towerz.engine.geometry.Range;
import com.github.towerz.engine.geometry.Vector2i;
import com.github.towerz.engine.graphics.DrawingPositioning;
import com.github.towerz.engine.graphics.DrawingTarget;
import com.github.towerz.engine.graphics.Paintable;
import com.github.towerz.engine.graphics.sprites.Sprite;
import com.github.towerz.engine.time.TimeAware;
import com.github.towerz.engine.traits.Removable;
import com.github.towerz.game.events.world.ProjectileHitEvent;

public class Projectile implements TimeAware, Removable, Paintable {

    private final EventEmitter eventEmitter;

    private final Range damageRange;

    private final Vector2i realSource;

    private final Vector2i realTarget;

    private final int realDistancePx;

    private final ProjectileStats stats;

    private final Sprite sprite;

    private int distanceCoveredPx = 0;

    public Projectile(EventEmitter eventEmitter,
                      Range damageRange,
                      Vector2i realSource,
                      Vector2i realTarget,
                      ProjectileStats stats,
                      Sprite sprite) {
        this.eventEmitter = eventEmitter;
        this.damageRange = damageRange;
        this.realSource = realSource;
        this.realTarget = realTarget;
        this.realDistancePx = (int) Math.round(realTarget.sub(realSource).getLength());
        this.stats = stats;
        this.sprite = sprite.rotate(Angle.between(realSource, realTarget));
    }

    @Override
    public void tick() {
        move();
    }

    private void move() {
        distanceCoveredPx += stats.speed();
        distanceCoveredPx = Math.min(realDistancePx, distanceCoveredPx);
    }

    @Override
    public void draw(DrawingTarget drawingTarget) {
        final Vector2i realPosition = realSource.interpolate(realTarget, (float) distanceCoveredPx / realDistancePx);
        sprite.draw(realPosition, drawingTarget, DrawingPositioning.ABSOLUTE);
    }

    @Override
    public boolean isGarbage() {
        return distanceCoveredPx >= realDistancePx;
    }

    @Override
    public void cleanUp() {
        final ProjectileHitEvent projectileHitEvent = new ProjectileHitEvent(damageRange, stats.damage());
        eventEmitter.emit(projectileHitEvent);
    }
}
