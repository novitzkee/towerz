package game.tower;

import engine.events.EventEmitter;
import engine.geometry.Range;
import engine.geometry.Vector2i;
import engine.graphics.DrawingPositioning;
import engine.graphics.DrawingTarget;
import engine.graphics.Paintable;
import engine.graphics.sprites.Sprite;
import engine.time.TimeAware;
import game.creature.Creature;
import game.world.GameGeometry;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class Tower implements Paintable, TimeAware {

    private static final Vector2i TOWER_DRAWING_OFFSET = new Vector2i(0, -1);

    private final EventEmitter eventEmitter;

    private final GameGeometry gameGeometry;

    private final Map<TowerLevel, Sprite> baseSprites;

    private final Map<TowerLevel, Sprite> projectileSprites;

    @Getter
    private final Vector2i position;

    private final List<Projectile> projectiles = new ArrayList<>();

    private TowerLevel level = TowerLevel.WEAK;

    public void upgrade() {
        level = TowerLevel.getNextLevel(level);
    }

    public void updateTarget(Creature target) {

    }

    public Range getPathRange() {
        return null;
    }

    @Override
    public void draw(DrawingTarget drawingTarget) {
        baseSprites.get(level).draw(position.add(TOWER_DRAWING_OFFSET), drawingTarget, DrawingPositioning.RELATIVE);
        projectiles.forEach(projectile -> projectile.draw(drawingTarget));
    }

    @Override
    public void tick() {
        projectiles.forEach(Projectile::tick);
    }
}
