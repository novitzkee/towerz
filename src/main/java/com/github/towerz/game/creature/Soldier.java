package com.github.towerz.game.creature;

import com.github.towerz.engine.events.EventEmitter;
import com.github.towerz.engine.graphics.animations.Animation;
import com.github.towerz.game.world.GameGeometry;

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
