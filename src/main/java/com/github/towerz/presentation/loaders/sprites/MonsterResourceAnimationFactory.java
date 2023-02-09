package com.github.towerz.presentation.loaders.sprites;

import com.github.towerz.engine.geometry.Direction;
import com.github.towerz.engine.graphics.animations.Animation;
import com.github.towerz.engine.graphics.animations.RepeatingAnimation;
import com.github.towerz.engine.graphics.sprites.Sprite;
import com.github.towerz.engine.graphics.sprites.SpriteSheet;
import com.github.towerz.game.engine.loaders.MonsterAnimationFactory;

import java.util.List;
import java.util.Map;

import static com.github.towerz.presentation.config.Dimensions.TILE_SIZE_PX;
import static com.github.towerz.presentation.config.Resources.*;

public class MonsterResourceAnimationFactory implements MonsterAnimationFactory {

    private static final SpriteSheet ENEMY_1_SPRITE_SHEET = new SpriteSheet(ENEMY1_SPRITE_SHEET, TILE_SIZE_PX);

    private static final SpriteSheet ENEMY_2_SPRITE_SHEET = new SpriteSheet(ENEMY2_SPRITE_SHEET, TILE_SIZE_PX);

    private static final SpriteSheet ENEMY_3_SPRITE_SHEET = new SpriteSheet(ENEMY3_SPRITE_SHEET, TILE_SIZE_PX);

    private static final SpriteSheet ENEMY_4_SPRITE_SHEET = new SpriteSheet(ENEMY4_SPRITE_SHEET, TILE_SIZE_PX);

    private final Map<Direction, List<Sprite>> mantisSpriteMap = createEnemySpriteMapWithLeftFlip(ENEMY_1_SPRITE_SHEET, 12);

    private final Map<Direction, List<Sprite>> beeSpriteMap = createEnemySpriteMapWithLeftFlip(ENEMY_2_SPRITE_SHEET, 12);

    private final Map<Direction, List<Sprite>> butterflySpriteMap = createEnemySpriteMapWithLeftFlip(ENEMY_3_SPRITE_SHEET, 6);

    private final Map<Direction, List<Sprite>> beetleSpriteMap = createBeetleAnimationSpriteMap();

    @Override
    public Animation getMantisAnimation() {
        return new RepeatingAnimation(mantisSpriteMap, 0.5f);
    }

    @Override
    public Animation getBeeAnimation() {
        return new RepeatingAnimation(beeSpriteMap, 0.7f);
    }

    @Override
    public Animation getButterflyAnimation() {
        return new RepeatingAnimation(butterflySpriteMap, 0.3f);
    }

    @Override
    public Animation getBeetleAnimation() {
        return new RepeatingAnimation(beetleSpriteMap, 0.3f);
    }

    private Map<Direction, List<Sprite>> createEnemySpriteMapWithLeftFlip(SpriteSheet sheet, int length) {
        final List<Sprite> flippedSprites = flipSprites(sheet.getSpriteRow(2, length));
        return Map.of(
                Direction.UP, sheet.getSpriteRow(1, length),
                Direction.DOWN, sheet.getSpriteRow(0, length),
                Direction.RIGHT, sheet.getSpriteRow(2, length),
                Direction.LEFT, flippedSprites);
    }

    private Map<Direction, List<Sprite>> createBeetleAnimationSpriteMap() {
        final List<Sprite> flippedSprites = flipSprites(ENEMY_4_SPRITE_SHEET.getSpriteRow(2, 8));
        return Map.of(
                Direction.UP, ENEMY_4_SPRITE_SHEET.getSpriteRow(1, 8),
                Direction.DOWN, ENEMY_4_SPRITE_SHEET.getSpriteRow(0, 8),
                Direction.RIGHT, flippedSprites,
                Direction.LEFT, ENEMY_4_SPRITE_SHEET.getSpriteRow(2, 8));
    }

    private List<Sprite> flipSprites(List<Sprite> sprites) {
        return sprites
                .stream()
                .map(Sprite::flipX)
                .toList();
    }
}
