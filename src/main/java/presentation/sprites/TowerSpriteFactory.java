package presentation.sprites;

import engine.geometry.Vector2i;
import engine.graphics.BasicSprite;
import engine.graphics.Sprite;
import engine.graphics.SpriteSheet;
import game.tower.TowerLevel;

import java.util.List;
import java.util.Map;

import static presentation.config.Dimensions.TILE_DIMENSIONS_PX;
import static presentation.config.Dimensions.TILE_SIZE_PX;
import static presentation.config.Sprites.*;

public class TowerSpriteFactory {

    private static final Vector2i towerSpriteSize = new Vector2i(1, 2).map(size -> size * TILE_DIMENSIONS_PX);

    public static final Vector2i projectileSpriteSize = TILE_SIZE_PX;

    private static final SpriteSheet tower1BasesSpriteSheet = new SpriteSheet(TOWER1_BASES_SPRITE_SHEET, towerSpriteSize);

    private static final SpriteSheet tower1ProjectileSpriteSheet = new SpriteSheet(TOWER1_PROJECTILES_SPRITE_SHEET, projectileSpriteSize);

    private static final SpriteSheet tower2BasesSpriteSheet = new SpriteSheet(TOWER2_BASES_SPRITE_SHEET, towerSpriteSize);

    private static final SpriteSheet tower2ProjectileSpriteSheet = new SpriteSheet(TOWER2_PROJECTILES_SPRITE_SHEET, projectileSpriteSize);

    private static final SpriteSheet tower3BasesSpriteSheet = new SpriteSheet(TOWER3_BASES_SPRITE_SHEET, towerSpriteSize);

    private static final SpriteSheet tower3ProjectileSpriteSheet = new SpriteSheet(TOWER3_PROJECTILES_SPRITE_SHEET, projectileSpriteSize);

    private static final SpriteSheet tower4BasesSpriteSheet = new SpriteSheet(TOWER4_BASES_SPRITE_SHEET, towerSpriteSize);

    private static final SpriteSheet tower4ProjectileSpriteSheet = new SpriteSheet(TOWER4_PROJECTILES_SPRITE_SHEET, projectileSpriteSize);

    public Map<TowerLevel, Sprite> getTower1BaseSprites() {
        return loadSpriteSheetSequence(tower1BasesSpriteSheet);
    }

    public Map<TowerLevel, Sprite> getTower1ProjectileSprites() {
        return loadSpriteSheetSequence(tower1ProjectileSpriteSheet);
    }

    public Map<TowerLevel, Sprite> getTower2BaseSprites() {
        return loadSpriteSheetSequence(tower2BasesSpriteSheet);
    }

    public Map<TowerLevel, Sprite> getTower2ProjectileSprites() {
        return loadSpriteSheetSequence(tower2ProjectileSpriteSheet);
    }

    public Map<TowerLevel, Sprite> getTower3BaseSprites() {
        return loadSpriteSheetSequence(tower3BasesSpriteSheet);
    }

    public Map<TowerLevel, Sprite> getTower3ProjectileSprites() {
        return loadSpriteSheetSequence(tower3ProjectileSpriteSheet);
    }

    public Map<TowerLevel, Sprite> getTower4BaseSprites() {
        return loadSpriteSheetSequence(tower4BasesSpriteSheet);
    }

    public Map<TowerLevel, Sprite> getTower4ProjectileSprites() {
        return loadSpriteSheetSequence(tower4ProjectileSpriteSheet);
    }

    private Map<TowerLevel, Sprite> loadSpriteSheetSequence(SpriteSheet spriteSheet) {
        final List<BasicSprite> sprites = spriteSheet.getSpriteRow(0);
        return Map.of(
                TowerLevel.WEAK, sprites.get(0),
                TowerLevel.MEDIUM, sprites.get(1),
                TowerLevel.STRONG, sprites.get(2));
    }
}
