package game.events.world;

import game.creature.Creature;

public record EnemyDeathEvent(Creature enemy) { }
