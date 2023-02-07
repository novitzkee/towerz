package game.tower.projectiles;

import engine.graphics.sprites.Sprite;
import game.tower.TowerLevel;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class ProjectileSpriteMap {

    private final Map<TowerLevel, Sprite> projectileSprites;

    public Sprite getSpriteForLevel(TowerLevel level) {
        return projectileSprites.get(level);
    }
}
