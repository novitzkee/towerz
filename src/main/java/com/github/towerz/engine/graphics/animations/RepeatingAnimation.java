package com.github.towerz.engine.graphics.animations;

import com.github.towerz.engine.geometry.Direction;
import com.github.towerz.engine.graphics.sprites.Sprite;

import java.util.List;
import java.util.Map;

public class RepeatingAnimation extends IndexingAnimation {

    private int currentSprite = 0;

    public RepeatingAnimation(Map<Direction, List<Sprite>> sprites) {
        super(sprites);
    }

    public RepeatingAnimation(Map<Direction, List<Sprite>> sprites, float timeScale) {
        super(sprites, timeScale);
    }

    @Override
    protected int getCurrentSpriteIndex() {
        return currentSprite;
    }

    @Override
    protected void update() {
        currentSprite++;
        currentSprite %= sprites.size();
    }
}
