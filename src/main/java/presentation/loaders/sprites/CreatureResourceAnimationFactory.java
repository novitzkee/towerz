package presentation.loaders.sprites;

import engine.geometry.Direction;
import engine.graphics.animations.Animation;
import engine.graphics.animations.BouncingAnimation;
import engine.graphics.animations.RepeatingAnimation;
import engine.graphics.animations.StaticAnimation;
import engine.graphics.sprites.Sprite;
import engine.graphics.sprites.SpriteSheet;
import game.creature.CreatureState;
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

    private static final SpriteSheet SOLDIER_1_ATTACK_SPRITE_SHEET = new SpriteSheet(SOLDIER1_ATTACK_SPRITE_SHEET, TILE_SIZE_PX);

    private static final SpriteSheet SOLDIER_2_SPRITE_SHEET = new SpriteSheet(SOLDIER2_SPRITE_SHEET, TILE_SIZE_PX);

    private static final SpriteSheet SOLDIER_2_ATTACK_SPRITE_SHEET = new SpriteSheet(SOLDIER2_ATTACK_SPRITE_SHEET, TILE_SIZE_PX);

    private static final SpriteSheet SOLDIER_3_SPRITE_SHEET = new SpriteSheet(SOLDIER3_SPRITE_SHEET, TILE_SIZE_PX);

    private static final SpriteSheet SOLDIER_3_ATTACK_SPRITE_SHEET = new SpriteSheet(SOLDIER3_ATTACK_SPRITE_SHEET, TILE_SIZE_PX);

    private static final SpriteSheet SOLDIER_4_SPRITE_SHEET = new SpriteSheet(SOLDIER4_SPRITE_SHEET, TILE_SIZE_PX);

    @Override
    public Animation getMantisAnimation() {
        return createEnemyAnimationWithLeftFlip(ENEMY_1_SPRITE_SHEET, 12, 0.5f);
    }

    @Override
    public Animation getBeeAnimation() {
        return createEnemyAnimationWithLeftFlip(ENEMY_2_SPRITE_SHEET, 12, 0.7f);
    }

    @Override
    public Animation getButterflyAnimation() {
        return createEnemyAnimationWithLeftFlip(ENEMY_3_SPRITE_SHEET, 6, 0.3f);
    }

    @Override
    public Animation getBeetleAnimation() {
        return createEnemyAnimationWithRightFlip(ENEMY_4_SPRITE_SHEET, 8, 0.3f);
    }

    private Animation createEnemyAnimationWithLeftFlip(SpriteSheet sheet, int length, float timeScale) {
        final List<Sprite> flippedSprites = flipSprites(sheet.getSpriteRow(2, length));
        final Map<Direction, List<Sprite>> spritesByDirection = Map.of(
                Direction.UP, sheet.getSpriteRow(1, length),
                Direction.DOWN, sheet.getSpriteRow(0, length),
                Direction.RIGHT, sheet.getSpriteRow(2, length),
                Direction.LEFT, flippedSprites);

        return new RepeatingAnimation(spritesByDirection, timeScale);
    }

    private Animation createEnemyAnimationWithRightFlip(SpriteSheet sheet, int length, float timeScale) {
        final List<Sprite> flippedSprites = flipSprites(sheet.getSpriteRow(2, length));
        final Map<Direction, List<Sprite>> spritesByDirection = Map.of(
                Direction.UP, sheet.getSpriteRow(1, length),
                Direction.DOWN, sheet.getSpriteRow(0, length),
                Direction.RIGHT, flippedSprites,
                Direction.LEFT, sheet.getSpriteRow(2, length));

        return new RepeatingAnimation(spritesByDirection, timeScale);
    }

    private List<Sprite> flipSprites(List<Sprite> sprites) {
        return sprites
                .stream()
                .map(Sprite::flipX)
                .toList();
    }

    @Override
    public Map<CreatureState, Animation> getLightSoldierAnimations() {
        return Map.of(
                CreatureState.WALKING, createSoldierWalkAnimation(SOLDIER_1_SPRITE_SHEET),
                CreatureState.FIGHTING, createSoldierFightAnimation(SOLDIER_1_ATTACK_SPRITE_SHEET));
    }

    @Override
    public Map<CreatureState, Animation> getMediumSoldierAnimations() {
        return Map.of(
                CreatureState.WALKING, createSoldierWalkAnimation(SOLDIER_2_SPRITE_SHEET),
                CreatureState.FIGHTING, createSoldierFightAnimation(SOLDIER_2_ATTACK_SPRITE_SHEET));
    }

    @Override
    public Map<CreatureState, Animation> getHeavySoldierAnimations() {
        return Map.of(
                CreatureState.WALKING, createSoldierWalkAnimation(SOLDIER_3_SPRITE_SHEET),
                CreatureState.FIGHTING, createSoldierFightAnimation(SOLDIER_3_ATTACK_SPRITE_SHEET));
    }

    @Override
    public Map<CreatureState, Animation> getSkeletonSoldierAnimations() {
        return Map.of(
                CreatureState.WALKING, createSoldierWalkAnimation(SOLDIER_4_SPRITE_SHEET),
                CreatureState.FIGHTING, createSoldierStandingAnimation(SOLDIER_4_SPRITE_SHEET));
    }

    private Animation createSoldierWalkAnimation(SpriteSheet sheet) {
        final Map<Direction, List<Sprite>> spritesByDirection = Map.of(
                Direction.UP, sheet.getSpriteRow(0, 9),
                Direction.DOWN, sheet.getSpriteRow(2, 9),
                Direction.RIGHT, sheet.getSpriteRow(3, 9),
                Direction.LEFT, sheet.getSpriteRow(1, 9));

        return new RepeatingAnimation(spritesByDirection, 0.4f);
    }

    private Animation createSoldierFightAnimation(SpriteSheet sheet) {
        final Map<Direction, List<Sprite>> spritesByDirection = Map.of(
                Direction.UP, sheet.getSpriteRow(0, 8),
                Direction.DOWN, sheet.getSpriteRow(2, 8),
                Direction.RIGHT, sheet.getSpriteRow(3, 8),
                Direction.LEFT, sheet.getSpriteRow(1, 8));

        return new BouncingAnimation(spritesByDirection, 0.4f);
    }

    private Animation createSoldierStandingAnimation(SpriteSheet sheet) {
        final Map<Direction, List<Sprite>> spritesByDirection = Map.of(
                Direction.UP, sheet.getSpriteRow(0, 1),
                Direction.DOWN, sheet.getSpriteRow(2, 1),
                Direction.RIGHT, sheet.getSpriteRow(3, 1),
                Direction.LEFT, sheet.getSpriteRow(1, 1));

        return new StaticAnimation(spritesByDirection);
    }
}
