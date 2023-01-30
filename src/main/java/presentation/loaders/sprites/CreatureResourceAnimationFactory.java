package presentation.loaders.sprites;

import engine.geometry.Direction;
import engine.graphics.Animation;
import engine.graphics.BasicAnimation;
import engine.graphics.Sprite;
import engine.graphics.SpriteSheet;
import game.engine.loaders.MonsterAnimationFactory;
import game.engine.loaders.SoldierAnimationFactory;

import java.util.List;
import java.util.Map;

import static presentation.config.Dimensions.TILE_SIZE_PX;
import static presentation.config.Resources.*;

public class CreatureResourceAnimationFactory implements MonsterAnimationFactory, SoldierAnimationFactory {

    private static final SpriteSheet ENEMY_1_SPRITE_SHEET = new SpriteSheet(ENEMY1_SPRITE_SHEET, TILE_SIZE_PX);

    private static final SpriteSheet ENEMY_2_SPRITE_SHEET = new SpriteSheet(ENEMY2_SPRITE_SHEET, TILE_SIZE_PX);

    private static final SpriteSheet ENEMY_3_SPRITE_SHEET = new SpriteSheet(ENEMY3_SPRITE_SHEET, TILE_SIZE_PX);

    private static final SpriteSheet ENEMY_4_SPRITE_SHEET = new SpriteSheet(ENEMY4_SPRITE_SHEET, TILE_SIZE_PX);

    private static final SpriteSheet SOLDIER_1_SPRITE_SHEET = new SpriteSheet(SOLDIER1_SPRITE_SHEET, TILE_SIZE_PX);

    private static final SpriteSheet SOLDIER_2_SPRITE_SHEET = new SpriteSheet(SOLDIER2_SPRITE_SHEET, TILE_SIZE_PX);

    private static final SpriteSheet SOLDIER_3_SPRITE_SHEET = new SpriteSheet(SOLDIER3_SPRITE_SHEET, TILE_SIZE_PX);

    private static final SpriteSheet SOLDIER_4_SPRITE_SHEET = new SpriteSheet(SOLDIER4_SPRITE_SHEET, TILE_SIZE_PX);

    @Override
    public Animation getMantisAnimation() {
        return createEnemyAnimationFromSpriteSheet(ENEMY_1_SPRITE_SHEET, 12, 0.5f);
    }

    @Override
    public Animation getBeeAnimation() {
        return createEnemyAnimationFromSpriteSheet(ENEMY_2_SPRITE_SHEET, 12, 0.7f);
    }

    @Override
    public Animation getButterflyAnimation() {
        return createEnemyAnimationFromSpriteSheet(ENEMY_3_SPRITE_SHEET, 6, 0.3f);
    }

    @Override
    public Animation getBeetleAnimation() {
        return createEnemyAnimationFromSpriteSheet(ENEMY_4_SPRITE_SHEET, 8, 0.3f);
    }

    private Animation createEnemyAnimationFromSpriteSheet(SpriteSheet spriteSheet, int length, float timeScale) {
        final Map<Direction, List<Sprite>> spritesByDirection = Map.of(
                Direction.UP, spriteSheet.getSpriteRow(1, length),
                Direction.DOWN, spriteSheet.getSpriteRow(0, length),
                Direction.RIGHT, spriteSheet.getSpriteRow(2, length),
                Direction.LEFT, spriteSheet.getSpriteRow(2, length));

        return new BasicAnimation(spritesByDirection, timeScale);
    }

    @Override
    public Animation getLightSoldierAnimation() {
        return createSoldierAnimationFromSpriteSheet(SOLDIER_1_SPRITE_SHEET);
    }

    @Override
    public Animation getMediumSoldierAnimation() {
        return createSoldierAnimationFromSpriteSheet(SOLDIER_2_SPRITE_SHEET);
    }

    @Override
    public Animation getHeavySoldierAnimation() {
        return createSoldierAnimationFromSpriteSheet(SOLDIER_3_SPRITE_SHEET);
    }

    @Override
    public Animation getSkeletonSoldierAnimation() {
        return createSoldierAnimationFromSpriteSheet(SOLDIER_4_SPRITE_SHEET);
    }

    private Animation createSoldierAnimationFromSpriteSheet(SpriteSheet spriteSheet) {
        final Map<Direction, List<Sprite>> spritesByDirection = Map.of(
                Direction.UP, spriteSheet.getSpriteRow(0, 9),
                Direction.DOWN, spriteSheet.getSpriteRow(2, 9),
                Direction.RIGHT, spriteSheet.getSpriteRow(3, 9),
                Direction.LEFT, spriteSheet.getSpriteRow(1, 9));

        return new BasicAnimation(spritesByDirection, 0.5f);
    }
}
