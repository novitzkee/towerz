package game.events;

import game.creature.Creature;

public record SoldierArrivalEvent(Creature soldier) { }
