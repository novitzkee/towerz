package game.creature;

import engine.traits.Removable;

public interface ICreature extends Removable {
    Health getHealth();

    Level getLevel();
}
