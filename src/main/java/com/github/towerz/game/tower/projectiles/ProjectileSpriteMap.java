package com.github.towerz.game.tower.projectiles;

import com.github.towerz.engine.graphics.sprites.Sprite;
import com.github.towerz.game.tower.TowerLevel;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class ProjectileSpriteMap {

    private final Map<TowerLevel, Sprite> projectileSprites;

    public Sprite getSpriteForLevel(TowerLevel level) {
        return projectileSprites.get(level);
    }
}
