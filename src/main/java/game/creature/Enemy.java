package game.creature;

import engine.events.EventEmitter;
import engine.graphics.Animation;
import game.world.GameGeometry;

public class Enemy extends AbstractCreature {

    public Enemy(EventEmitter eventEmitter,
                 GameGeometry gameGeometry,
                 Animation animation,
                 Health health,
                 int speed,
                 int damage) {
        super(eventEmitter, gameGeometry, animation, CreatureType.ATTACKER, health, speed, damage);
    }
}
