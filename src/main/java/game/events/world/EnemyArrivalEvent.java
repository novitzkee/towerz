package game.events.world;

import game.creature.Creature;

public record EnemyArrivalEvent(Creature monster) { }
