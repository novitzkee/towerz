package presentation.loaders.sprites;

import engine.geometry.Vector2i;
import engine.graphics.sprites.Sprite;
import engine.graphics.sprites.SpriteSheet;
import game.engine.loaders.TowerSpriteFactory;
import game.tower.TowerLevel;

import java.util.List;
import java.util.Map;

import static presentation.config.Dimensions.TILE_DIMENSIONS_PX;
import static presentation.config.Dimensions.TILE_SIZE_PX;
import static presentation.config.Resources.*;

public class TowerResourceSpriteFactory implements TowerSpriteFactory {

    private static final Vector2i TOWER_SPRITE_SIZE = new Vector2i(1, 2).mul(TILE_DIMENSIONS_PX);

    public static final Vector2i PROJECTILE_SPRITE_SIZE = TILE_SIZE_PX;

    private static final SpriteSheet TOWER_1_BASES_SPRITE_SHEET = new SpriteSheet(TOWER1_BASES_SPRITE_SHEET, TOWER_SPRITE_SIZE);

    private static final SpriteSheet TOWER_1_PROJECTILE_SPRITE_SHEET = new SpriteSheet(TOWER1_PROJECTILES_SPRITE_SHEET, PROJECTILE_SPRITE_SIZE);

    private static final SpriteSheet TOWER_2_BASES_SPRITE_SHEET = new SpriteSheet(TOWER2_BASES_SPRITE_SHEET, TOWER_SPRITE_SIZE);

    private static final SpriteSheet TOWER_2_PROJECTILE_SPRITE_SHEET = new SpriteSheet(TOWER2_PROJECTILES_SPRITE_SHEET, PROJECTILE_SPRITE_SIZE);

    private static final SpriteSheet TOWER_3_BASES_SPRITE_SHEET = new SpriteSheet(TOWER3_BASES_SPRITE_SHEET, TOWER_SPRITE_SIZE);

    private static final SpriteSheet TOWER_3_PROJECTILE_SPRITE_SHEET = new SpriteSheet(TOWER3_PROJECTILES_SPRITE_SHEET, PROJECTILE_SPRITE_SIZE);

    private static final SpriteSheet TOWER_4_BASES_SPRITE_SHEET = new SpriteSheet(TOWER4_BASES_SPRITE_SHEET, TOWER_SPRITE_SIZE);

    private static final SpriteSheet TOWER_4_PROJECTILE_SPRITE_SHEET = new SpriteSheet(TOWER4_PROJECTILES_SPRITE_SHEET, PROJECTILE_SPRITE_SIZE);


    @Override
    public Map<TowerLevel, Sprite> getArrowTowerSprites() {
        return loadSpriteSheetSequence(TOWER_1_BASES_SPRITE_SHEET);
    }

    @Override
    public Map<TowerLevel, Sprite> getArrowProjectileSprites() {
        return loadSpriteSheetSequence(TOWER_1_PROJECTILE_SPRITE_SHEET);
    }

    @Override
    public Map<TowerLevel, Sprite> getElectricTowerSprites() {
        return loadSpriteSheetSequence(TOWER_2_BASES_SPRITE_SHEET);
    }

    @Override
    public Map<TowerLevel, Sprite> getElectricProjectileSprites() {
        return loadSpriteSheetSequence(TOWER_2_PROJECTILE_SPRITE_SHEET);
    }

    @Override
    public Map<TowerLevel, Sprite> getCandyTowerSprites() {
        return loadSpriteSheetSequence(TOWER_3_BASES_SPRITE_SHEET);
    }

    @Override
    public Map<TowerLevel, Sprite> getCandyProjectiles() {
        return loadSpriteSheetSequence(TOWER_3_PROJECTILE_SPRITE_SHEET);
    }

    @Override
    public Map<TowerLevel, Sprite> getBastionTowerSprites() {
        return loadSpriteSheetSequence(TOWER_4_BASES_SPRITE_SHEET);
    }

    @Override
    public Map<TowerLevel, Sprite> getBastionProjectileSprites() {
        return loadSpriteSheetSequence(TOWER_4_PROJECTILE_SPRITE_SHEET);
    }

    private Map<TowerLevel, Sprite> loadSpriteSheetSequence(SpriteSheet spriteSheet) {
        final List<Sprite> sprites = spriteSheet.getSpriteRow(0, 3);
        return Map.of(
                TowerLevel.WEAK, sprites.get(0),
                TowerLevel.MEDIUM, sprites.get(1),
                TowerLevel.STRONG, sprites.get(2));
    }
}
