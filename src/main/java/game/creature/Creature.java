package game.creature;

import engine.graphics.Paintable;
import engine.time.TimeAware;
import engine.traits.Removable;

public interface Creature extends TimeAware, Paintable, Removable {

    int getPathPosition();

    int getPathPositionInTicks(int ticks);

    CreatureState getState();

    void setState(CreatureState creatureState);

    void takeDamage(int amount);

    int getDamage();
}
