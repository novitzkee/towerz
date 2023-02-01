package game.creature;

import engine.events.EventEmitter;
import engine.graphics.animations.Animation;
import game.engine.loaders.SoldierAnimationFactory;
import game.world.GameGeometry;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class SoldierFactory {

    private final EventEmitter eventEmitter;

    private final GameGeometry gameGeometry;

    private final SoldierAnimationFactory soldierAnimationFactory;

    public Soldier createLightSolder() {
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

    public Soldier createSkeletonSolder() {
        final Map<CreatureState, Animation> animations = soldierAnimationFactory.getSkeletonSoldierAnimations();
        return new Soldier(eventEmitter, gameGeometry, animations, new Health(2000), 2, 0);
    }
}
