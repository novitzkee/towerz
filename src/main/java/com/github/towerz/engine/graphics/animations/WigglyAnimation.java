package com.github.towerz.engine.graphics.animations;

import com.github.towerz.engine.geometry.Direction;
import com.github.towerz.engine.geometry.Vector2i;
import com.github.towerz.engine.graphics.DrawingPositioning;
import com.github.towerz.engine.graphics.DrawingTarget;
import com.github.towerz.engine.graphics.sprites.Sprite;
import com.github.towerz.engine.time.delegators.ScalingDelegator;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

public class WigglyAnimation implements Animation {

    private static final float MAX_WIGGLE = 0.1f;

    private float wiggleDelta = 0.5e-1f;

    private float currentWiggleAmount = 0;

    @Getter
    private final Map<Direction, List<Sprite>> sprites;

    @Getter
    @Setter
    private Direction direction = Direction.RIGHT;

    private final ScalingDelegator animationScalingDelegator;

    public WigglyAnimation(Map<Direction, List<Sprite>> sprites) {
        this.sprites = sprites;
        this.animationScalingDelegator = new ScalingDelegator(this::update);
    }

    public WigglyAnimation(Map<Direction, List<Sprite>> sprites, float timeScale) {
        this.sprites = sprites;
        this.animationScalingDelegator = new ScalingDelegator(timeScale, this::update);
    }

    @Override
    public void draw(Vector2i position, DrawingTarget drawingTarget, DrawingPositioning drawingPositioning) {
        final Vector2i wiggleVector = position.interpolate(position.add(direction.getVector()), currentWiggleAmount);
        sprites.get(direction).get(0).draw(wiggleVector, drawingTarget, drawingPositioning);
    }

    private void update() {
        currentWiggleAmount += wiggleDelta;

        if (shouldBounce()) {
            wiggleDelta *= -1;
        }
    }

    private boolean shouldBounce() {
        return currentWiggleAmount < 0 || currentWiggleAmount > MAX_WIGGLE;
    }
}
