package game.creature;

import engine.events.EventEmitter;
import engine.graphics.Animation;
import game.world.GameGeometry;

public class Soldier extends AbstractCreature {

    public Soldier(EventEmitter eventEmitter,
                   GameGeometry gameGeometry,
                   Animation animation,
                   Health health,
                   int speed,
                   int damage) {
        super(eventEmitter, gameGeometry, animation, CreatureType.DEFENDER, health, speed, damage);
    }
}
