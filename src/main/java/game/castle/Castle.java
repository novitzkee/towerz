package game.castle;

import engine.geometry.Vector2i;
import engine.graphics.Paintable;
import engine.graphics.Sprite;
import game.creature.Health;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
public class Castle implements Paintable {

    private final Vector2i position;

    private final Health health;

    private final Map<State, Sprite> sprites;


    @Override
    public void draw(Graphics2D graphics) {
        sprites.get(getState()).draw(position, graphics);
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
