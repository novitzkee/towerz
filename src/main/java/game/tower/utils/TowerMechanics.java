package game.tower.utils;

import engine.geometry.Range;
import engine.geometry.Vector2i;
import engine.time.TimeAware;
import game.fight.Creatures;
import game.tower.Tower;
import game.tower.TowerLevel;
import game.tower.projectiles.Projectile;
import game.tower.projectiles.ProjectileFactory;
import game.tower.projectiles.ProjectileStats;
import game.world.GameGeometry;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class TowerMechanics implements TimeAware {

    private static final Map<TowerLevel, Vector2i> IDLE_PROJECTILE_OFFSETS = Map.of(
            TowerLevel.WEAK, new Vector2i(0, -80),
            TowerLevel.MEDIUM, new Vector2i(0, -80),
            TowerLevel.STRONG, new Vector2i(0, -100));

    private final Creatures creatures;

    private final GameGeometry gameGeometry;

    private final ProjectileFactory projectileFactory;

    private final Map<TowerLevel, ProjectileStats> projectileStats;

    private TowerLevel currentTowerLevel = TowerLevel.WEAK;

    private Vector2i projectileShootingPosition = new Vector2i(0, 0);

    private DistanceMap distanceMap = DistanceMap.empty();

    private Range currentRange = Range.empty();

    @Setter
    private int reloadTime;

    private int reloadCounter;

    @Override
    public void tick() {
        updateReloadCounter();
    }

    private void updateReloadCounter() {
        reloadCounter = Math.min(reloadTime, ++reloadCounter);
    }

    private boolean isReloaded() {
        return reloadCounter >= reloadTime;
    }

    public Optional<Projectile> getFiredProjectile() {
        if (!isReloaded()) return Optional.empty();

        return creatures.getFirstMonsterInRange(currentRange)
                .flatMap(distanceMap::getBestRendezvouzPosition)
                .map(this::createAndSendProjectile);
    }

    private Projectile createAndSendProjectile(Vector2i mapTarget) {
        reloadCounter = 0;
        return projectileFactory.createProjectile(projectileShootingPosition, mapTarget, currentTowerLevel);
    }

    public void calibrate(Tower tower) {
        currentTowerLevel = tower.getLevel();
        reloadTime = tower.getCurrentTowerStats().reloadTime();

        final Vector2i idleProjectileOffset = IDLE_PROJECTILE_OFFSETS.get(tower.getLevel());
        final Vector2i realProjectilePosition = gameGeometry.toRealPosition(tower.getPosition()).add(idleProjectileOffset);

        projectileShootingPosition = realProjectilePosition;

        final int projectileSpeed = projectileFactory.getProjectileStats().get(currentTowerLevel).speed();
        final List<Vector2i> positionsInRange = gameGeometry.getMapPositionsInRange(tower.getCurrentRange());
        final List<Integer> realTickDistanceForTiles = positionsInRange.stream()
                .map(gameGeometry::toRealPosition)
                .map(realTilePosition -> calculateRealTickDistance(realProjectilePosition, realTilePosition, projectileSpeed))
                .toList();

        currentRange = gameGeometry.toPathRange(positionsInRange);
        distanceMap = new DistanceMap(realTickDistanceForTiles, positionsInRange);
    }

    private int calculateRealTickDistance(Vector2i realFrom, Vector2i realTo, int projectileSpeed) {
        final double tickDistance = realTo.sub(realFrom).getLength() / projectileSpeed;
        return (int) Math.round(tickDistance);
    }
}
