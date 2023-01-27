package presentation.sprites;

import engine.geometry.Direction;
import engine.graphics.LayeredSprite;
import engine.graphics.Sprite;
import engine.graphics.SpriteSheet;

import java.util.Map;

import static presentation.config.Dimensions.TILE_SIZE_PX;
import static presentation.config.Sprites.WORLD_SPRITES_PATH;

public class MapSpriteFactory {
    private static final SpriteSheet worldSpriteSheet = new SpriteSheet(WORLD_SPRITES_PATH, TILE_SIZE_PX);

    public Sprite getGrassSprite() {
        return worldSpriteSheet.getSprite(0, 1);
    }

    public Sprite getRockSprite() {
        final Sprite grassSpriteLayer = getGrassSprite();
        final Sprite rockSpriteLayer = worldSpriteSheet.getSprite(4, 3);
        return new LayeredSprite(grassSpriteLayer, rockSpriteLayer);
    }

    public Sprite getPathSprite() {
        return worldSpriteSheet.getSprite(4, 0);
    }

    public Map<Direction, Sprite> getPathTurnSprites() {
        return Map.of(
                Direction.UP, worldSpriteSheet.getSprite(5, 2),
                Direction.DOWN, worldSpriteSheet.getSprite(5, 0),
                Direction.RIGHT, worldSpriteSheet.getSprite(3, 0),
                Direction.LEFT, worldSpriteSheet.getSprite(3, 2));
    }
}
