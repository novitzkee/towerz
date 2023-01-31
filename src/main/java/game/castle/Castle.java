package game.castle;

import engine.geometry.Vector2i;
import engine.graphics.DrawingPositioning;
import engine.graphics.DrawingTarget;
import engine.graphics.Paintable;
import engine.graphics.sprites.Sprite;
import game.creature.Health;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class Castle implements Paintable {

    private final Vector2i position;

    private final Health health;

    private final Map<State, Sprite> sprites;

    @Override
    public void draw(DrawingTarget drawingTarget) {
        sprites.get(getState()).draw(position, drawingTarget, DrawingPositioning.RELATIVE);
    }

    private State getState() {
        final float healthiness = health.getHealthiness();

        if(healthiness < 0.33f) {
            return State.BAD;
        } else if (0.33f <= healthiness && healthiness < 0.66f) {
            return State.MEDIUM;
        } else {
            return State.GOOD;
        }
    }
}
