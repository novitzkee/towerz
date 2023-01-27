package game.events;

import game.creature.ICreature;

public record SoldierArrivalEvent(ICreature soldier) { }
