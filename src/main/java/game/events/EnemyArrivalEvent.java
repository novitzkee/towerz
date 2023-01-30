package game.events;

import game.creature.Creature;

public record EnemyArrivalEvent(Creature monster) { }
