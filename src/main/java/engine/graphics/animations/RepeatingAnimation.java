package engine.graphics.animations;

import engine.geometry.Direction;
import engine.graphics.sprites.Sprite;

import java.util.List;
import java.util.Map;

public class RepeatingAnimation extends AbstractAnimation {

    private int currentSprite = 0;

    public RepeatingAnimation(Map<Direction, List<Sprite>> sprites) {
        super(sprites);
    }

    public RepeatingAnimation(Map<Direction, List<Sprite>> sprites, float timeScale) {
        super(sprites, timeScale);
    }

    @Override
    Sprite getCurrentSprite() {
        return sprites.get(direction).get(currentSprite);
    }

    @Override
    void update() {
        currentSprite++;
        currentSprite %= sprites.size();
    }
}
