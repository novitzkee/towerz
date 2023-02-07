package game.tower.projectiles;

import engine.events.EventEmitter;
import engine.geometry.Range;
import engine.geometry.Vector2i;
import engine.graphics.sprites.Sprite;
import game.tower.TowerLevel;
import game.world.GameGeometry;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ProjectileFactory {

    private final EventEmitter eventEmitter;

    private final GameGeometry gameGeometry;

    private final Map<TowerLevel, Sprite> sprites;

    @Getter
    private final Map<TowerLevel, ProjectileStats> projectileStats;

    public Projectile createProjectile(Vector2i shootingPosition, Vector2i mapTarget, TowerLevel level) {
        final Sprite sprite = sprites.get(level);
        final ProjectileStats stats = projectileStats.get(level);
        final Vector2i realTargetPosition = gameGeometry.toRealPosition(mapTarget);
        final Range damageRange = gameGeometry.toPathRange(List.of(mapTarget));
        return new Projectile(eventEmitter, damageRange, shootingPosition, realTargetPosition, stats, sprite);
    }
}
