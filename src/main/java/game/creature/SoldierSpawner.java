package game.creature;

import engine.events.EventEmitter;
import engine.graphics.animations.Animation;
import engine.time.TimeAware;
import engine.time.delegators.ScalingDelegator;
import game.engine.loaders.SoldierAnimationFactory;
import game.fight.Creatures;
import game.world.GameGeometry;
import lombok.RequiredArgsConstructor;
import presentation.loaders.sprites.CreatureResourceAnimationFactory;

import java.util.Map;

@RequiredArgsConstructor
public class SoldierSpawner implements TimeAware {

    private final SoldierAnimationFactory creatureAnimationFactory = new CreatureResourceAnimationFactory();

    private final EventEmitter eventEmitter;

    private final GameGeometry gameGeometry;

    private final Creatures creatures;

    private final ScalingDelegator spawnDelegator = new ScalingDelegator(0.02f, this::spawnSoldier);

    @Override
    public void tick() {
        spawnDelegator.tick();
    }

    public void spawnSoldier() {
        final Creature soldier = createSoldier();
        creatures.add(soldier, CreatureType.DEFENDER);
    }

    private Soldier createSoldier() {
        final Map<CreatureState, Animation> animation = creatureAnimationFactory.getHeavySoldierAnimations();
        return new Soldier(eventEmitter, gameGeometry, animation, new Health(1000), 10, 1);
    }
}
