package com.github.towerz.game.castle;

import com.github.towerz.engine.geometry.Vector2i;
import com.github.towerz.engine.graphics.DrawingPositioning;
import com.github.towerz.engine.graphics.DrawingTarget;
import com.github.towerz.engine.graphics.Paintable;
import com.github.towerz.engine.graphics.sprites.Sprite;
import com.github.towerz.game.creature.Health;
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

    public int getCurrentHealth() {
        return health.getCurrentAmount();
    }

    public void damage(int amount) {
        health.decrease(amount);
    }
}
