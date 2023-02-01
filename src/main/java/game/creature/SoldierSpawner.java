package game.creature;

import engine.time.TimeAware;
import engine.time.delegators.ScalingDelegator;
import game.fight.Creatures;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SoldierSpawner implements TimeAware {

    private final SoldierFactory soldierFactory;

    private final Creatures creatures;

    private final ScalingDelegator spawnDelegator = new ScalingDelegator(0.01f, this::spawnSoldier);

    @Override
    public void tick() {
        spawnDelegator.tick();
    }

    public void spawnSoldier() {
        creatures.add(soldierFactory.createLightSolder(), CreatureType.DEFENDER);
        creatures.add(soldierFactory.createMediumSoldier(), CreatureType.DEFENDER);
        creatures.add(soldierFactory.createHeavySoldier(), CreatureType.DEFENDER);
        creatures.add(soldierFactory.createSkeletonSolder(), CreatureType.DEFENDER);
    }
}
