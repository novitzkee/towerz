package engine.graphics.animations;

import engine.geometry.Direction;
import engine.graphics.sprites.Sprite;

import java.util.List;
import java.util.Map;

public class BouncingAnimation extends AbstractAnimation {

    private int delta = 1;

    private int currentSprite = 0;

    public BouncingAnimation(Map<Direction, List<Sprite>> sprites) {
        super(sprites);
    }

    public BouncingAnimation(Map<Direction, List<Sprite>> sprites, float timeScale) {
        super(sprites, timeScale);
    }

    @Override
    Sprite getCurrentSprite() {
        return sprites.get(direction).get(currentSprite);
    }

    @Override
    void update() {
        currentSprite += delta;

        if (shouldBounce()) {
            delta *= -1;
        }
    }

    private boolean shouldBounce() {
        return currentSprite == 0 || currentSprite == sprites.get(direction).size() - 1;
    }
}
