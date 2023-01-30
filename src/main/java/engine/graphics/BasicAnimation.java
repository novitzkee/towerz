package engine.graphics;

import engine.geometry.Direction;
import engine.geometry.Vector2i;
import engine.time.delegators.ScalingDelegator;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

public class BasicAnimation implements Animation {

    private int currentSprite = 0;

    @Getter
    private final Map<Direction, List<Sprite>> sprites;

    private final ScalingDelegator animationScalingDelegator;

    @Getter
    @Setter
    private Direction direction = Direction.RIGHT;

    public BasicAnimation(Map<Direction, List<Sprite>> sprites) {
        this.sprites = sprites;
        this.animationScalingDelegator = new ScalingDelegator(this::updateSprite);
    }

    public BasicAnimation(Map<Direction, List<Sprite>> sprites, float timeScale) {
        this.sprites = sprites;
        this.animationScalingDelegator = new ScalingDelegator(timeScale, this::updateSprite);
    }

    @Override
    public void draw(Vector2i position, DrawingTarget drawingTarget, DrawingPositioning drawingPositioning) {
        sprites.get(direction).get(currentSprite).draw(position, drawingTarget, drawingPositioning);
        animationScalingDelegator.tick();
    }

    public void updateSprite() {
        currentSprite++;
        currentSprite %= sprites.size();
    }
}
