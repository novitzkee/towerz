package game.creature;

import engine.events.EventEmitter;
import engine.graphics.animations.Animation;
import game.engine.loaders.MonsterAnimationFactory;
import game.world.GameGeometry;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MonsterFactory {

    private final EventEmitter eventEmitter;

    private final GameGeometry gameGeometry;

    private final MonsterAnimationFactory monsterAnimationFactory;

    public Enemy createMantis() {
        final Animation animation = monsterAnimationFactory.getMantisAnimation();
        return new Enemy(eventEmitter, gameGeometry, animation, new Health(150), 7, 3);
    }

    public Enemy createBee() {
        final Animation animation = monsterAnimationFactory.getBeeAnimation();
        return new Enemy(eventEmitter, gameGeometry, animation, new Health(250), 5, 5);
    }

    public Enemy createButterfly() {
        final Animation animation = monsterAnimationFactory.getButterflyAnimation();
        return new Enemy(eventEmitter, gameGeometry, animation, new Health(700), 6, 6);
    }

    public Enemy createBeetle() {
        final Animation animation = monsterAnimationFactory.getBeetleAnimation();
        return new Enemy(eventEmitter, gameGeometry, animation, new Health(1200), 3, 5);
    }
}
