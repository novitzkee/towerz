package engine.graphics.animations;

import engine.geometry.Direction;
import engine.geometry.Vector2i;
import engine.graphics.DrawingPositioning;
import engine.graphics.DrawingTarget;
import engine.graphics.sprites.Sprite;
import engine.time.delegators.ScalingDelegator;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

public abstract class AbstractAnimation implements Animation {

    @Getter
    protected final Map<Direction, List<Sprite>> sprites;

    @Getter
    @Setter
    protected Direction direction = Direction.RIGHT;

    private final ScalingDelegator animationScalingDelegator;

    public AbstractAnimation(Map<Direction, List<Sprite>> sprites) {
        this.sprites = sprites;
        this.animationScalingDelegator = new ScalingDelegator(this::update);
    }

    public AbstractAnimation(Map<Direction, List<Sprite>> sprites, float timeScale) {
        this.sprites = sprites;
        this.animationScalingDelegator = new ScalingDelegator(timeScale, this::update);
    }

    @Override
    public void draw(Vector2i position, DrawingTarget drawingTarget, DrawingPositioning drawingPositioning) {
        getCurrentSprite().draw(position, drawingTarget, drawingPositioning);
        animationScalingDelegator.tick();
    }

    abstract Sprite getCurrentSprite();

    abstract void update();
}
