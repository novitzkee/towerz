package com.github.towerz.game.creature;

import com.github.towerz.engine.geometry.Vector2i;
import com.github.towerz.engine.graphics.Paintable;
import com.github.towerz.engine.time.TimeAware;
import com.github.towerz.engine.traits.Removable;

public interface Creature extends TimeAware, Paintable, Removable {

    Health getHealth();

    int getPathPosition();

    Vector2i getMapPositionInTicks(int ticks);

    CreatureState getState();

    void setState(CreatureState creatureState);

    void takeDamage(int amount);

    int getDamage();
}
