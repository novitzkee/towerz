package game.tower;

import engine.events.EventEmitter;
import engine.geometry.Vector2i;
import engine.graphics.Paintable;
import engine.graphics.BasicSprite;
import engine.time.TimeAware;
import engine.traits.Removable;
import game.events.ProjectileHitEvent;
import lombok.RequiredArgsConstructor;

import java.awt.*;

@RequiredArgsConstructor
public class Projectile implements TimeAware, Removable, Paintable {

    private final EventEmitter eventEmitter;

    private final Vector2i realSource;

    private final Vector2i realTarget;

    private Vector2i realPosition;

    private final Vector2i targetTile;

    /*private final Vector2f movementVector = Angle.between(realSource, realTarget)
            .asVector();*/

    // Known before. Should be composed or abstract.
    private final int hitRange;

    private final int speed;

    private final BasicSprite sprite;

    @Override
    public void tick() {
        move();
    }

    private void move() {
        /*final Vector2i displacement = movementVector.mul(speed).round();
        realPosition = realPosition.add(displacement);*/
    }

    @Override
    public void draw(Graphics2D graphics) {
        sprite.draw(realPosition, graphics);
    }

    @Override
    public boolean isGarbage() {
        return realTarget.sub(realPosition).getLength() < hitRange;
    }

    @Override
    public void cleanUp() {
        final ProjectileHitEvent projectileHitEvent = new ProjectileHitEvent(this,targetTile);
        eventEmitter.emit(projectileHitEvent);
    }
}
