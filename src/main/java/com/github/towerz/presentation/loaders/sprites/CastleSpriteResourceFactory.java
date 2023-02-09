package com.github.towerz.presentation.loaders.sprites;

import com.github.towerz.engine.geometry.Vector2i;
import com.github.towerz.engine.graphics.sprites.Sprite;
import com.github.towerz.engine.graphics.sprites.SpriteSheet;
import com.github.towerz.game.castle.State;
import com.github.towerz.presentation.config.Dimensions;
import com.github.towerz.presentation.config.Resources;

import java.util.Map;

public class CastleSpriteResourceFactory {

    private static final Vector2i CASTLE_SPRITE_SIZE = new Vector2i(3, 3).map(size -> size * Dimensions.TILE_DIMENSIONS_PX);

    private static final SpriteSheet CASTLE_SPRITE_SHEET = new SpriteSheet(Resources.CASTLE_SPRITE_SHEET, CASTLE_SPRITE_SIZE);

    public Map<State, Sprite> getCastleSprites() {
        return Map.of(
                State.GOOD, CASTLE_SPRITE_SHEET.getSprite(0, 0),
                State.MEDIUM, CASTLE_SPRITE_SHEET.getSprite(1, 0),
                State.BAD, CASTLE_SPRITE_SHEET.getSprite(2, 0));
    }
}
