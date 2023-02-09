package com.github.towerz.presentation.loaders.sprites;

import com.github.towerz.engine.geometry.Vector2i;
import com.github.towerz.engine.graphics.sprites.Sprite;
import com.github.towerz.engine.graphics.sprites.SpriteSheet;
import com.github.towerz.game.engine.loaders.ProjectileSpriteFactory;
import com.github.towerz.game.engine.loaders.TowerSpriteFactory;
import com.github.towerz.game.tower.TowerLevel;

import java.util.List;
import java.util.Map;

import static com.github.towerz.presentation.config.Dimensions.TILE_DIMENSIONS_PX;
import static com.github.towerz.presentation.config.Dimensions.TILE_SIZE_PX;
import static com.github.towerz.presentation.config.Resources.*;

public class TowerResourceSpriteFactory implements TowerSpriteFactory, ProjectileSpriteFactory {

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
    public Map<TowerLevel, Sprite> getCandyTowerProjectileSprites() {
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
