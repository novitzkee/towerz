package game.engine.loaders;

import engine.graphics.Animation;

public interface SoldierAnimationFactory {

    Animation getLightSoldierAnimation();

    Animation getMediumSoldierAnimation();

    Animation getHeavySoldierAnimation();

    Animation getSkeletonSoldierAnimation();
}
