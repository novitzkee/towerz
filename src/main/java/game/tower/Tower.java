package game.tower;

import engine.geometry.Vector2i;
import engine.graphics.DrawingPositioning;
import engine.graphics.DrawingTarget;
import engine.graphics.Paintable;
import engine.time.TimeAware;
import engine.traits.Upgradeable;
import game.fight.Creatures;
import game.tower.utils.TowerEngine;
import game.tower.utils.TowerSprites;
import game.tower.utils.TowerStats;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class Tower implements Paintable, TimeAware {

    private static final Vector2i TOWER_DRAWING_OFFSET = new Vector2i(0, -1);

    private final Creatures creatures;

    private final TowerStats towerStats;

    private final TowerEngine towerEngine;

    private final TowerSprites towerSprites;

    @Getter
    private final Vector2i position;

    @Getter
    private final Upgradeable upgradeable = new TowerUpgradeable();

    private final List<Projectile> projectiles = new ArrayList<>();

    private TowerLevel level = TowerLevel.WEAK;


    public void upgrade() {
        level = TowerLevel.getNextLevel(level);
    }

    @Override
    public void draw(DrawingTarget drawingTarget) {
        towerSprites.getBaseSpriteForLevel(level).draw(position.add(TOWER_DRAWING_OFFSET), drawingTarget, DrawingPositioning.RELATIVE);
        projectiles.forEach(projectile -> projectile.draw(drawingTarget));
    }

    @Override
    public void tick() {
        projectiles.forEach(Projectile::tick);
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
            return towerSprites.getIconForLevel(level);
        }

        @Override
        public ImageIcon getUpgradedIcon() {
            return towerSprites.getIconForLevel(TowerLevel.getNextLevel(level));
        }

        @Override
        public int getUpgradePrice() {
            return towerStats.getStatsForLevel(level).upgradePrice();
        }

        @Override
        public int getSellPrice() {
            return towerStats.getStatsForLevel(level).sellPrice();
        }
    }
}
