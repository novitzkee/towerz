package com.github.towerz.game.creature;

import com.github.towerz.engine.events.EventEmitter;
import com.github.towerz.engine.graphics.animations.Animation;
import com.github.towerz.game.engine.loaders.SoldierAnimationFactory;
import com.github.towerz.game.world.GameGeometry;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class SoldierFactory {

    private final EventEmitter eventEmitter;

    private final GameGeometry gameGeometry;

    private final SoldierAnimationFactory soldierAnimationFactory;

    public Soldier createLightSoldier() {
        final Map<CreatureState, Animation> animations = soldierAnimationFactory.getLightSoldierAnimations();
        return new Soldier(eventEmitter, gameGeometry, animations, new Health(200), 6, 5);
    }

    public Soldier createMediumSoldier() {
        final Map<CreatureState, Animation> animations = soldierAnimationFactory.getMediumSoldierAnimations();
        return new Soldier(eventEmitter, gameGeometry, animations, new Health(500), 5, 8);
    }

    public Soldier createHeavySoldier() {
        final Map<CreatureState, Animation> animations = soldierAnimationFactory.getHeavySoldierAnimations();
        return new Soldier(eventEmitter, gameGeometry, animations, new Health(1000), 3, 10);
    }

    public Soldier createSkeletonSoldier() {
        final Map<CreatureState, Animation> animations = soldierAnimationFactory.getSkeletonSoldierAnimations();
        return new Soldier(eventEmitter, gameGeometry, animations, new Health(2000), 2, 0);
    }
}
