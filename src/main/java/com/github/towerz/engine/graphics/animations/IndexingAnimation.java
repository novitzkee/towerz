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

public abstract class IndexingAnimation implements Animation {

    @Getter
    protected final Map<Direction, List<Sprite>> sprites;

    @Getter
    @Setter
    protected Direction direction = Direction.RIGHT;

    private final ScalingDelegator animationScalingDelegator;

    public IndexingAnimation(Map<Direction, List<Sprite>> sprites) {
        this.sprites = sprites;
        this.animationScalingDelegator = new ScalingDelegator(this::update);
    }

    public IndexingAnimation(Map<Direction, List<Sprite>> sprites, float timeScale) {
        this.sprites = sprites;
        this.animationScalingDelegator = new ScalingDelegator(timeScale, this::update);
    }

    @Override
    public void draw(Vector2i position, DrawingTarget drawingTarget, DrawingPositioning drawingPositioning) {
        sprites.get(direction).get(getCurrentSpriteIndex()).draw(position, drawingTarget, drawingPositioning);
        animationScalingDelegator.tick();
    }

    protected abstract int getCurrentSpriteIndex();

    protected abstract void update();
}
