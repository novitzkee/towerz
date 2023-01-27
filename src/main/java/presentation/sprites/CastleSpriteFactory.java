package presentation.sprites;

import engine.geometry.Vector2i;
import engine.graphics.Sprite;
import engine.graphics.SpriteSheet;
import game.castle.State;

import java.util.Map;

import static presentation.config.Dimensions.TILE_DIMENSIONS_PX;
import static presentation.config.Sprites.*;

public class CastleSpriteFactory {

    private static final Vector2i castleSpriteSize = new Vector2i(3, 3).map(size -> size * TILE_DIMENSIONS_PX);

    private static final SpriteSheet castleSpriteSheet = new SpriteSheet(CASTLE_SPRITE_SHEET, castleSpriteSize);

    public Map<State, Sprite> getCastleSprites() {
        return Map.of(
                State.GOOD, castleSpriteSheet.getSprite(0, 0),
                State.MEDIUM, castleSpriteSheet.getSprite(1, 0),
                State.BAD, castleSpriteSheet.getSprite(2, 0));
    }
}
