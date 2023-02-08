package game.tower.utils;

import engine.geometry.Range;
import engine.geometry.Vector2i;
import engine.time.TimeAware;
import game.creature.Creature;
import game.fight.Creatures;
import game.tower.Tower;
import game.tower.TowerLevel;
import game.tower.projectiles.Projectile;
import game.tower.projectiles.ProjectileFactory;
import game.world.GameGeometry;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class TowerMechanics implements TimeAware {

    private static final Map<TowerLevel, Vector2i> IDLE_PROJECTILE_OFFSETS = Map.of(
            TowerLevel.WEAK, new Vector2i(0, -40),
            TowerLevel.MEDIUM, new Vector2i(0, -60),
            TowerLevel.STRONG, new Vector2i(0, -80));

    private final Creatures creatures;

    private final GameGeometry gameGeometry;

    private final ProjectileFactory projectileFactory;

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
        reloadCounter++;
        reloadCounter = Math.min(reloadTime, reloadCounter);
    }

    private boolean isReloaded() {
        return reloadCounter >= reloadTime;
    }

    public synchronized Optional<Projectile> fireProjectile() {
        if (!isReloaded()) return Optional.empty();

        final Optional<Creature> firstCreature = creatures.getFirstMonsterInRange(currentRange);

        return firstCreature.flatMap(distanceMap::getBestRendezvouzPosition)
                .or(() -> firstCreature.map(this::creatureMapPosition))
                .map(this::createAndSendProjectile);
    }

    private Vector2i creatureMapPosition(Creature creature) {
        return gameGeometry.toMapPosition(creature.getPathPosition());
    }

    private Projectile createAndSendProjectile(Vector2i mapTarget) {
        reloadCounter = 0;
        return projectileFactory.createProjectile(projectileShootingPosition, mapTarget, currentTowerLevel);
    }

    public synchronized void calibrate(Tower tower) {
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
        final double tickDistance = realTo.sub(realFrom).getLength() / projectileSpeed - 5;
        return (int) Math.round(tickDistance);
    }
}
