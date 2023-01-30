package presentation.loaders.sprites;

import engine.geometry.Vector2i;
import engine.graphics.Sprite;
import engine.graphics.SpriteSheet;
import game.castle.State;
import presentation.config.Resources;

import java.util.Map;

import static presentation.config.Dimensions.TILE_DIMENSIONS_PX;

public class CastleSpriteResourceFactory {

    private static final Vector2i CASTLE_SPRITE_SIZE = new Vector2i(3, 3).map(size -> size * TILE_DIMENSIONS_PX);

    private static final SpriteSheet CASTLE_SPRITE_SHEET = new SpriteSheet(Resources.CASTLE_SPRITE_SHEET, CASTLE_SPRITE_SIZE);

    public Map<State, Sprite> getCastleSprites() {
        return Map.of(
                State.GOOD, CASTLE_SPRITE_SHEET.getSprite(0, 0),
                State.MEDIUM, CASTLE_SPRITE_SHEET.getSprite(1, 0),
                State.BAD, CASTLE_SPRITE_SHEET.getSprite(2, 0));
    }
}
