package presentation.loaders.sprites;

import engine.geometry.Direction;
import engine.graphics.animations.Animation;
import engine.graphics.animations.BouncingAnimation;
import engine.graphics.animations.RepeatingAnimation;
import engine.graphics.animations.StaticAnimation;
import engine.graphics.sprites.Sprite;
import engine.graphics.sprites.SpriteSheet;
import game.creature.CreatureState;
import game.engine.loaders.SoldierAnimationFactory;

import java.util.List;
import java.util.Map;

import static presentation.config.Dimensions.TILE_SIZE_PX;
import static presentation.config.Resources.*;

public class SoldierResourceAnimationFactory implements SoldierAnimationFactory {

    private static final SpriteSheet SOLDIER_1_SPRITE_SHEET = new SpriteSheet(SOLDIER1_SPRITE_SHEET, TILE_SIZE_PX);

    private static final SpriteSheet SOLDIER_1_ATTACK_SPRITE_SHEET = new SpriteSheet(SOLDIER1_ATTACK_SPRITE_SHEET, TILE_SIZE_PX);

    private static final SpriteSheet SOLDIER_2_SPRITE_SHEET = new SpriteSheet(SOLDIER2_SPRITE_SHEET, TILE_SIZE_PX);

    private static final SpriteSheet SOLDIER_2_ATTACK_SPRITE_SHEET = new SpriteSheet(SOLDIER2_ATTACK_SPRITE_SHEET, TILE_SIZE_PX);

    private static final SpriteSheet SOLDIER_3_SPRITE_SHEET = new SpriteSheet(SOLDIER3_SPRITE_SHEET, TILE_SIZE_PX);

    private static final SpriteSheet SOLDIER_3_ATTACK_SPRITE_SHEET = new SpriteSheet(SOLDIER3_ATTACK_SPRITE_SHEET, TILE_SIZE_PX);

    private static final SpriteSheet SOLDIER_4_SPRITE_SHEET = new SpriteSheet(SOLDIER4_SPRITE_SHEET, TILE_SIZE_PX);

    private final Map<Direction, List<Sprite>> lightSoldierWalkSpriteMap = createSoldierWalkSpriteMap(SOLDIER_1_SPRITE_SHEET);

    private final Map<Direction, List<Sprite>> lightSoldierFightSpriteMap = createSoldierFightSpriteMap(SOLDIER_1_ATTACK_SPRITE_SHEET);

    private final Map<Direction, List<Sprite>> mediumSoldierWalkSpriteMap = createSoldierWalkSpriteMap(SOLDIER_2_SPRITE_SHEET);

    private final Map<Direction, List<Sprite>> mediumSoldierFightSpriteMap = createSoldierFightSpriteMap(SOLDIER_2_ATTACK_SPRITE_SHEET);

    private final Map<Direction, List<Sprite>> heavySoldierWalkSpriteMap = createSoldierWalkSpriteMap(SOLDIER_3_SPRITE_SHEET);

    private final Map<Direction, List<Sprite>> heavySoldierFightSpriteMap = createSoldierFightSpriteMap(SOLDIER_3_ATTACK_SPRITE_SHEET);

    private final Map<Direction, List<Sprite>> skeletonSoldierWalkSpriteMap = createSoldierWalkSpriteMap(SOLDIER_4_SPRITE_SHEET);

    private final Map<Direction, List<Sprite>> skeletonSoldierStandSpriteMap = createSkeletonStandSpriteMap();

    @Override
    public Map<CreatureState, Animation> getLightSoldierAnimations() {
        return Map.of(
                CreatureState.WALKING, new RepeatingAnimation(lightSoldierWalkSpriteMap, 0.4f),
                CreatureState.FIGHTING, new BouncingAnimation(lightSoldierFightSpriteMap, 0.4f));
    }

    @Override
    public Map<CreatureState, Animation> getMediumSoldierAnimations() {
        return Map.of(
                CreatureState.WALKING, new RepeatingAnimation(mediumSoldierWalkSpriteMap, 0.4f),
                CreatureState.FIGHTING, new BouncingAnimation(mediumSoldierFightSpriteMap, 0.4f));
    }

    @Override
    public Map<CreatureState, Animation> getHeavySoldierAnimations() {
        return Map.of(
                CreatureState.WALKING, new RepeatingAnimation(heavySoldierWalkSpriteMap, 0.4f),
                CreatureState.FIGHTING, new BouncingAnimation(heavySoldierFightSpriteMap, 0.4f));
    }

    @Override
    public Map<CreatureState, Animation> getSkeletonSoldierAnimations() {
        return Map.of(
                CreatureState.WALKING, new RepeatingAnimation(skeletonSoldierWalkSpriteMap, 0.4f),
                CreatureState.FIGHTING, new StaticAnimation(skeletonSoldierStandSpriteMap));
    }

    private Map<Direction, List<Sprite>> createSoldierWalkSpriteMap(SpriteSheet sheet) {
        return Map.of(
                Direction.UP, sheet.getSpriteRow(0, 9),
                Direction.DOWN, sheet.getSpriteRow(2, 9),
                Direction.RIGHT, sheet.getSpriteRow(3, 9),
                Direction.LEFT, sheet.getSpriteRow(1, 9));
    }

    private Map<Direction, List<Sprite>> createSoldierFightSpriteMap(SpriteSheet sheet) {
        return Map.of(
                Direction.UP, sheet.getSpriteRow(0, 8),
                Direction.DOWN, sheet.getSpriteRow(2, 8),
                Direction.RIGHT, sheet.getSpriteRow(3, 8),
                Direction.LEFT, sheet.getSpriteRow(1, 8));
    }

    private Map<Direction, List<Sprite>> createSkeletonStandSpriteMap() {
        return Map.of(
                Direction.UP, SOLDIER_4_SPRITE_SHEET.getSpriteRow(0, 1),
                Direction.DOWN, SOLDIER_4_SPRITE_SHEET.getSpriteRow(2, 1),
                Direction.RIGHT, SOLDIER_4_SPRITE_SHEET.getSpriteRow(3, 1),
                Direction.LEFT, SOLDIER_4_SPRITE_SHEET.getSpriteRow(1, 1));
    }
}
