package game.creature;

import engine.geometry.Vector2i;
import engine.graphics.Paintable;
import engine.time.TimeAware;
import engine.traits.Removable;

public interface Creature extends TimeAware, Paintable, Removable {

    Health getHealth();

    int getPathPosition();

    Vector2i getMapPositionInTicks(int ticks);

    CreatureState getState();

    void setState(CreatureState creatureState);

    void takeDamage(int amount);

    int getDamage();
}
