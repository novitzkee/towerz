package game.tower.utils;

import engine.graphics.sprites.Sprite;
import game.tower.TowerLevel;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.util.Map;

@RequiredArgsConstructor
public class TowerSpriteMap {

    private final Map<TowerLevel, Sprite> baseSprites;

    private final Map<TowerLevel, ImageIcon> icons;

    public Sprite getBaseSpriteForLevel(TowerLevel level) {
        return baseSprites.get(level);
    }

    public ImageIcon getIconForLevel(TowerLevel level) {
        return icons.get(level);
    }
}
