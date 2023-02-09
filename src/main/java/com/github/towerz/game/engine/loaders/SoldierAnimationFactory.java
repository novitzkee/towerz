package com.github.towerz.game.engine.loaders;

import com.github.towerz.engine.graphics.animations.Animation;
import com.github.towerz.game.creature.CreatureState;

import java.util.Map;

public interface SoldierAnimationFactory {

    Map<CreatureState, Animation> getLightSoldierAnimations();

    Map<CreatureState, Animation> getMediumSoldierAnimations();

    Map<CreatureState, Animation> getHeavySoldierAnimations();

    Map<CreatureState, Animation> getSkeletonSoldierAnimations();
}
