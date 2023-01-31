package game.engine.loaders;

import engine.graphics.animations.Animation;
import game.creature.CreatureState;

import java.util.Map;

public interface SoldierAnimationFactory {

    Map<CreatureState, Animation> getLightSoldierAnimations();

    Map<CreatureState, Animation> getMediumSoldierAnimations();

    Map<CreatureState, Animation> getHeavySoldierAnimations();

    Map<CreatureState, Animation> getSkeletonSoldierAnimations();
}
