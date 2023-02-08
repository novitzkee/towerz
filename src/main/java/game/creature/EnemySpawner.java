package game.creature;

import engine.time.TimeAware;
import engine.time.delegators.ScalingDelegator;
import game.fight.Creatures;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EnemySpawner implements TimeAware {

    private final MonsterFactory monsterFactory;

    private final Creatures creatures;

    private final ScalingDelegator spawnDelegator = new ScalingDelegator(0.02f, this::spawnEnemies);

    @Override
    public void tick() {
        spawnDelegator.tick();
    }

    private void spawnEnemies() {
        creatures.add(monsterFactory.createMantis(), CreatureType.ATTACKER);
        creatures.add(monsterFactory.createBee(), CreatureType.ATTACKER);
        creatures.add(monsterFactory.createButterfly(), CreatureType.ATTACKER);
        creatures.add(monsterFactory.createBeetle(), CreatureType.ATTACKER);
    }
}
