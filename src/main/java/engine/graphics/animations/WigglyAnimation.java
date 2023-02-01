package engine.graphics.animations;

import engine.geometry.Direction;
import engine.geometry.Vector2i;
import engine.graphics.DrawingPositioning;
import engine.graphics.DrawingTarget;
import engine.graphics.sprites.Sprite;
import engine.time.delegators.ScalingDelegator;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class WigglyAnimation implements Animation {

    private static final float MAX_WIGGLE = 0.1f;

    private float wiggleDelta = 0.5e-1f;

    private float currentWiggleAmount = 0;

    @Getter
    private final Map<Direction, Sprite> sprites;

    @Getter
    @Setter
    private Direction direction = Direction.RIGHT;

    private final ScalingDelegator animationScalingDelegator;

    public WigglyAnimation(Map<Direction, Sprite> sprites) {
        this.sprites = sprites;
        this.animationScalingDelegator = new ScalingDelegator(this::update);
    }

    public WigglyAnimation(Map<Direction, Sprite> sprites, float timeScale) {
        this.sprites = sprites;
        this.animationScalingDelegator = new ScalingDelegator(timeScale, this::update);
    }

    @Override
    public void draw(Vector2i position, DrawingTarget drawingTarget, DrawingPositioning drawingPositioning) {
        final Vector2i wiggleVector = position.interpolate(position.add(direction.getVector()), currentWiggleAmount);
        sprites.get(direction).draw(wiggleVector, drawingTarget, drawingPositioning);
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
