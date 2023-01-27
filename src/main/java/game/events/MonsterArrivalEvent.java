package game.events;

import game.creature.ICreature;

public record MonsterArrivalEvent(ICreature monster) { }
