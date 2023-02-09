package com.github.towerz.presentation.loaders.sprites;

import com.github.towerz.engine.geometry.Direction;
import com.github.towerz.engine.graphics.sprites.LayeredSprite;
import com.github.towerz.engine.graphics.sprites.Sprite;
import com.github.towerz.engine.graphics.sprites.SpriteSheet;
import com.github.towerz.presentation.config.Dimensions;
import com.github.towerz.presentation.config.Resources;

import java.util.Map;

public class MapResourceSpriteFactory {
    private static final SpriteSheet WORLD_SPRITE_SHEET = new SpriteSheet(Resources.WORLD_SPRITES_PATH, Dimensions.TILE_SIZE_PX);

    public Sprite getGrassSprite() {
        return WORLD_SPRITE_SHEET.getSprite(0, 1);
    }

    public Sprite getRockSprite() {
        final Sprite grassSpriteLayer = getGrassSprite();
        final Sprite rockSpriteLayer = WORLD_SPRITE_SHEET.getSprite(7, 3);
        return new LayeredSprite(grassSpriteLayer, rockSpriteLayer);
    }

    public Map<Direction, Sprite> getStraightPathSprites() {
        final Sprite horizontalSprite = WORLD_SPRITE_SHEET.getSprite(4, 0);
        final Sprite verticalSprite = WORLD_SPRITE_SHEET.getSprite(3, 1);

        return Map.of(
                Direction.UP, verticalSprite,
                Direction.DOWN, verticalSprite,
                Direction.RIGHT, horizontalSprite,
                Direction.LEFT, horizontalSprite);
    }

    public Map<Direction, Sprite> getPathTurnSprites() {
        return Map.of(
                Direction.UP, WORLD_SPRITE_SHEET.getSprite(5, 2),
                Direction.DOWN, WORLD_SPRITE_SHEET.getSprite(5, 0),
                Direction.RIGHT, WORLD_SPRITE_SHEET.getSprite(3, 0),
                Direction.LEFT, WORLD_SPRITE_SHEET.getSprite(3, 2));
    }
}
