package game.tower;

import engine.geometry.Circle;
import engine.geometry.Vector2i;
import engine.graphics.DrawingPositioning;
import engine.graphics.DrawingTarget;
import engine.graphics.Paintable;
import engine.graphics.sprites.Sprite;
import engine.time.TimeAware;
import engine.traits.Upgradeable;
import game.tower.projectiles.Projectile;
import game.tower.utils.TowerMechanics;
import game.tower.utils.TowerStats;
import lombok.Getter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tower implements Paintable, TimeAware {

    private static final Vector2i TOWER_DRAWING_OFFSET = new Vector2i(0, -1);

    private final Map<TowerLevel, TowerStats> towerStatsMap;

    private final Map<TowerLevel, Sprite> towerSpriteMap;

    private final Map<TowerLevel, ImageIcon> towerIcons;

    private final TowerMechanics towerMechanics;

    @Getter
    private final Vector2i position;

    @Getter
    private final Upgradeable upgradeable = new TowerUpgradeable();

    private final List<Projectile> projectiles = new ArrayList<>();

    @Getter
    private TowerLevel level;

    @Getter
    private TowerStats currentTowerStats;

    public Tower(Map<TowerLevel, TowerStats> towerStats,
                 Map<TowerLevel, Sprite> towerSprites,
                 Map<TowerLevel, ImageIcon> towerIcons,
                 TowerMechanics towerMechanics,
                 Vector2i position) {
        this.towerStatsMap = towerStats;
        this.towerSpriteMap = towerSprites;
        this.towerIcons = towerIcons;
        this.towerMechanics = towerMechanics;
        this.position = position;

        this.level = TowerLevel.WEAK;
        this.currentTowerStats = towerStats.get(level);

        towerMechanics.calibrate(this);
    }

    public Circle getCurrentRange() {
        return new Circle(position, currentTowerStats.rangeRadius());
    }

    public void upgrade() {
        level = TowerLevel.getNextLevel(level);
        currentTowerStats = towerStatsMap.get(level);
        towerMechanics.setReloadTime(currentTowerStats.reloadTime());
        towerMechanics.calibrate(this);
    }

    @Override
    public synchronized void draw(DrawingTarget drawingTarget) {
        towerSpriteMap.get(level).draw(position.add(TOWER_DRAWING_OFFSET), drawingTarget, DrawingPositioning.RELATIVE);
        projectiles.forEach(projectile -> projectile.draw(drawingTarget));
    }

    @Override
    public synchronized void tick() {
        towerMechanics.tick();
        towerMechanics.fireProjectile().ifPresent(projectiles::add);
        clearProjectiles();
        projectiles.forEach(Projectile::tick);
    }

    private void clearProjectiles() {
        projectiles.stream()
                .filter(Projectile::isGarbage)
                .forEach(Projectile::cleanUp);

        projectiles.removeIf(Projectile::isGarbage);
    }

    private final class TowerUpgradeable implements Upgradeable {

        @Override
        public Vector2i getPosition() {
            return position;
        }

        @Override
        public boolean canUpgrade() {
            return level != TowerLevel.STRONG;
        }

        @Override
        public ImageIcon getCurrentIcon() {
            return towerIcons.get(level);
        }

        @Override
        public ImageIcon getUpgradedIcon() {
            return towerIcons.get(TowerLevel.getNextLevel(level));
        }

        @Override
        public int getUpgradePrice() {
            return towerStatsMap.get(level).upgradePrice();
        }

        @Override
        public int getSellPrice() {
            return towerStatsMap.get(level).sellPrice();
        }
    }
}
