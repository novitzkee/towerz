package game.tower;

import engine.events.EventEmitter;
import engine.geometry.Vector2i;
import engine.graphics.DrawingPositioning;
import engine.graphics.DrawingTarget;
import engine.graphics.Paintable;
import engine.graphics.sprites.Sprite;
import engine.time.TimeAware;
import engine.traits.Upgradeable;
import game.fight.Creatures;
import game.world.GameGeometry;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
public class Tower implements Paintable, TimeAware {

    private static final Vector2i TOWER_DRAWING_OFFSET = new Vector2i(0, -1);

    private final EventEmitter eventEmitter;

    private final GameGeometry gameGeometry;

    private final Creatures creatures;

    private final Map<TowerLevel, ImageIcon> upgradeIcons;

    private final Map<TowerLevel, Sprite> baseSprites;

    private final Map<TowerLevel, Sprite> projectileSprites;

    @Getter
    private final Upgradeable upgradeable = new TowerUpgradeable();

    @Getter
    private final Vector2i position;

    private final List<Projectile> projectiles = new ArrayList<>();

    private TowerLevel level = TowerLevel.WEAK;

    private Set<Vector2i> tileRange;

    private int reloadCounter;

    public void upgrade() {
        level = TowerLevel.getNextLevel(level);
        updateTileRange();
    }

    private void updateTileRange() {

    }

    public void updateTarget() {

    }

    @Override
    public void draw(DrawingTarget drawingTarget) {
        baseSprites.get(level).draw(position.add(TOWER_DRAWING_OFFSET), drawingTarget, DrawingPositioning.RELATIVE);
        projectiles.forEach(projectile -> projectile.draw(drawingTarget));
    }

    @Override
    public void tick() {
        updateTarget();
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
            return upgradeIcons.get(level);
        }

        @Override
        public ImageIcon getUpgradedIcon() {
            return upgradeIcons.get(TowerLevel.getNextLevel(level));
        }

        @Override
        public int getUpgradePrice() {
            return 100;
        }

        @Override
        public int getSellPrice() {
            return 100;
        }
    }
}
