package engine.graphics.animations;

import engine.geometry.Direction;
import engine.graphics.sprites.Sprite;

import java.util.List;
import java.util.Map;

public class StaticAnimation extends IndexingAnimation {

    public StaticAnimation(Map<Direction, List<Sprite>> sprites) {
        super(sprites);
    }

    public StaticAnimation(Map<Direction, List<Sprite>> sprites, float timeScale) {
        super(sprites, timeScale);
    }

    @Override
    protected int getCurrentSpriteIndex() {
        return 0;
    }

    @Override
    protected void update() { }
}
