package game.creature;

import engine.events.EventEmitter;
import engine.graphics.Animation;
import engine.time.TimeAware;
import engine.time.delegators.ScalingDelegator;
import game.engine.loaders.MonsterAnimationFactory;
import game.fight.Creatures;
import game.world.GameGeometry;
import lombok.RequiredArgsConstructor;
import presentation.loaders.sprites.CreatureResourceAnimationFactory;

@RequiredArgsConstructor
public class EnemySpawner implements TimeAware {

    private final MonsterAnimationFactory creatureAnimationFactory = new CreatureResourceAnimationFactory();

    private final EventEmitter eventEmitter;

    private final GameGeometry gameGeometry;

    private final Creatures creatures;

    private final ScalingDelegator spawnDelegator = new ScalingDelegator(0.02f, this::spawnEnemy);

    @Override
    public void tick() {
        spawnDelegator.tick();
    }

    private void spawnEnemy() {
        final Enemy enemy = createEnemy();
        creatures.add(enemy, CreatureType.ATTACKER);
    }

    private Enemy createEnemy() {
        final Animation animation = creatureAnimationFactory.getBeetleAnimation();
        return new Enemy(eventEmitter, gameGeometry, animation, new Health(100), 10, 10);
    }
}
