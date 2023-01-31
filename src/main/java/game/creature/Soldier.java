package game.creature;

import engine.events.EventEmitter;
import engine.graphics.animations.Animation;
import game.world.GameGeometry;

import java.util.Map;

public class Soldier extends AbstractCreature {

    public Soldier(EventEmitter eventEmitter,
                   GameGeometry gameGeometry,
                   Map<CreatureState, Animation> animation,
                   Health health,
                   int speed,
                   int damage) {
        super(eventEmitter, gameGeometry, animation, CreatureType.DEFENDER, health, speed, damage);
    }
}
