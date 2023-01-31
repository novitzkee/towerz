package presentation.loaders.sprites;

import engine.geometry.Direction;
import engine.graphics.sprites.LayeredSprite;
import engine.graphics.sprites.Sprite;
import engine.graphics.sprites.SpriteSheet;

import java.util.Map;

import static presentation.config.Dimensions.TILE_SIZE_PX;
import static presentation.config.Resources.WORLD_SPRITES_PATH;

public class MapResourceSpriteFactory {
    private static final SpriteSheet WORLD_SPRITE_SHEET = new SpriteSheet(WORLD_SPRITES_PATH, TILE_SIZE_PX);

    public Sprite getGrassSprite() {
        return WORLD_SPRITE_SHEET.getSprite(0, 1);
    }

    public Sprite getRockSprite() {
        final Sprite grassSpriteLayer = getGrassSprite();
        final Sprite rockSpriteLayer = WORLD_SPRITE_SHEET.getSprite(4, 3);
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
